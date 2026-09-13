# Drone Sim Consumer (Java)


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

