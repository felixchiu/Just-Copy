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