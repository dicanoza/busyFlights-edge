package com.diogenes.busyflights.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.diogenes.busyflights.model.CrazyAirFlight;
import com.diogenes.busyflights.model.Flight;

@Service
public class CrazyAirIntegrationService extends FlightSearchService {

	private @Value("${CrasyFlights.url}") String url;

	@Override
	public List<Flight> searchFlights(String origin, String destination, Date departureDate, Date returnDate,
			Integer numberOfPassengers) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new LogRequestResponse());

		// adding interceptor for logging
		restTemplate.setInterceptors(interceptors);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("origin", origin)
				.queryParam("destination", destination).queryParam("departureDate", departureDate)
				.queryParam("returnDate", returnDate).queryParam("numberOfPassengers", numberOfPassengers);

		CrazyAirFlight[] crazyFlightArray = restTemplate.getForObject(builder.build().encode().toUri(),
				CrazyAirFlight[].class);
		List<Flight> resultList = new ArrayList<Flight>();
		if (crazyFlightArray != null) {
			for (CrazyAirFlight crazyFlight : crazyFlightArray) {
				resultList.add(crazyFlight.flight());
			}
		}
		return resultList;
	}

}
