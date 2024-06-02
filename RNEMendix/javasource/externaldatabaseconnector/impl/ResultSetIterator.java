package externaldatabaseconnector.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * ResultSetIterator implements {@link Iterator} interface. It wraps
 * {@link ResultSet} into a stream for more convenient usage. Along with that,
 * it provides information about columns of the given result set.
 */
public class ResultSetIterator implements Iterator<ResultSet> {
    private final ResultSet resultSet;
    private final List<ColumnInfo> columnInfos;
    private final IMetaObject metaObject;
    public ResultSetIterator(final ResultSet resultSet, final IMetaObject metaObject, final String columnMapping) {
        this.resultSet = resultSet;
        this.metaObject = metaObject;
        this.columnInfos = createColumnInfos(resultSet,columnMapping);
    }

    private List<ColumnInfo> createColumnInfos(final ResultSet resultSet,final String columnMapping) {
        try {
            final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            final int columnCount = resultSetMetaData.getColumnCount();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObjectColumnMapping;
            try {
                jsonObjectColumnMapping = (JSONObject) jsonParser.parse(columnMapping);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return IntStream.rangeClosed(1, columnCount).mapToObj(i->getColumnInfo(i,jsonObjectColumnMapping))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ColumnInfo getColumnInfo(final int index,final JSONObject jsonObjectColumnMapping) {
        final String columnName;
        try {
            columnName = resultSet.getMetaData().getColumnLabel(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String entityAttributeName = jsonObjectColumnMapping.get(columnName).toString();
        final Predicate<IMetaPrimitive> caseInsensitiveName = (IMetaPrimitive mp) -> mp.getName()
                .equalsIgnoreCase(entityAttributeName);
        final Optional<? extends IMetaPrimitive> primitive = metaObject.getMetaPrimitives().stream()
                .filter(caseInsensitiveName).findFirst();

        final IMetaPrimitive.PrimitiveType type = primitive.<RuntimeException>orElseThrow(() -> {
            final String msg = "The entity type '%s' does not contain the primitive '%s' as specified in the query.";
            throw new RuntimeException(String.format(msg, metaObject.getName(), columnName));
        }).getType();

        return new ColumnInfo(index, entityAttributeName, type);
    }

    @Override
    public ResultSet next() {
        return resultSet;
    }

    @Override
    public boolean hasNext() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<ResultSet> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this, Spliterator.ORDERED), false);
    }

    public Stream<ColumnInfo> getColumnInfos() {
        return columnInfos.stream();
    }

}
