## binance-influxdb-grafana

   *This application is used to store the Order Updates into the InfluxDB. This code handles all BTC pairs order updates and store it in Influx database. The stored data are visualized using Grafana tool.*
   
### InfluxDB SetUP

InfluxDB is a time series database designed to handle high write and query loads

   1. Download InfluxDB https://dl.influxdata.com/influxdb/releases/influxdb-1.7.7_windows_amd64.zip
   2. unzip influxdb-1.7.7_windows_amd64.zip
   3. Go into the folder.
   4. Run influxd (server)
   5. Run influx (client)

   Note: InfluxDB would run under defalut port 8086 

### Grafana Tool Setup

Grafana is an open source visualization tool. It allows you to query, visualize, alert on and understand your metrics no matter where they are stored. 

   1. Download Grafana https://dl.grafana.com/oss/release/grafana-6.2.5.windows-amd64.zip
   2. Installation steps available in https://grafana.com/docs/installation/windows/

### Integrating Grafana with InfluxDB

Using Grafana GUI
   1. Add data source as InfluxDB and give necessary information to configure 
   2. Create a variable. This is used to create dynamic dashboard based on the variable value.
      1. Go the Dashboard setting and select variable menu
      2. Create a new vaiable
         Name : BTCPAIR
         Type : Query
         DataSource : InfluxDB
         Query : show measurements
   2. Create a dashboard 
      1. Choose Table template 
      2. Queries : Add Query for BIDS table
            """ SELECT "price", "quantity", "price"*"quantity"  as Total FROM "stock_retention".$BTCPAIRS WHERE ("category" = 'BIDS') AND $timeFilter """
      3. Visualization: Format the table
      4. General: Give panel name
      5. Save the Dashboard
   3. Repeat the above step #2 by adding new panel in the same dashboard. And in the Queries section give the below query for ASKS
           """ SELECT "price", "quantity", "price"*"quantity"  as Total FROM "stock_retention".$BTCPAIRS WHERE ("category" = 'ASKS') AND $timeFilter """
  
## Start Project

   1. Download or clone the project
   2. Run maven pom file to build all dependencies. (mvn clean)
   2. Run the below java file to fetch the all BTC pairs info and store in the database
         /binance-influxdb/src/main/java/com/binance/api/client/store/ExecutePairs.java

   Note: Refer visualization screenshot added along with README file.



   
 
