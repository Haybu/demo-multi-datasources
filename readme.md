# Spring Boot with Multiple Datasources

This sample shows how to configure an application to create multiple custom datasources. 
It uses JPA (Hibernate) and enables the creation of multi-datasources
to same or different database types. 

The sample shows also how to use multiple datasources in Cloudfoundry. 

# Run Locally

Run with multi-datasources to two different H2 databases:

* clone this repo, from the project home directory build the application 
 ```bash
    $ mvn clean install
```

* run the application
```bash
    $ java -jar target/demo-multi-datasources-0.0.1-SNAPSHOT.jar
```

# Run in Cloud Foundry

Run with multi-datasources to two different MySql database services:

* clone this repo, from the project home directory build the application 
 ```bash
    $ mvn clean install
```

* login to your CF account and create two MySql services
```bash
    $ cf cs p-mysql 100mb first-db
    
    $ cf cs p-mysql 100mb second-db
``` 

* push the application to CF
```bash
    $ cf push
```

# Validate

Hit your browser with the appropriate route or localhost depending on 
where you're running it. Locally browse to:

```bash
http://localhost:8080/firstdb
or 
http://localhost:8080/seconddb
```

And notice different records. The same should be done when running in CF:

```bash
http://<app-route>/firstdb
or 
http://<app-route>/seconddb
```