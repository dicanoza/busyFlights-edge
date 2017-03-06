package com.diogenes.busyflights.service;

import static java.lang.String.valueOf;

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

import com.diogenes.busyflights.model.Flight;
import com.diogenes.busyflights.model.ToughJetFlight;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class ToughJetIntegrationService extends FlightSearchService {

	private static final Logger logger = LoggerFactory.getLogger(ToughJetIntegrationService.class);
	
	@Value("${ToughJet.url}")
	private String url;
	private ObjectMapper mapper;

	public ToughJetIntegrationService() {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

	}

	@Override
	public List<Flight> searchFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate,
			Integer numberOfPassengers) {

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		urlParameters.add(new BasicNameValuePair("from", origin));
		urlParameters.add(new BasicNameValuePair("to", destination));
		urlParameters.add(new BasicNameValuePair("numberOfAdults", valueOf(numberOfPassengers)));

		if (departureDate != null) {
			urlParameters.add(new BasicNameValuePair("departureDay", valueOf(departureDate.getDayOfMonth())));
			urlParameters.add(new BasicNameValuePair("departureMonth", valueOf(departureDate.getMonthValue())));
			urlParameters.add(new BasicNameValuePair("departureYear", valueOf(departureDate.getYear())));
		}
		if (returnDate != null) {
			urlParameters.add(new BasicNameValuePair("returnDay", valueOf(returnDate.getDayOfMonth())));
			urlParameters.add(new BasicNameValuePair("returnMonth", valueOf(returnDate.getMonthValue())));
			urlParameters.add(new BasicNameValuePair("dreturnYear", valueOf(returnDate.getYear())));
		}

		List<Flight> resultList = new ArrayList<Flight>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		try {
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				List<ToughJetFlight> flightList = mapper.readValue(response.getEntity().getContent(),
						TypeFactory.defaultInstance().constructCollectionType(List.class, ToughJetFlight.class));
				for (ToughJetFlight flight : flightList) {
					resultList.add(flight.flight());
				}

			}

		} catch (IOException e) {
			logger.warn("Error calling " + url, e);
		}

		return resultList;
	}
}