Application runs on port 7776

Application connects to MongoDB, the connection details are hard coded
in the application.properties, in my case it requires a locally installed
MongoDB to run. Once MongoDB is installed it requires no further configuration
as the application creates databasse and collection on the first insert.

On running we need to specify enviromental variables with name "kafkabroker" and "concurrency"
"kafkabroker" is the kafka server address, in my case its localhost:9092
"concurrency" is the number of concurrent Consumers