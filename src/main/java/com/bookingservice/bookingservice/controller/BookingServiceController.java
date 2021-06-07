package com.bookingservice.bookingservice.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.bookingservice.bookingservice.model.Available;
import com.bookingservice.bookingservice.model.AvailableSpace;
import com.bookingservice.bookingservice.model.Booking;
import com.bookingservice.bookingservice.model.BookingRef;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/bookings")
public class BookingServiceController {

    private static final String baseUrl = "http://localhost:8080/api/bookings";

    WebClient webClient;

    @PostConstruct
    private void init() {
        webClient = WebClient.builder().baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    /**
     * Check Available.
     */
    @PostMapping(path = "/checkAvailable", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<Available> checkAvailable(@RequestBody @Valid Booking booking) {
        var res = webClient.post().uri("/checkAvailable").body(Mono.just(booking), Booking.class).retrieve()
                .onStatus(HttpStatus::is5xxServerError, errorReponse -> {
                    return Mono.error(new Exception("Sorry there was a problem processing your request"));
                }).bodyToMono(AvailableSpace.class);

        return res.map(m -> {
            Available avail = new Available((m.getAvailableSpace() == 0) ? false : true); // As written in Task
            // Available avail = new Available((m.getAvailableSpace() > 0) ? true : false); // This may be
            return avail;
        });
    }

    /**
     * Call Booking and return BookingRef.
     */
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<BookingRef> check(@RequestBody @Valid Booking booking) {
        return webClient.post().uri("/").body(Mono.just(booking), Booking.class).retrieve()
                .onStatus(HttpStatus::is5xxServerError, errorReponse -> {
                    return Mono.error(new Exception("Sorry there was a problem processing your request"));
                }).bodyToMono(BookingRef.class);
    }

    /**
     * Get All Booking.
     * 
     * @return
     */
    @GetMapping(path = "/getAllBookings")
    public Flux<Booking> getAllBooking() {
        return webClient.get().uri("/getAllBookings").retrieve().bodyToFlux(Booking.class);
    }
}
