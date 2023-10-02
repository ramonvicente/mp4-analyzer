# MP4 Analyzer

## SetUp and Run
In order to run the application with docker: 

- install docker in case you don't have it (https://www.docker.com/).
- run ```docker build -t mp4-analyzer:latest .``` to build the application
- run ```docker run -p 8080:8080 mp4-analyzer:latest``` to run the application

## Endpoint
```
curl --location 'http://localhost:8080/api/analyzes?url=https%3A%2F%2Fdemo.castlabs.com%2Ftmp%2Ftext0.mp4'
```
