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

## Notes and Improvements
- To make the endpoint better I could create a global excepton handler with @ControllerAdvice and give to the user a better error response.
- For the client to make the http request could also be an interface so as future implementation can use another one.
- Make possible to extract all root boxes instead only the first one.