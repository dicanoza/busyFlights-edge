package com.diogenes.busyflights.integrated;

import static com.diogenes.busyflights.util.DatePatterMatcher.matchesDatePattern;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.diogenes.busyflights.controller.FlightsSearchController;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightsSearchController.class)
public class RestTest {
	private static final String DATE_TIME_ISO_RETURN = "yyyy-MM-dd'T'HH:mm:ss";

	private static final int crazyAirPort = 8181;

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(crazyAirPort);

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testCrazyAirReturn200() throws Exception {
		stubFor(get(urlMatching("/crazyair/search(.*)")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("/searchTest/crazyAirMockReturn.json")));
		stubFor(get(urlMatching("/toughjet/search(.*)"))
				.willReturn(aResponse().withStatus(404).withHeader("Content-Type", "application/json")));

		mvc.perform(get("/v1/search").param("departureDate", "2017-02-05")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].fare", is(150.29)))
				.andExpect(jsonPath("$[0].departureAirportCode", is("LHR")))
				.andExpect(jsonPath("$[0].destinationAirportCode", is("LAX")))
				.andExpect(jsonPath("$[0].suplier", is("CrazyAir")))
				.andExpect(jsonPath("$[0].departureDate",
						is(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.of(2017, 2, 5, 12, 24, 39)))))
				.andExpect(jsonPath("$[0].arrivalDate", matchesDatePattern(DATE_TIME_ISO_RETURN)))
				.andExpect(jsonPath("$[0].arrivalDate",
						is(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.of(2017, 3, 30, 15, 24, 39)))));
	}

	@Test
	public void testToughJetReturn200() throws Exception {
		stubFor(get(urlMatching("/crazyair/search(.*)")).willReturn(aResponse().withStatus(404)));
		stubFor(get(urlMatching("/toughjet/search(.*)")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("/searchTest/toughJetMockReturn.json")));

		mvc.perform(get("/v1/search").param("departureDate", "2017-02-05")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].fare", is(99.0))).andExpect(jsonPath("$[0].departureAirportCode", is("GRU")))
				.andExpect(jsonPath("$[0].destinationAirportCode", is("LAX")))
				.andExpect(jsonPath("$[0].suplier", is("ToughJet")))
				.andExpect(jsonPath("$[0].departureDate", matchesDatePattern(DATE_TIME_ISO_RETURN)))
				.andExpect(jsonPath("$[0].departureDate",
						is(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.of(2017, 2, 5, 0, 0, 0)))))
				.andExpect(jsonPath("$[0].arrivalDate", matchesDatePattern(DATE_TIME_ISO_RETURN)))
				.andExpect(jsonPath("$[0].arrivalDate",
						is(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.of(2017, 2, 10, 0, 0, 0)))));
	}

	@Test
	public void testCrazyAirReturnEmpty() throws Exception {
		stubFor(get(urlMatching("/crazyair/search(.*)"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("[]")));
		stubFor(get(urlMatching("/toughjet/search(.*)"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("[]")));

		mvc.perform(get("/v1/search").param("departureDate", "2015-12-24")).andExpect(status().isOk());
	}

	@Test
	public void testMalformedDates() throws Exception {
		stubFor(get(urlMatching("/toughjet/search(.*)")).willReturn(aResponse().withStatus(404)));
		stubFor(get(urlMatching("/crazyair/search(.*)")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("/searchTest/crazyAirMockReturn.json")));

		mvc.perform(get("/v1/search").param("departureDate", "2015-13-32")).andExpect(status().is(400));
		mvc.perform(get("/v1/search").param("returnDate", "2015-13-32")).andExpect(status().is(400));
	}

	@Test
	public void testReturnedJson() throws Exception {
		stubFor(get(urlMatching("/toughjet/search(.*)")).willReturn(aResponse().withStatus(404)));
		stubFor(get(urlMatching("/crazyair/search(.*)")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("/searchTest/crazyAirMockReturn.json")));

		mvc.perform(get("/v1/search")).andDo(print()).andExpect(status().is(200)).andDo(print())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[1].fare", is(153.29)))
				.andExpect(jsonPath("$[1].arrivalDate", matchesDatePattern(DATE_TIME_ISO_RETURN)));
	}

}
