CREATE TABLE IF NOT EXISTS %s.fredskovlinie
(
  uuid character varying(32) unique,
  featureid double precision,
  temafladeid double precision,
  featurecode smallint,
  featuretype character varying(40),
  kms_sagsid double precision,
  kms_journalnummer character varying(12),
  matrikelskelid int,
  forloeb character varying(255),
  timeofcreation date,
  the_geom geometry(polygon,25832),
  constraint fredskovlinie_pkey primary key (uuid)
)