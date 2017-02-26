package com.diogenes.busyflights.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diogenes.busyflights.model.Flight;
import com.diogenes.busyflights.service.CrazyAirIntegrationService;
import com.diogenes.busyflights.service.FlightSearchService;

@RestController
public class FlightsSearchController {

	@Autowired
	private FlightSearchService flightSearchService;
	@Autowired
	private CrazyAirIntegrationService crazyAirIntegrationService;

	@RequestMapping(value = "/v1/search", produces = { APPLICATION_JSON_UTF8_VALUE })
	@Validated
	public List<? extends Flight> search(@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value = "destination", required = false) String destination,
			@RequestParam(value = "departureDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") Date departureDate,
			@RequestParam(value = "returnDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") Date returnDate,
			@RequestParam(value = "numberOfPassengers", required = false) Integer numberOfPassengers) {

		return flightSearchService.searchFlights(origin, destination, departureDate, returnDate, numberOfPassengers);

	}

}
