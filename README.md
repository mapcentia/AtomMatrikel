# Repliker Matrikelkortet for hele Danmark
Repliker Matrikelkortet for hele Danmark til lokal database og hold den opdateret med ændringer på daglig basis.

## Hvordan bruges replikatoren
Replikatoren er et kommandolinje-værktøj skrevet i Java og derfor kan anvendes på alle platforme (Windows, Linux, Mac mv.)

Pt. er det kun PostgreSQL database-serveren, som er understøttet.

### Forudsætninger
- Et Java 8 Runtime
- PostgreSQL med PostGIS

### Installering
- Hent zip filen på: https://github.com/mapcentia/AtomMatrikel/archive/master.zip
- Udpak den.
- Kopiere AtomMatrikel-master/bin/AtomMatrikel.jar til der, hvor du vil have den.
- opret en konfigurationsfil (kan lægges i samme folder som jar-filen ), kald den fx config.yml, kopiere dette til den og ret til:

```
#PostgreSQL forbindelse. Databasen "min_matrikel_database" skal have Postgis extension aktiveret

connection:
      url: jdbc:postgresql://127.0.0.1:5432/min_matrikel_database
      user: postgres
      pw: 1234

# Database schema hvor data havner. Schemaet skal være oprettet

schema: matrikel

# Login til Kortforsyningen

kortforsyningen:
    user: MapCentia
    pw: xxxxxx
```

Kør replikatoren. Første gang den køres hentes alle ejerlav i Atom feed'et, hvilket gerne skulle dække hele Danmark. Næste gang hentes kun ejerlav med ændringer og tabellerne opdateres.
```
java -jar AtomMatrikel.jar config.yml
```
Følgende data bliver kopieret til databasen:
- Jordstykke
- Optaget vej
- Fredskov
- Strandbeskyttelse
- Centroide
    
Ovenstående kommando kan indsættes i en job scheduler og køres en gang om dag.

### Note om hukommelsesforbrug
Kortforsyningens Atom feed publicerer alle data for et ejerlav i et stort XML dokument. For de største ejerlav kan dette dokument være relativt stort og indlæsningen kræver en vis mængde hukommelse.   
Det anbefales at have min. 4GB fri RAM til rådighed.
