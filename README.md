# datamicroservices-example

An example of Data Microservices using Brazillian Government Data Sources.

This application uses Spring Cloud Stream to read data from two Sources, and then aggregate that data in an Procesor.

After aggregation, a Sink is activated for logging. And other Processor to convert municipality ids from IBGE to municipality names reading it from other resource

#### Running Kafka docker

Run Kafka as message broker for communication between components.

```sh
$ docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka
```

#### Build Sources

```sh
$ mvn -f sources/pom.xml clean install -DskipTests
```

(Skips tests because it has not mocked api's access yet)

#### Run Sources

```sh
$ java -jar sources/target/sources-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.output.destination=mecsource-input \
--server.port=8091 \
--spring.cloud.stream.bindings.output.content-type=application/json;charset=UTF-8
```

#### Build Api Aggregator

```sh
$ mvn -f api-aggregator/pom.xml clean install
```

#### Run Api Aggregator

```sh
$ java -jar api-aggregator/target/api-aggregator-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.input.destination=mecsource-input \
--spring.cloud.stream.bindings.output.destination=apiagregator-output \
--server.port=8092 \
--spring.cloud.stream.bindings.output.content-type=application/json;charset=UTF-8 \
--spring.cloud.stream.bindings.input.content-type=application/json;charset=UTF-8
```

#### Build Ibge Municipality Handler

```sh
$ mvn -f ibge-municipality-handler/pom.xml clean install
```

#### Run Ibge Municipality Handler

```sh
$ java -jar ibge-municipality-handler/target/ibge-municipality-handler-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.input.destination=apiagregator-output \
--spring.cloud.stream.bindings.output.destination=municipality-output \
--server.port=8093 \
--spring.cloud.stream.bindings.output.content-type=application/json;charset=UTF-8 \
--spring.cloud.stream.bindings.input.content-type=application/json;charset=UTF-8
```

#### Build Log Sink

```sh
$ mvn -f log-sink/pom.xml clean install
```

#### Run Log Sinks

```sh
$ java -jar log-sink/target/log-sink-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.input.destination=mecsource-input \
--server.port=8094 \
--spring.cloud.stream.bindings.input.content-type=application/json;charset=UTF-8
```

```sh
$ java -jar log-sink/target/log-sink-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.input.destination=apiagregator-output \
--server.port=8095 \
--spring.cloud.stream.bindings.input.content-type=application/json;charset=UTF-8
```

```sh
$ java -jar log-sink/target/log-sink-1.0-SNAPSHOT.jar \
--spring.cloud.stream.bindings.input.destination=municipality-output \
--server.port=8096 \
--spring.cloud.stream.bindings.input.content-type=application/json;charset=UTF-8
```

