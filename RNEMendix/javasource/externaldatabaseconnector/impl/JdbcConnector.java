package externaldatabaseconnector.impl;

import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive.PrimitiveType;
import externaldatabaseconnector.interfaces.ConnectionManager;
import externaldatabaseconnector.interfaces.ObjectInstantiator;
import externaldatabaseconnector.interfaces.PreparedStatementCreator;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * JdbcConnector implements the execute query (and execute statement)
 * functionality, and returns a {@link Stream} of {@link IMendixObject}s.
 */
public class JdbcConnector {
    private final ILogNode logNode;
    private final ObjectInstantiator objectInstantiator;
    private final ConnectionManager connectionManager;
    public final PreparedStatementCreator preparedStatementCreator;

    public JdbcConnector(final ILogNode logNode, final ObjectInstantiator objectInstantiator,
                         final ConnectionManager connectionManager, final PreparedStatementCreator preparedStatementCreator
    ) {
        this.logNode = logNode;
        this.objectInstantiator = objectInstantiator;
        this.connectionManager = connectionManager;
        this.preparedStatementCreator = preparedStatementCreator;
    }

    public JdbcConnector(final ILogNode logNode) {
        this(logNode, new ObjectInstantiatorImpl(), ConnectionManagerSingleton.getInstance(),
                new PreparedStatementCreatorImpl());
    }

    public List<IMendixObject> executeQuery(final String jdbcUrl, final String userName, final String password,
                                            final IMetaObject metaObject, final String sql, final String queryParameters,
                                            final String columnMapping, final IContext context)
                                throws SQLException, DatabaseConnectorException {
        if (logNode.isTraceEnabled()) logNode.trace(String.format("executeQuery: %s, %s, %s", jdbcUrl, userName, sql));

        try (Connection connection = connectionManager.getConnection(jdbcUrl, userName, password);
             PreparedStatement preparedStatement = preparedStatementCreator.create(sql, queryParameters, connection);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ResultSetReader resultSetReader = new ResultSetReader(resultSet, metaObject, columnMapping);
            return createMendixObjects(context, metaObject, resultSetReader.readAll());
        }
    }

    private Function<Object, Object> toSuitableValue(final PrimitiveType type) {
        return v -> type == PrimitiveType.Binary ? new ByteArrayInputStream((byte[]) v) : v;
    }

    private List<IMendixObject> createMendixObjects(final IContext context,
                                                    final IMetaObject metaObject, List<Map<String, Optional<Object>>> resultSet) throws DatabaseConnectorException {
        List<IMendixObject> convertedResult = new ArrayList<>();
        for (Map<String, Optional<Object>> result : resultSet) {
            convertedResult.add(createMendixObject(context, metaObject, result));
        }
        return convertedResult;
    }

    private IMendixObject createMendixObject(final IContext context,
                                             final IMetaObject metaObject, Map<String, Optional<Object>> columns) throws DatabaseConnectorException {
        IMendixObject obj = objectInstantiator.instantiate(context, metaObject.getName());

        for (Map.Entry<String, Optional<Object>> column : columns.entrySet()) {
            setMemberValue(context, metaObject, obj, column.getKey(), column.getValue());
        }
        return obj;
    }

    private void setMemberValue(IContext context, IMetaObject metaObject, IMendixObject obj, String name, Optional<Object> value) throws DatabaseConnectorException {
        IMetaPrimitive metaPrimitive = metaObject.getMetaPrimitive(name);
        if (metaPrimitive == null) {
            String errorMessage = String.format(
                    "Database attribute '%1$s' is not in the entity '%2$s'."
                            + " Please check the entity '%2$s' attribute names with the database column names.",
                    name, metaObject.getName());
            logNode.error(errorMessage);
            throw new DatabaseConnectorException(errorMessage);
        }
        PrimitiveType type = metaPrimitive.getType();
        // convert to suitable value (different for Binary type)
        Function<Object, Object> toSuitableValue = toSuitableValue(type);
        // for Boolean type, convert null to false
        Supplier<Object> defaultValue = () -> type == PrimitiveType.Boolean ? Boolean.FALSE : null;
        // apply two functions declared above
        Object convertedValue = value.map(toSuitableValue).orElseGet(defaultValue);
        // update object with converted value
        if (type == PrimitiveType.HashString)
            throw new DatabaseConnectorException(String.format(
                    "Attribute type Hashed String for attribute '%1$s' on entity '%2$s' is not supported, "
                            + "please use attribute type 'String' instead",
                    name, metaObject.getName()));
        else
            obj.setValue(context, name, convertedValue);
    }

    public long executeStatement(final String jdbcUrl, final String userName, final String password, final String sql, final String queryParameters)
            throws SQLException {
        if (logNode.isTraceEnabled())
            logNode.trace(String.format("executeStatement: %s, %s, %s", jdbcUrl, userName, sql));

        try (Connection connection = connectionManager.getConnection(jdbcUrl, userName, password);
             PreparedStatement preparedStatement = preparedStatementCreator.create(sql, queryParameters, connection)) {
            return preparedStatement.executeUpdate();
        }
    }
}
