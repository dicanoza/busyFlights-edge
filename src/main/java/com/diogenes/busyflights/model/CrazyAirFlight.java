package com.diogenes.busyflights.model;

import static com.diogenes.busyflights.model.Flight.Suplier.CRAZY_AIR;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * Represents CrazyAir returning object
 *
 */
public class CrazyAirFlight {

	private static final String CRAZYAIR_DATETIME = "MM-dd-yyyy HH:mm:ss";

	private String airline;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CRAZYAIR_DATETIME)
	private LocalDateTime arrivalDate;
	private Character cabinclass;
	private String departureAirportCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CRAZYAIR_DATETIME)
	private LocalDateTime departureDate;
	private String destinationAirportCode;
	private Double price;

	/**
	 * Builds an Fligth using the values of the current object
	 * 
	 * @return {@link Flight}
	 */
	public Flight flight() {
		return new Flight().airline(airline).departureDate(departureDate).arrivalDate(arrivalDate)
				.departureAirportCode(departureAirportCode).destinationAirportCode(destinationAirportCode).fare(price)
				.suplier(CRAZY_AIR);
	}

	public String getAirline() {
		return airline;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Character getCabinclass() {
		return cabinclass;
	}

	public void setCabinclass(Character cabinclass) {
		this.cabinclass = cabinclass;
	}

}
