CREATE TABLE IF NOT EXISTS %s.strandbeskyttelse
(
  uuid character varying(32) unique,
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
  the_geom geometry(polygon,25832),
  constraint strandbeskyttelse_pkey primary key (uuid)
)