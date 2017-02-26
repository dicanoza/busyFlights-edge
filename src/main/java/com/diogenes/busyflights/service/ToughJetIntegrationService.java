package com.diogenes.busyflights.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.diogenes.busyflights.model.Flight;

@Service
public class ToughJetIntegrationService extends FlightSearchService {

	@Override
	public List<Flight> searchFlights(String origin, String destination, Date departureDate, Date returnDate,
			Integer numberOfPassengers) {
		// TODO
		return new ArrayList<Flight>();
	}
}