-- Créer la séquence si elle n'existe pas
CREATE SEQUENCE IF NOT EXISTS referentiel.code_postal_id_seq;
CREATE SEQUENCE IF NOT EXISTS referentiel.forme_juridique_id_seq;
-- Table referentiel.type_redaction
CREATE TABLE IF NOT EXISTS referentiel.type_redaction
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT type_redaction_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS referentiel.forme_juridique
(
    id bigint NOT NULL DEFAULT nextval('referentiel.forme_juridique_id_seq'::regclass),
    nom character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT forme_juridique_pkey PRIMARY KEY (id)
)
INSERT INTO referentiel.forme_juridique (id, nom)
VALUES 
    (1, 'Société Civile'),
    (2, 'Société Anonyme'),
    (3, 'Société Unipersonnelle à Responsabilité Limitée'),
    (4, 'Société à Responsabilité Limitée');

-- Table referentiel.gouvernorats
CREATE TABLE IF NOT EXISTS referentiel.gouvernorats
(
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    activation character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nom_ar character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nom_fr character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT gouvernorats_pkey PRIMARY KEY (code),
    CONSTRAINT gouvernorats_activation_check CHECK (activation::text = ANY (ARRAY['Active'::character varying, 'Desactive'::character varying]::text[]))
);
INSERT INTO referentiel.gouvernorats (code, activation, nom_ar, nom_fr)
VALUES 
('GOV001', 'Active', 'تونس', 'Tunis'),
('GOV002', 'Active', 'أريانة', 'Ariana'),

-- Table referentiel.sections
CREATE TABLE IF NOT EXISTS referentiel.sections
(
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    activation character varying(255) COLLATE pg_catalog."default" NOT NULL,
    titre_ar character varying(255) COLLATE pg_catalog."default" NOT NULL,
    titre_fr character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT sections_pkey PRIMARY KEY (code),
    CONSTRAINT sections_activation_check CHECK (activation::text = ANY (ARRAY['Active'::character varying, 'Desactive'::character varying]::text[]))
);
INSERT INTO referentiel.sections (code, activation, titre_ar, titre_fr) VALUES 
('SEC001', 'Active', 'قسم 1', 'Section 1'),
('SEC002', 'Active', 'قسم 2', 'Section 2');
-- Table referentiel.villes
CREATE TABLE IF NOT EXISTS referentiel.villes
(
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    activation character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name_ar character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nom_fr character varying(255) COLLATE pg_catalog."default" NOT NULL,
    gouvernorat_code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT villes_pkey PRIMARY KEY (code),
    CONSTRAINT fkhiuoi5utrfkr2w33e2df7i43n FOREIGN KEY (gouvernorat_code)
        REFERENCES referentiel.gouvernorats (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT villes_activation_check CHECK (activation::text = ANY (ARRAY['Active'::character varying, 'Desactive'::character varying]::text[]))
);
INSERT INTO referentiel.villes (code, activation, name_ar, nom_fr, gouvernorat_code)
VALUES 
('VILLE001', 'Active', 'مدينة تونس', 'Ville de Tunis', 'GOUV001'),
('VILLE002', 'Active', 'سكرة', 'Soukra', 'GOUV002');

-- Table referentiel.code_postal
CREATE TABLE IF NOT EXISTS referentiel.code_postal
(
    id bigint NOT NULL DEFAULT nextval('referentiel.code_postal_id_seq'::regclass),
    code_postal character varying(255) COLLATE pg_catalog."default" NOT NULL,
    ville_code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT code_postal_pkey PRIMARY KEY (id),
    CONSTRAINT fkm5co6047d6buipnn602ymsae7 FOREIGN KEY (ville_code)
        REFERENCES referentiel.villes (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
INSERT INTO referentiel.code_postal (id, code_postal, ville_code)
VALUES 
(1, '1000', 'VILLE001'),
(2, '2000', 'VILLE002');

-- Table referentiel.articles
CREATE TABLE IF NOT EXISTS referentiel.articles
(
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    activation character varying(255) COLLATE pg_catalog."default" NOT NULL,
    autre_proposition character varying(255) COLLATE pg_catalog."default",
    created_at timestamp(6) without time zone NOT NULL,
    text_complementaire character varying(255) COLLATE pg_catalog."default",
    titre_ar character varying(255) COLLATE pg_catalog."default" NOT NULL,
    titre_fr character varying(255) COLLATE pg_catalog."default" NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    section_code character varying(255) COLLATE pg_catalog."default",
    type_redaction_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT articles_pkey PRIMARY KEY (code),
    CONSTRAINT fk4u3w0wo8inv0n8f4m3xll0t0e FOREIGN KEY (section_code)
        REFERENCES referentiel.sections (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk6ewh6sk1hm5393089nx7ts3rg FOREIGN KEY (type_redaction_id)
        REFERENCES referentiel.type_redaction (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT articles_activation_check CHECK (activation::text = ANY (ARRAY['Active'::character varying, 'Desactive'::character varying]::text[]))
);
INSERT INTO referentiel.articles (code, activation, created_at, titre_ar, titre_fr, updated_at, section_code, type_redaction_id)
VALUES 
('ART001', 'Active', CURRENT_TIMESTAMP, 'Titre AR A', 'Titre FR A', CURRENT_TIMESTAMP, 'SEC001', 'Type1'),
('ART002', 'Active', CURRENT_TIMESTAMP, 'Titre AR B', 'Titre FR B', CURRENT_TIMESTAMP, 'SEC002', 'Type2'),


-- Table referentiel.propositions
CREATE TABLE IF NOT EXISTS referentiel.propositions
(
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    activation character varying(255) COLLATE pg_catalog."default",
    texte_ar character varying(255) COLLATE pg_catalog."default" NOT NULL,
    texte_fr character varying(255) COLLATE pg_catalog."default" NOT NULL,
    article_code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT propositions_pkey PRIMARY KEY (code),
    CONSTRAINT fkijfr59b8xqvkihwt68pmnahcs FOREIGN KEY (article_code)
        REFERENCES referentiel.articles (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT propositions_activation_check CHECK (activation::text = ANY (ARRAY['Active'::character varying, 'Desactive'::character varying]::text[]))
);

-- Table referentiel.statuts
CREATE TABLE IF NOT EXISTS referentiel.statuts
(
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    categorie character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    forme_juridique_id bigint,
    titre character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT statuts_pkey PRIMARY KEY (code),
    CONSTRAINT statuts_categorie_check CHECK (categorie::text = ANY (ARRAY['Association'::character varying, 'Societe'::character varying]::text[]))
);
INSERT INTO referentiel.statuts (code, categorie, description, forme_juridique_id, titre)
VALUES 
('STAT001', 'Societe', 'Description du statut 1', 1, 'Titre du statut 1'),
('STAT002', 'Societe', 'Description du statut 2', 2, 'Titre du statut 2'),

-- Table referentiel.statuts_sections
CREATE TABLE IF NOT EXISTS referentiel.statuts_sections
(
    statut_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    sections_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fkkmvbkwdc6ndts0ekj2xm05oll FOREIGN KEY (sections_code)
        REFERENCES referentiel.sections (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkqy2tcpi2o55wls0ng4nv1hrc3 FOREIGN KEY (statut_code)
        REFERENCES referentiel.statuts (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
