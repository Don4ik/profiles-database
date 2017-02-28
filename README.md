**Candidates Profile Database Application**

**Overview**

This application can store/retrieve/filter profiles via rest endpoint.

**Usage**

Run spring boot application: `mvn spring-boot:run

Now application is up and running and you can add/retrieve profiles via REST endpoint `http://localhost:8080/` - POST to add, GET to retrieve
(standard local deployment)

To search using skill names, use GET request to url like that: `http://localhost:8080?skills=java&skills=python` (You can put only one skill or all of those that are in Skills enum)