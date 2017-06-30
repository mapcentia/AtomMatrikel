CREATE TABLE IF NOT EXISTS %s.strandbeskyttelse
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
  temaOmfang character varying(255),
  areal int,
  timeofcreation date,
  the_geom geometry(multipolygon,25832),
  constraint strandbeskyttelse_pkey primary key (gid)
)