CREATE TABLE IF NOT EXISTS %s.centroide
(
  uuid character varying(32) unique,
  featureid double precision,
  featurecode smallint,
  featuretype character varying(40),
  ejerlavsnavn character varying(39),
  landsejerlavskode double precision,
  matrikelnummer character varying(8),
  jordstykkeid int,
  kms_sagsid double precision,
  kms_journalnummer character varying(12),
  timeofcreation date,
  the_geom geometry(point,25832),
  constraint centroide_pkey primary key (uuid)
)