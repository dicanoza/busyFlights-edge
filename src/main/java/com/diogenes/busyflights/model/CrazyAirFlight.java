package com.diogenes.busyflights.model;

import static com.diogenes.busyflights.model.Flight.Suplier.CrazyAir;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CrazyAirFlight {

	private String airline;
	private Date arrivalDate;
	@JsonIgnore
	private Character cabinclass;
	private String departureAirportCode;
	private Date departureDate;
	private String destinationAirportCode;
	private Double price;

	public String getAirline() {
		return airline;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public void setDepartureDate(Date departureDate) {
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

	public Flight flight() {
		return new Flight().airline(airline).departureDate(departureDate).arrivalDate(arrivalDate)
				.departureAirportCode(departureAirportCode).destinationAirportCode(destinationAirportCode).fare(price)
				.suplier(CrazyAir);
	}

}
