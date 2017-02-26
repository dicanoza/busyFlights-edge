package com.diogenes.busyflights.model;

import java.util.Comparator;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ComparisonChain;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {

	private String airline;
	private Date arrivalDate;

	private String departureAirportCode;
	private Date departureDate;
	private String destinationAirportCode;
	private Double fare;
	private Suplier suplier;

	public Flight airline(String airline) {
		this.airline = airline;
		return this;
	}

	public Flight arrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
		return this;
	}

	public Flight departureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
		return this;
	}

	public Flight departureDate(Date departureDate) {
		this.departureDate = departureDate;
		return this;
	}

	public Flight destinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
		return this;
	}

	public Flight fare(Double fare) {
		this.fare = fare;
		return this;
	}

	public Flight suplier(Suplier suplier) {
		this.suplier = suplier;
		return this;
	}

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

	public Double getFare() {
		return fare;
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

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public static FlightFareComparator flightFareComparator() {
		return new FlightFareComparator();
	}

	public String getSuplier() {
		return suplier.toString();
	}

	public void setSuplier(String suplier) {
		this.suplier = Suplier.valueOf(suplier);
	}

	private static class FlightFareComparator implements Comparator<Flight> {

		@Override
		public int compare(Flight o1, Flight o2) {
			return ComparisonChain.start().compare(o1.fare, o2.fare).result();
		}
	}

	enum Suplier {
		CrazyAir, ToughJet
	}

}
