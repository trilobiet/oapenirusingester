
SHORT HOWTO COMMAND LINE
========================

This should normally be handled by a cronjob, but can be called from command line.    

Run as user oapen: 
su oapen

Directory 'irusuk' contains file 'app-state.properties' 
with key 'last_ingested_month'

Update this key to perform harvest or ingestion for the next month:
Example: to ingest month 2022-02 set last_ingested_month=2022-01

To harvest (download from irus-uk) call
    ./irus-ingester.jar harvest

To ingest (parse downloaded file) call
    ./irus-ingester.jar ingest
    

Log files should report a failure on ingestion every day, 
except on the day following ingestion of a new harvest.    
