
# lagom-sbt-poc

Building Reactive Java 8 application with Lagom framework. This is a classic CRUD application which persist events in Cassandra Db. 
Here we are using external Cassandra to persist events and external kafka for publishing and subscribing between microservices.

## Prerequisites
1. Java 1.8
2. SBT 0.13.12
3. Cassandra 3.9
4. Kafka 0.10.1.1


## Getting the Project
#### To clone the project follow the command
`git clone git@github.com:knoldus/lagom-sbt-poc.git`

### Starting Cassandra on local machine
`cassandra/bin> ./cassandra`

### Starting kafka server and zookeeper on local machine
`> bin/zookeeper-server-start.sh config/zookeeper.properties`
<br>
 `> bin/kafka-server-start.sh config/server.properties`
 
#### Cassandra port is configured to be `9042` by default.
#### Zookeeper port is configured to be `2181` by default.
#### Kafka server port is configured to be `9092` by default.

#### Command to start the project
`./activator runAll`

#### Application runs on port 9000 by default  `http://localhost:9000`

## Json Formats for different Rest services are mentioned below :

#### 1. Create User:

Route(Method - POST) : `localhost:9000/api/user`

Rawdata(json): 
    {
	"id": "1",
	"name": "User 1",
	"age": 24
    }


#### 2. Update User:

Route(Method - PUT) : `localhost:9000/api/user`

Rawdata(json): 
    {
	"id": "1",
	"name": "User 1",
	"age": 30
    }
    

#### 3. Delete User:

Route(Method - DELETE) : `localhost:9000/api/user/:id`
    

#### 4. Get User details:

Route(Method - GET) : `localhost:9000/api/user/:id`

### Publish message to kafka topic `greetings`
`curl -H "Content-Type: application/json" -X POST -d '{"message":"Hi"}' http://localhost:9000/api/hello/Bob`

### Consume message from kafka topic
The application subscribes to the topic `greetings` and dump out messages to standard output.
