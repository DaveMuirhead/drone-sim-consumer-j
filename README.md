# Drone Sim Consumer (Java)


## Running

### Override config/profile at runtime
```
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
./mvnw spring-boot:run -Dspring-boot.run.arguments="--drone.mqtt.broker-urls[0]=tcp://localhost:1883"
```

### JVM args (e.g. remote debug)
```
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

### Skip tests on a rebuild
```
./mvnw spring-boot:run -DskipTests
```

### Production
```
./mvnw clean package
java -jar target/drone-sim-consumer-j-0.0.1-SNAPSHOT.jar
```

## Application Health

- Is the JVM process alive?
```
jps -l | grep drone-sim-consumer-j
```

- What is it listening on?
```
lsof -iTCP -sTCP:LISTEN -P | grep java
```

- Health endpoint (needs spring-boot-starter-actuator)
```
curl -s localhost:8080/actuator/health
```

