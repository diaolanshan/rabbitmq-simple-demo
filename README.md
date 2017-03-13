# rabbitmq-simple-demo

Simple demo app that publishes a message to a queue and reads a message from a queue by calling a REST endpoint

There are 2 simple REST endpoints
- To publish a message use the GET endpoint /publish/{my-message-here}
- To read a message from the queue use the GET endpoint /get
