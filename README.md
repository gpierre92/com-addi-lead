# com-addi-leads

Prototype project that incorporates the layers suggested by the Clean Architecture pattern for the microservices to be built. These layers are the following:

* Entity: Contains all the entities, such as POJOs, that participate in the domain (business) of the microservice, these entities are isolated from the technology that persists them in some repository or that are shared to another external application.
* Domain: Defines the interfaces and implements the services that handle the business logic of the microservice, for this it requires the entity layer because these are manipulated (processed) within the business logic. In addition to this, the interfaces of the adapters that will be required within the business logic are also defined, such as, for example, database repositories, HTTP service clients, event generators or others of the same type. It is emphasized that this layer does not have any technical dependencies to deal with the adapters that are defined.
* Adapter: Implements the adapter interfaces defined in the domain layer (business). These implementations are the ones that contain the technical logic to, for example, make the connection with the different data repositories required by the application, with the different third-party application services, with the event bus or others of the same type. For this, this layer requires the definition made in the business layer.
* Application: Exposes the services of the business logic through various endpoints required by the front end to carry out the business process. Likewise, it is in charge of instantiating what is defined in the adapter and domain layers, since the microservice starts from this layer.

## Starting

Follow the instructions below to start the development of this project.

### Pre requirements

Plugins that must be installed in your IDE:
* [Lombok](http://projectlombok.org/) - *Bytecode library that automatically generates Getters and Setters*.
* [CheckStyle](http://www.checkstyle.com/) - *Plugin to be able to check the code style using Google rules*

---

Have installed, or configured in the IDE, Java version 1.8 SE as a minimum, version 3 of Maven

---

### Compilation and execution of the project

To compile run the command in the main project directory:

mvn clean install

After that, access the payment-application subdirectory and execute the following command:

mvn spring-boot:run

Note: Consider that to run the application you already have integration with kafka and third-party services running to consume the apis



## Testing execution

For the execution of `unit tests`, the following must be executed:

```
mvn test
```

For the execution of `integration tests`, the following must be executed:

```
mvn verify -Pfailsafe
```

### Integration tests:
LeadRestControllerIT.java

### Data for IT locate in:
MockServer.java

## Assumptions or improvements

```
Improvements
```

* Implement circuit breaker pattern in API clients like JudicialRecordClient, LeadsClient, 
NationalRegistryClient and VerifyScoreClient.


* Implement the integration with kafka to raise an event when the evaluation of leads completes successfully,
to continue processing in another microservice now as a lead.


* Document the API using Swagger 2


* Complement checkstyle with findbugs

```
Assumptions
```

* Some Unit test empty for demos API