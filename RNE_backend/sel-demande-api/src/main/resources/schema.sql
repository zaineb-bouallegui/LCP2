CREATE SCHEMA IF NOT EXISTS demande;
CREATE SEQUENCE IF NOT EXISTS demande.societe_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.activite_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.action_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.adresse_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.capital_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.demande_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.documents_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.membre_spec_id_seq;
CREATE SEQUENCE IF NOT EXISTS demande.personne_id_seq;


CREATE TABLE IF NOT EXISTS demande.certificat
(
    isValid  boolean,
    denomination_sociale_fr character varying COLLATE pg_catalog."default",
    denomination_sociale_ar character varying COLLATE pg_catalog."default",
    numCertificat character varying COLLATE pg_catalog."default"
);

INSERT INTO demande.certificat ("numCertificat", "denomination_sociale_ar", "denomination_sociale_fr", "isValid")
VALUES 
    ('250', 'الشركة الفرنسية', 'Société Française', true),
    ('1000', 'الشركة الإنجليزية', 'Société Anglaise', false);


CREATE TABLE IF NOT EXISTS demande.benifvalidation
(
    nom character varying COLLATE pg_catalog."default",
    prenom character varying COLLATE pg_catalog."default",
    numbenif character varying COLLATE pg_catalog."default"
);

INSERT INTO demande.benifvalidation (nom, prenom, numbenif)
VALUES 
    ('Dupont', 'Jean', 'BENIF123'),
    ('Martin', 'Paul', 'BENIF124');

CREATE TABLE IF NOT EXISTS demande.identifiant_unique
(
    id character varying COLLATE pg_catalog."default",
    denomination_sociale_fr character varying COLLATE pg_catalog."default",
    denomination_sociale_ar character varying COLLATE pg_catalog."default",
    isValid boolean,
    formeJuridiqueName character varying COLLATE pg_catalog."default"
);

INSERT INTO demande.identifiant_unique ("id", "denomination_sociale_fr", "denomination_sociale_ar", "isValid", "formeJuridiqueName")
VALUES 
    ('120', 'Société Alpha', 'شركة ألفا', true, 'SARL'),
    ('124', 'Société Beta', 'شركة بيتا', false, 'SA');


CREATE TABLE IF NOT EXISTS demande.societe
(
    id bigint NOT NULL DEFAULT nextval('demande.societe_id_seq'::regclass),
    uid character varying(255) COLLATE pg_catalog."default",
    c_enseigne boolean,
    c_nom_commercial boolean,
    denomination_sociale_ar character varying(255) COLLATE pg_catalog."default",
    denomination_sociale_fr character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    nb_employes integer,
    num_beneficiaire character varying(255) COLLATE pg_catalog."default",
    num_reservation character varying(255) COLLATE pg_catalog."default",
    origine_fond_commercial character varying(255) COLLATE pg_catalog."default",
    forme_juridique_id bigint,
    CONSTRAINT societe_pkey PRIMARY KEY (id),
    CONSTRAINT fkjywhfmmgpne2wo1y26mcju3ke FOREIGN KEY (forme_juridique_id)
        REFERENCES referentiel.forme_juridique (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT societe_origine_fond_commercial_check CHECK (origine_fond_commercial::text = 'creation'::text)
);

CREATE TABLE IF NOT EXISTS demande.activite
(
    id bigint NOT NULL DEFAULT nextval('demande.activite_id_seq'::regclass),
    activite_local character varying(255) COLLATE pg_catalog."default",
    date_debut_activite date,
    nature_activitep_ar character varying(255) COLLATE dpg_catalog."default",
    nature_activitep_fr character varying(255) COLLATE pg_catalog."default",
    nature_activites_ar character varying(255) COLLATE pg_catalog."default",
    nature_activites_fr character varying(255) COLLATE pg_catalog."default",
    societe_id bigint,
    CONSTRAINT activite_pkey PRIMARY KEY (id),
    CONSTRAINT fka0otytja3q6r82stx320aat2o FOREIGN KEY (societe_id)
        REFERENCES demande.societe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT activite_activite_local_check CHECK (activite_local::text = ANY (ARRAY['Permanente'::character varying, 'Saisonnier'::character varying, 'Ambulant'::character varying, 'Temporaire'::character varying, 'Autre'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS demande.action
(
    id bigint NOT NULL DEFAULT nextval('demande.action_id_seq'::regclass),
    action_en_nature integer,
    action_en_numeraire integer,
    created_at timestamp(6) without time zone NOT NULL,
    nbr_parts integer,
    rapport real,
    total_des_actions real,
    updated_at timestamp(6) without time zone NOT NULL,
    valeur_nominale_part real,
    societe_id bigint,
    CONSTRAINT action_pkey PRIMARY KEY (id),
    CONSTRAINT fkpym6srda0iaigrlw42qxk94k7 FOREIGN KEY (societe_id)
        REFERENCES demande.societe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
);

CREATE TABLE IF NOT EXISTS demande.adresse (
    id BIGINT NOT NULL DEFAULT nextval('demande.adresse_id_seq'::regclass),
    adresse_type VARCHAR(255) NOT NULL,
    code_postal VARCHAR(255) NOT NULL,
    code_ville VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    gov_code VARCHAR(255) NOT NULL,
    rue_ar VARCHAR(255) NOT NULL,
    rue_fr VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    societe_id BIGINT NOT NULL,
    CONSTRAINT adresse_pkey PRIMARY KEY (id),
    CONSTRAINT fk_societe FOREIGN KEY (societe_id)
        REFERENCES demande.societe (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT adresse_adresse_type_check CHECK (adresse_type IN ('SIEGE', 'PERSONNEL', 'ACTIVITY', 'MEMBER'))
);

CREATE TABLE IF NOT EXISTS demande.capital
(
    id bigint NOT NULL DEFAULT nextval('demande.capital_id_seq'::regclass),
    action real,
    capital_min real,
    capital_tdn real,
    created_at timestamp(6) without time zone NOT NULL,
    date_cloture date,
    duree_societe integer NOT NULL,
    nb_associes integer,
    nbr_parts integer,
    updated_at timestamp(6) without time zone NOT NULL,
    valeur_parts_nature integer,
    valeur_parts_numeraire integer,
    societe_id bigint,
    CONSTRAINT capital_pkey PRIMARY KEY (id),
    CONSTRAINT uk_7o2iy3see8if9nv2kuyi2u9o4 UNIQUE (societe_id),
    CONSTRAINT fkqc42yxctiypfv8mqhfh2o5bww FOREIGN KEY (societe_id)
        REFERENCES demande.societe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
);

CREATE TABLE IF NOT EXISTS demande.demande
(
    id bigint NOT NULL DEFAULT nextval('demande.demande_id_seq'::regclass),
    type_paiment character varying(255) COLLATE pg_catalog."default",
    code_statut character varying(255) COLLATE pg_catalog."default",
    date_envoi date,
    deadline date,
    statut_demande character varying(255) COLLATE pg_catalog."default",
    type_registre character varying(255) COLLATE pg_catalog."default",
    societe_id bigint,
    CONSTRAINT demande_pkey PRIMARY KEY (id),
    CONSTRAINT fk871kx9silss1dw9a60o27gosr FOREIGN KEY (societe_id)
        REFERENCES demande.societe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE, 
    CONSTRAINT demande_statut_demande_check CHECK (statut_demande::text = ANY (ARRAY['Cree'::character varying, 'EnCours'::character varying, 'StatutEnCours'::character varying,'Validee'::character varying,'Refusee'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS demande.documents
(
    id bigint NOT NULL DEFAULT nextval('demande.documents_id_seq'::regclass),
    contenu character varying(10485760) COLLATE pg_catalog."default",
    date_depot date,
    is_valid boolean,
    nom character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    demande_id bigint,
    CONSTRAINT documents_pkey PRIMARY KEY (id),
    CONSTRAINT fks5w89g7s2mopg6ta0r2l0erji FOREIGN KEY (demande_id)
        REFERENCES demande.demande (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS demande.membre_spec
(
    id bigint NOT NULL DEFAULT nextval('demande.membre_spec_id_seq'::regclass),
    adresse character varying(255) COLLATE pg_catalog."default",
    created_at timestamp(6) without time zone NOT NULL,
    date_fin_nomination date,
    date_nomination date,
    duree_nomination bigint,
    email character varying(255) COLLATE pg_catalog."default",
    genre character varying(255) COLLATE pg_catalog."default",
    is_deposant boolean,
    numero integer NOT NULL,
    pouvoirs character varying(255) COLLATE pg_catalog."default",
    qualite character varying(255) COLLATE pg_catalog."default",
    updated_at timestamp(6) without time zone NOT NULL,
    societe_id bigint,
    CONSTRAINT membre_spec_pkey PRIMARY KEY (id),
    CONSTRAINT fkboil7ve6o7x8pyyaqc3u40l6b FOREIGN KEY (societe_id)
        REFERENCES demande.societe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT membre_spec_qualite_check CHECK (qualite::text = ANY (ARRAY['Gerant'::character varying, 'Co_Gerant1'::character varying, 'Co_Gerant2'::character varying, 'Gerant_Delegue'::character varying, 'President_du_conseil_administration'::character varying, 'Vice_President_du_conseil_administration'::character varying, 'Commissaire'::character varying]::text[])),
    CONSTRAINT membre_spec_genre_check CHECK (genre::text = ANY (ARRAY['male'::character varying, 'femelle'::character varying]::text[])),
    CONSTRAINT membre_spec_pouvoirs_check CHECK (pouvoirs::text = ANY (ARRAY['RepresentantLegal'::character varying, 'Pouvoir_etendu'::character varying, 'Signature'::character varying, 'Co_Signature'::character varying, 'RepresentantAdministration'::character varying, 'RepresentantTribunaux'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS demande.personne
(
    id bigint NOT NULL DEFAULT nextval('demande.personne_id_seq'::regclass),
    id_carte bigint,
    created_at timestamp(6) without time zone NOT NULL,
    date_delivrance date,
    date_naiss date,
    genre character varying(255) COLLATE pg_catalog."default",
    lieu_naiss_ar character varying(255) COLLATE pg_catalog."default",
    lieu_naiss_fr character varying(255) COLLATE pg_catalog."default",
    nom_prenom_ar character varying(255) COLLATE pg_catalog."default",
    nom_prenom_fr character varying(255) COLLATE pg_catalog."default",
    updated_at timestamp(6) without time zone NOT NULL,
    adresse_id bigint,
    CONSTRAINT personne_pkey PRIMARY KEY (id),
    CONSTRAINT uk_iw4d7fbix8d591gyvja8qcmj6 UNIQUE (adresse_id),
    CONSTRAINT fk64wypb6go3kiegrp8ntdf7ywp FOREIGN KEY (adresse_id)
        REFERENCES demande.adresse (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE, 
    CONSTRAINT personne_genre_check CHECK (genre::text = ANY (ARRAY['male'::character varying, 'femelle'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS demande.membre_spec_personnes
(
    membre_spec_id bigint NOT NULL,
    personnes_id bigint NOT NULL,
    CONSTRAINT fkbr8m8rc36fl989uk4iaoqtqpl FOREIGN KEY (personnes_id)
        REFERENCES demande.personne (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE, 
    CONSTRAINT fkfir07jgqf4mynrqdtyay6t2i0 FOREIGN KEY (membre_spec_id)
        REFERENCES demande.membre_spec (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE  
);

CREATE TABLE IF NOT EXISTS demande.personne_actions
(
    personnes_id bigint NOT NULL,
    actions_id bigint NOT NULL,
    CONSTRAINT personne_actions_pkey PRIMARY KEY (personnes_id, actions_id),
    CONSTRAINT fkfu0u5stmjh6hexivmnc8k0e5e FOREIGN KEY (actions_id)
        REFERENCES demande.action (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE, 
    CONSTRAINT fkolbxt4e1ds42tmh1f16ue2dnu FOREIGN KEY (personnes_id)
        REFERENCES demande.personne (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE  
)