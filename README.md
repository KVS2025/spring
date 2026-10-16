# Movie Booking — Spring Demo

A small Spring Boot web app for exploring dependency injection, inversion of control (IoC), bean lifecycle callbacks, and singleton beans. Its browser dashboard lets you check a movie-show timer, look up sample movie ratings, book seats, and inspect the Spring-managed beans.

## Features

- **Bean lifecycle:** Spring starts the show when the `ShowTimer` bean is created and stops it when the application shuts down.
- **Constructor injection:** `MovieInfoService` receives its `RatingProvider`; `BookingManager` receives `UserService` and `SeatService`.
- **IoC and singleton scope:** The `/api/beans` endpoint displays selected beans and their object IDs so you can compare them across requests.
- **Movie ratings:** Sample ratings are available for Avengers, 3 Idiots, Jawan, and Kalki.
- **Seat booking:** Book seats and see the seats booked during the current application run.
- **Browser dashboard:** A responsive page is served from `/` and calls the JSON API.

## Requirements

- Java 11 or later
- Maven 3.6 or later

The project uses Spring Boot 2.7.18 and the Spring Web starter. It does not require a database or additional services.

## Run the application

From the repository root, enter the project directory:

```bash
cd spring-lab7-web
```

Start the app with Maven:

```bash
mvn spring-boot:run
```

Or build and run the executable JAR:

```bash
mvn clean package
java -jar target/spring-lab7-web-1.0.jar
```

Open [http://localhost:8080](http://localhost:8080) to use the dashboard. The server listens on port `8080` by default; change `server.port` in `src/main/resources/application.properties` to use another port.

## API

All endpoints return JSON and are available under `/api`.

| Method | Endpoint | Parameters | Description |
| --- | --- | --- | --- |
| `GET` | `/api/timer/status` | — | Get the show status and ticket totals. |
| `POST` | `/api/timer/sell` | `tickets` (integer, at least 1) | Sell tickets and return the updated totals. |
| `GET` | `/api/movies/rating` | `movie` | Look up a sample rating. Unknown titles return `Not available`. |
| `POST` | `/api/bookings` | `name`, `movie`, `seat` | Register the user and book a seat. |
| `GET` | `/api/beans` | — | List selected Spring beans, their classes, and object IDs. |

For example, once the app is running, these URLs can be opened in a browser:

- `http://localhost:8080/api/timer/status`
- `http://localhost:8080/api/movies/rating?movie=Avengers`
- `http://localhost:8080/api/beans`

The booking endpoint is a `POST` and accepts its parameters as query parameters. A seat cannot be booked twice for the same movie; a duplicate booking returns HTTP `409 Conflict`. Missing text parameters or an invalid ticket count return HTTP `400 Bad Request`.

## Project layout

```text
src/main/java/com/example/
├── booking/     # BookingManager and SeatService
├── config/      # Explicit Spring bean configuration
├── event/       # UserService
├── movie/       # MovieInfoService and RatingProvider
├── timer/       # ShowTimer lifecycle and ticket counts
└── web/         # REST API controller

src/main/resources/
├── application.properties
└── static/index.html   # Browser dashboard
```

## Notes

This is an educational demo, not a production booking system. Ratings, ticket counts, and booked seats are held in memory and reset when the application restarts. There is no database, authentication, or payment processing.
