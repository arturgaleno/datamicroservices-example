# datamicroservices-example

An example of Data Microservices using Brazillian Government Data Sources

##Running Kafka docker
$ docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka

##Build Sources
mvn -f sources/pom.xml clean install

##Run Sources
java -jar sources/target/sources-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.output.destination=mecsource-input \
--server.port=8091 \
--spring.cloud.stream.bindings.output.content-type=application/json;charset=UTF-8

##Build Processors
mvn -f processors/pom.xml clean install

##Run Processors
java -jar processors/target/processors-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.input.destination=mecsource-input \
--spring.cloud.stream.bindings.output.destination=apiagregator-output \
--server.port=8092 \
--spring.cloud.stream.bindings.output.content-type=application/json;charset=UTF-8 \
--spring.cloud.stream.bindings.input.content-type=application/json;charset=UTF-8

##Build Sinks
mvn -f sinks/pom.xml clean install

##Run Sinks
java -jar sinks/target/sinks-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.input.destination=apiagregator-output \
--server.port=8093 \
--spring.cloud.stream.bindings.input.content-type=application/json;charset=UTF-8


