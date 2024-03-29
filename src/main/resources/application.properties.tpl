
# Properties for deployment

spring.jpa.properties.hibernate.jdbc.batch_size=100
spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true

logging.level.root=WARN
logging.level.oapen.irusuk=INFO
logging.file.name=${user.home}/dashboard/irusuk/logs/ingester.log

spring.datasource.url=jdbc:mysql://<yourhost>:3306/irusuk?reconnect=true&rewriteBatchedStatements=true
spring.datasource.username=*******
spring.datasource.password=*******

irusuk.baseurl=https://irus.jisc.ac.uk/api/v3/oapen/reports/oapen_ir/
irusuk.requestorid=********************************
irusuk.apikey=********************************
irusuk.platform=***

app.path.reports=${user.home}/dashboard/irusuk/downloaded_reports 
app.path.app-status=${user.home}/dashboard/irusuk/app-state.properties

dbtests.enabled=FALSE
