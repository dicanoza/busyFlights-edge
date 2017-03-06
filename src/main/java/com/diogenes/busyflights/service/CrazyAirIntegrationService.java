package com.diogenes.busyflights.service;

import static java.lang.String.valueOf;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diogenes.busyflights.model.CrazyAirFlight;
import com.diogenes.busyflights.model.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Service
public class CrazyAirIntegrationService extends FlightSearchService {

	private static final Logger logger = LoggerFactory.getLogger(CrazyAirIntegrationService.class);
	private static final String CRAZY_AIR_FORMAT = "MM-dd-yyyy";

	@Value("${CrasyFlights.url}")
	private String url;
	private ObjectMapper mapper;

	public CrazyAirIntegrationService() {
		mapper = new ObjectMapper();
		mapper.findAndRegisterModules();

	}

	/**
	 * Looks for Flights at CrazyAir service through http call
	 * 
	 * @param origin
	 * @param destination
	 * @param departureDate
	 * @param returnDate
	 * @param numberOfPassengers
	 * @return {@link List} of {@link Flight}
	 */
	@Override
	public List<Flight> searchFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate,
			Integer numberOfPassengers) {

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		urlParameters.add(new BasicNameValuePair("origin", origin));
		urlParameters.add(new BasicNameValuePair("destination", destination));
		urlParameters.add(new BasicNameValuePair("numberOfPassengers", valueOf(numberOfPassengers)));

		if (departureDate != null) {
			urlParameters
					.add(new BasicNameValuePair("departureDate", departureDate.format(ofPattern(CRAZY_AIR_FORMAT))));
		}
		if (returnDate != null) {
			urlParameters.add(new BasicNameValuePair("returnDate", returnDate.format(ofPattern(CRAZY_AIR_FORMAT))));
		}

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		List<Flight> resultList = new ArrayList<Flight>();
		try {
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				List<CrazyAirFlight> flightList = mapper.readValue(response.getEntity().getContent(),
						TypeFactory.defaultInstance().constructCollectionType(List.class, CrazyAirFlight.class));
				for (CrazyAirFlight flight : flightList) {
					resultList.add(flight.flight());
				}

			}

		} catch (IOException e) {
			logger.warn("Error calling " + url, e);
		}
		return resultList;
	}

}
