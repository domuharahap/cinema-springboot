### Cinemas-spring-boot

This Cinemas-spring-boot repository is a simple microservices java Spring Boot. The cinemas architecture consist of following modules:
* Movies
* Showtimes
* Bookings
* api-gateway

### How to run

After clone the project, navigate to each modules (movies/showtimes etc) to build project and dokcer images for each of the modules. run below script to build project and docker images:

`$ cd movies`

`$ mvn clean install -Dmaven.test.skip=true`

`$ Docker build -t movies .`

### Docker compose

Run all containers docker application with compose. docker-compose.yml file can find and root projects

`$ cd ..`

`$ docker-compose up -d`

### Access your applications

Add Movies:

`curl --location --request POST 'localhost:8080/api/movies' \
--header 'Content-Type: application/json' \
--data-raw '{
	"title": "The Way Back",
	"genre": "Drama, Sport",
	"description": "Movie desk",
	"rating":6.8,
	"director": "Gavin O'\''Connor",
	"icon": "https://m.media-url.icon.jpg",
    "duration": 110.0
}'`

Add showtimes:

`curl --location --request POST 'localhost:8080/api/showtime' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '{
    "movieId": 4,
    "startTime": "2020-04-26 13:00:00",
    "price": 15
}'`

Add Bookings:

`curl --location --request POST 'localhost:8080/api/booking?noOfTickets=1' \
--header 'Content-Type: application/json' \
--data-raw '{
	"movieId":1,
	"showtimeId":1,
	"bookingNo":"",
	"username": "test",
    "createDate": "2020-04-21T13:13:00.000+0000"
}'`

### Postman Result sample
