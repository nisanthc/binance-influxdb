## Binance-InfluxDB-Grafana

   *This application is used to store the Order Updates into the InfluxDB. This code handles all BTC/BNB pairs order updates and store it in Influx database. The stored data are visualized using Grafana tool.*
   
### InfluxDB Setup

InfluxDB is a time series database designed to handle high write and query loads

   1. Download InfluxDB https://dl.influxdata.com/influxdb/releases/influxdb-1.7.7_windows_amd64.zip
   
   2. unzip influxdb-1.7.7_windows_amd64.zip
   
   3. Go into the folder.
   
   4. Run influxd (server)
   
   5. Run influx (client) and create database using below query
   
          create database "stock_order";
          create retention policy "stock_retention" on "stock_order" duration 10d replication 1 default;
          

   Note: InfluxDB server runs with the default port http://127.0.0.1:8086

### Grafana Tool Setup

Grafana is an open source visualization tool. It allows you to query, visualize, alert on and understand your metrics no matter where they are stored. 

   1. Download Grafana https://dl.grafana.com/oss/release/grafana-6.2.5.windows-amd64.zip
   
   2. Installation steps available in https://grafana.com/docs/installation/windows/ (Change the port to 8888)
    
   Note: Open URL http://127.0.0.1:8888 to check the grafana 

### Integrating Grafana with InfluxDB

Grafana GUI 

   1. Open Grafana URL 

   2. Goto Home Dashboard. Click "create your first data source" and select "InfluxDB". Give necessary information to configure.
   
               Name     : InfluxDB
               Url      : http://127.0.0.1:8086
               Database : stock_order

   3. Dynamic dashboard has been created using variable
   
        1. Go the Dashboard setting and select variable menu

        2. Create a new vaiable with below parameter
      
               Name       : PAIRS
               Type       : Query
               DataSource : InfluxDB,
               Query      : show tag values from orderbook with key = "symbol"

   4. Create a dashboard 
   
        a. Goto Home Dashboard and click "create your first dashboard" 
        
        b. Select "Add Query" and paste the below BIDS query under toogle edit mode
        
               SELECT "price", "quantity", "price"*"quantity"  as Total FROM "stock_retention"."orderbook" WHERE ("category" = 'BIDS'    and  "symbol" = '$PAIRS') AND $timeFilter

        c. Select Visualization Icon and choose table format from drop down menu. Format the table as your choice by providing column               style 
        
        d. select setting icon and provide title name
        
        e. Save the Dashboard
        
   5. Open the above dashboard, repeat the above step 4.a, 4.b, 4.c, 4.d by clicking "add panel" icon in top menu. Paste the below ASKS       query 
          
               SELECT "price", "quantity", "price"*"quantity"  as Total FROM "stock_retention"."orderbook" WHERE ("category" = 'ASKS' and  "symbol" = '$PAIRS') AND $timeFilter
  
## Start Project

   1. Download or clone the project
   
   2. Run the below jar file by giving pair either BTC or BNB as command line argument
   
           java -jar binance-influxdb\target\binance-influxdb-1.0.0.jar BTC 
           
   3. Goto influx client and run the below query to verify the database update   
   
           select count(*) from "stock_retention"."orderbook";
         
   4. Open Grafana and check the real time visualization


   **Note: Refer visualization screenshot added inside docs folder.**



   
 
