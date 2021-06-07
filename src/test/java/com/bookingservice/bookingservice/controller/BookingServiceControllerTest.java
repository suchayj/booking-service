package com.bookingservice.bookingservice.controller;

import com.bookingservice.bookingservice.model.Available;
import com.bookingservice.bookingservice.model.AvailableSpace;
import com.bookingservice.bookingservice.model.Booking;
import com.bookingservice.bookingservice.model.BookingRef;
import com.bookingservice.bookingservice.model.ContainerTypeEnum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(BookingServiceController.class)
public class BookingServiceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private static final String baseUrl = "http://localhost:8080/api/bookings";

    Booking newBooking = new Booking(20, ContainerTypeEnum.REEFER, "Southampton", "Singapore", 4);

    @Test
    void storeBooking() {

        var result = webTestClient.post().uri(baseUrl).body(Mono.just(newBooking).log(), Booking.class)
                .header("content-type", "application/json").header("accept", "application/json").exchange()
                .expectStatus().isEqualTo(HttpStatus.OK).returnResult(BookingRef.class);

        System.out.println("result " + result);
    }

    @Test
    void storeBookingBodyTest() {

        var result = webTestClient.post().uri(baseUrl).header("content-type", "application/json")
                .header("accept", "application/json").body(Mono.just(newBooking).log(), Booking.class).exchange()
                .expectStatus().isOk().expectBody().jsonPath("$.bookingRef").isNotEmpty().jsonPath("$.origin")
                .isEqualTo("Southampton");

        // .jsonPath("$.origin").isEqualTo("Southampton").jsonPath("$.destination").isEqualTo("Singapore")
        // .jsonPath("$.ContainerType").isEqualTo("4");
        // .returnResult(BookingRef.class);

        System.out.println("result " + result);
    }

    @Test
    void storeBookingWith400Exception() {

        var result = webTestClient.post().uri(baseUrl).body(Mono.just(newBooking).log(), Booking.class)
                .header("content-type", "application/json").header("accept", "application/json").exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST).returnResult(BookingRef.class);

        System.out.println("result " + result);
    }

    @Test
    void storeBookingWith500Exception() {

        var result = webTestClient.post().uri(baseUrl).body(Mono.just(newBooking).log(), Booking.class)
                .header("content-type", "application/json").header("accept", "application/json").exchange()
                .expectStatus().isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR).returnResult(BookingRef.class);

        System.out.println("result " + result);
    }

    @Test
    void checkAvailable() {

        var result = webTestClient.post().uri(baseUrl + "/checkAvailable")
                .body(Mono.just(newBooking).log(), Booking.class).header("content-type", "application/json")
                .header("accept", "application/json").exchange().expectStatus().isEqualTo(HttpStatus.OK)
                .returnResult(AvailableSpace.class);

        System.out.println("result " + result);

    }

    @Test
    void checkAvailableWith400Exception() {

        var result = webTestClient.post().uri(baseUrl + "/checkAvailable")
                .body(Mono.just(newBooking).log(), Booking.class).header("content-type", "application/json")
                .header("accept", "application/json").exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .returnResult(Available.class);

        System.out.println("result " + result);

    }

    @Test
    void checkAvailableWith500Exception() {

        var result = webTestClient.post().uri(baseUrl + "/checkAvailable")
                .body(Mono.just(newBooking).log(), Booking.class).header("content-type", "application/json")
                .header("accept", "application/json").exchange().expectStatus()
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR).returnResult(Available.class);

        System.out.println("result " + result);

    }
}
