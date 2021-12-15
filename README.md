# OAPEN IRUS-UK Data Ingester

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

Ip address in memory loading will only happen when the ingester encounters 
the first ip address while parsing the JSON file.

## Java memory management

To speed things up, ip lookup service loads all ip addresses from database in memory.
Be sure to increase Java heap size when OutOfMemoryErrors occur.

Use JVM argument `-Xmx1G` to specify a maximum heap size of 1G.

You may also set this as a permanent java option in `/etc/environment`:  
`_JAVA_OPTIONS=-Xmx1G`
	

