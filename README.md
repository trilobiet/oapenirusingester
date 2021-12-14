# OAPEN IRUS-UK Data Ingester

Harvest data from IRUS-UK, enrich ip-addresses with geolocation data, split in 
tables and upload to a database.


## Java memory management

To speed things up, JpaIpLookupService loads all ip addresses from database in memory.
Be sure to increase Java heap size when OutOfMemoryErrors occur.

Use JVM argument `-Xmx1G` to specify a maximum heap size of 1G.

You may also set this as a permanent java option in `/etc/environment`:  
`_JAVA_OPTIONS=-Xmx1G`
	

