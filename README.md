# Handi: OAPEN IRUS-UK Data Harvester and Ingester

<img src="handi.jpg" alt="Handi"/>
Harvest data from IRUS-UK, enrich ip-addresses with geolocation data, split into 
tables and upload to a database.

## How to use
Run in either harvest or ingest mode. Use cronjobs to periodically download 
and ingest files. Ingestion status (`last_ingested_month`) is persisted across 
runs.

### Operation modes

#### harvest

`get last_ingested_month -> add 1 -> download corresponding JSON file from IRUS-UK`

#### ingest

`get last_ingested_month -> add 1 -> find corresponding JSON file -> ingest into db -> set last_ingested_month`

`last_ingested_month` will only be set when all of the following conditions are true:

- a corresponding JSON file is found
- the corresponding JSON file contains no header exceptions 
- ingestion into the database succeeded

Ip data in-memory-loading happens on the first encounter of an ip address by 
the ingester while parsing the JSON file (lazy loading the entire 
ip location table in a binary search tree).

## Java memory management

To speed things up, ip lookup service loads all ip addresses from database in memory.
Be sure to increase Java heap size when OutOfMemoryErrors occur.

Use JVM argument `-Xmx2G` to specify a maximum heap size of 2G.

You may also set this as a permanent java option in `/etc/environment`:  
`_JAVA_OPTIONS=-Xmx2G`


## IP database updates

Regular updates (2-4 / year) can be downloaded from <https://lite.ip2location.com/database-download>  
See `/dev/db/ipdb.import.mysql.txt` for update instructions


## Documentation
[Java docs](https://trilobiet.github.io/oapenirusingester/)

