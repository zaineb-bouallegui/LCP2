
CREATE TABLE demande.action (
    id SERIAL PRIMARY KEY,
    action_en_nature INTEGER,
    action_en_numeraire INTEGER,
    nbr_parts INTEGER,
    rapport real,
    total_des_actions real,
    valeur_nominale_parts real,
    societe_id INTEGER REFERENCES demande.societe(societe_id)
);

CREATE TABLE demande.activite(
 id SERIAL PRIMARY KEY,
 date_debut_activite DATE,
 nature_activitep_ar VARCHAR(100) NOT NULL,
 nature_activitep_fr VARCHAR(100) NOT NULL,
 nature_activites_ar VARCHAR(100) NOT NULL,
 nature_activites_fr VARCHAR(100) NOT NULL,
 societe_id INTEGER REFERENCES demande.societe(societe_id)
);

CREATE TABLE demande.adresse(
id SERIAL PRIMARY KEY,
code_postal_id INTEGER REFERENCES referentiel.code_postal(code_postal_id),
code_ville INTEGER REFERENCES referentiel.villes(code_ville),
gov_code INTEGER REFERENCES referentiel.gouvernorats(gov_code),
rue_ar VARCHAR(100) NOT NULL,
rue_fr VARCHAR(100) NOT NULL,
societe_id INTEGER REFERENCES demande.societe(societe_id),
code_postal INTEGER REFERENCES referentiel.code_postal(code_postal)
);