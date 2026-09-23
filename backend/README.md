# Spring Boot feature backend

Run `mvn verify` from this directory to build the subsystem JARs and the sole
deployable `application-war` executable WAR. Start the WAR module with
`mvn -pl application-war spring-boot:run`.

Each folder under `modules/` is an independently owned subsystem JAR. A module
may declare another module as a normal Maven dependency and use its public
implementation directly. Add dependencies only in the direction the feature
flow requires and avoid cycles. `application-war` composes all feature modules
and shared application policy into the deployable WAR.
