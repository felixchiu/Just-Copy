# Just Copy

## Purpose   

Provide a highly scalable and configurable data processing service to provide basic ETL process 

## Configuration

### Pre-Target SQL File (Optional)

The SQL we want to run __BEFORE__ we pull data

### Source SQL File

The SQL file which defines what data we are going to pull

### Target SQL File

An insert statement to load data into target table. The first column should be the unique key for this run

### Post-Target SQL File (Optional)

The SQL we want to run __AFTER__ we loaded data

### Column Names

The column names that we are pulling from source SQL query, that we want to insert into target table. The columns must ne in sequence to the target SQL. Again, the first column should be the unique key of this batch.   

## Limitation

We only support STRING (or TEXT) at this moment. 

## Diagram

![Sequence Diagram](http://www.plantuml.com/plantuml/png/VL6xJiGm4Epz5QDhg0Bz5JI7aA122HArusmE2yVhMziW_XxdWHHE2LlExipixjpOZlHjq9AiTZNvZaIfP9CZFEUOSEBmZJUMZzxnbrA75s53CQ8X-RI63gZkKK2gNhMSAM7f7t14dJr2wQIv4ozERKvMiyILxX5DDXDqXqTjNHRQSMyuYo4ywAJNzD4-pgt9UdDiv2_xNFERt0useHlXWJjowYoS0vvy94ceoR1q2gKSSq3F0jBc7LB-XAMsjKVJ4Mbrd9p-AfOCr0xllsif1lOTV1vQ4d2F7Chg5FSEttdwh1C9XeVWA5dsUw5pJBzPhWH6kCG-4TJsq9xtQROaX9N63m00)
