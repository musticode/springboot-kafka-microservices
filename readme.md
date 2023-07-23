### Kafka

#### Properties file
```
spring.kafka.topic.name=order_topics

spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=stock
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
```
topic name and group-id can be different for project



#### Container
```
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.0.1
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
  broker:
    image: confluentinc/cp-kafka:7.0.1
    container_name: broker
    ports:
      - "9092:9092"
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_INTERNAL://broker:29092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
```

run container:
```
docker-compose up -d
```

go to container:
```
docker ps
```

create a topic
```
docker exec broker \                                                                                                                                                ✔  17:21:10
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic exampletopic
```

## Project Demo

Curl commands of project:

```
curl --location 'http://localhost:8080/api/v1/orders' \
--header 'Content-Type: application/json' \
--data '{
  
  "name": "aaa",
  "qty": 1,
  "price": 1.0
}'
```

response
```
Order placed successfully...
```

<br>Kafka Zoekeeper<br>

Kafka kurulum:<br>
https://blog.burakkutbay.com/docker-ile-apache-kafka-kurulumu-ve-kullanimi.html/


https://www.javaguides.net/2022/07/event-driven-microservices-using-spring-boot-and-apache-kafka.html



https://medium.com/@marcelo.hossomi/running-kafka-in-docker-machine-64d1501d6f0b

https://fatmadelenn.medium.com/apache-kafka-ve-spring-boot-d72a3c247167

https://www.javaguides.net/p/apache-kafka-tutorial.html


https://varneanand-88.medium.com/how-to-set-up-kafka-kafka-connect-zookeeper-using-docker-compose-f931300a6093