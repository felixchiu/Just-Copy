# Specification 

## New SQL 
* TBC

## Setup

### Add Oracle JDBC Driver for build
```mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=/Users/fchiu/Downloads/ojdbc7.jar -DgeneratePom=true```

## Reference 
* https://mvnrepository.com/artifact/com.oracle/ojdbc14/10.2.0.4.0
* http://www.baeldung.com/java-microsoft-excel
* http://www.baeldung.com/spring-data-jpa-query

## Group

* Group 1: temp.delays >= 4
* Group 2: temp.delays >= 8