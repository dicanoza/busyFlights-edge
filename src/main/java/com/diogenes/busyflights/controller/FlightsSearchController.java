package com.diogenes.busyflights.controller;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diogenes.busyflights.model.Flight;
import com.diogenes.busyflights.service.FlightSearchService;

@RestController
public class FlightsSearchController {

	@Autowired
	private FlightSearchService flightSearchService;

	@RequestMapping(value = "/v1/search", produces = { APPLICATION_JSON_UTF8_VALUE })
	@Validated
	public List<Flight> search(@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value = "destination", required = false) String destination,
			@RequestParam(value = "departureDate", required = false) @DateTimeFormat(iso = DATE) LocalDate departureDate,
			@RequestParam(value = "returnDate", required = false) @DateTimeFormat(iso = DATE) LocalDate returnDate,
			@RequestParam(value = "numberOfPassengers", required = false) Integer numberOfPassengers) {

		return flightSearchService.searchFlights(origin, destination, departureDate, returnDate, numberOfPassengers);
	}
}
