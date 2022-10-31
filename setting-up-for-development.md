### Az applikáció elindítása
- pullold le a projectet a link segítsévégel [PersonalRegistry](https://github.com/horvzs/personalRegistry.git)
- Az applikáció mssql adatbázist használ, győzödj meg róla, hogy van futó instance-od
- Az adatbázis inicializálásához az alábbi *sql*-eket futtasd
    - > src/main/resources/sql/01.init_database.sql 
    - > src/main/resources/sql/02.insert_base_data.sql
- Az applikáció endpointjaira készültek előre definiált hívások, melyeket itt találsz
  -  > .idea/httpRequest.http