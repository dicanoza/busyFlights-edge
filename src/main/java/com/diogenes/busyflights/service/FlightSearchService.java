package com.diogenes.busyflights.service;

import static com.diogenes.busyflights.model.Flight.flightFareComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogenes.busyflights.model.Flight;

@Service
public class FlightSearchService {

	@Autowired
	private CrazyAirIntegrationService crazyAirIntegrationService;

	@Autowired
	private ToughJetIntegrationService toughJetIntegrationService;

	/**
	 * Looks for Flights at ThoughJet and CrazyAir services through http call
	 * 
	 * @param origin
	 * @param destination
	 * @param departureDate
	 * @param returnDate
	 * @param numberOfPassengers
	 * @return {@link List} of {@link Flight}
	 */
	public List<Flight> searchFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate,
			Integer numberOfPassengers) {

		List<Flight> resultList = new ArrayList<Flight>();

		// Get flights from all the companies
		resultList.addAll(crazyAirIntegrationService.searchFlights(origin, destination, departureDate, returnDate,
				numberOfPassengers));
		resultList.addAll(toughJetIntegrationService.searchFlights(origin, destination, departureDate, returnDate,
				numberOfPassengers));

		// Sort the results by fare
		Collections.sort(resultList, flightFareComparator());

		return resultList;
	}

}
