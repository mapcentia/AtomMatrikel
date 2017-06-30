CREATE TABLE IF NOT EXISTS %s.optagetvej
(
  gid serial,
  uuid character varying(255),
  featureid double precision,
  featurecode smallint,
  featuretype character varying(40),
  ejerlavsnavn character varying(39),
  landsejerlavskode double precision,
  matrikelnummer character varying(8),
  jordstykkeid int,
  kms_sagsid double precision,
  dq_prodmetode character varying(255),
  timeofcreation date,
  the_geom geometry(linestring,25832),
  constraint optagetvej_pkey primary key (gid)
)