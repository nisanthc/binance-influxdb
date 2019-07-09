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

Using Grafana GUI

   1. Add data source as InfluxDB and give necessary information to configure
   
   2. Create a variable. This is used to create dynamic dashboard based on the variable value.
   
      1. Go the Dashboard setting and select variable menu
      
      2. Create a new vaiable
           Name : PAIRS,
           Type : Query,
           DataSource : InfluxDB,
           Query : show tag values from orderbook with key = "symbol"
         
   3. Create a dashboard 
   
        1. Choose Table template 
        
        2. Queries : Add Query for BIDS table

          SELECT "price", "quantity", "price"*"quantity"  as Total FROM "stock_retention"."orderbook" WHERE ("category" = 'BIDS' and  "symbol" = '$PAIRS') AND $timeFilter

        3. Visualization: Format the table
        
        4. General: Give panel name
        
        5. Save the Dashboard
        
   4. Repeat the above step #2 by adding new panel in the same dashboard. And in the Queries section add the below query for ASKS
          
          SELECT "price", "quantity", "price"*"quantity"  as Total FROM "stock_retention"."orderbook" WHERE ("category" = 'ASKS' and  "symbol" = '$PAIRS') AND $timeFilter
  
## Start Project

   1. Download or clone the project
   
   2. Run the below jar file by giving pair either BTC or BNB as command line argument
   
           java -jar binance-influxdb\target\binance-influxdb-1.0.0.jar BTC 
           
   3. Goto influx client and run the below query to verify the database update   
   
          show measurements;
         
   4. Open Grafana and check the real time visualization.


   **Note: Refer visualization screenshot added inside docs folder.**



   
 
