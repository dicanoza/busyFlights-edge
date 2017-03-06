package com.diogenes.busyflights.model;

import java.time.LocalDateTime;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ComparisonChain;

/**
 * 
 * Represents an internal Flight, all integrations should be parsed into this
 * class
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {

	private String airline;
	private LocalDateTime arrivalDate;
	private String departureAirportCode;
	private LocalDateTime departureDate;
	private String destinationAirportCode;
	private Double fare;
	private Suplier suplier;

	// build methods

	public Flight airline(String airline) {
		this.airline = airline;
		return this;
	}

	public Flight arrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
		return this;
	}

	public Flight departureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
		return this;
	}

	public Flight departureDate(LocalDateTime departureDate) {
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

	// getters and setters
	/**
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * @param airline
	 *            the airline to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * @return the arrivalDate
	 */
	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * @param arrivalDate
	 *            the arrivalDate to set
	 */
	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/**
	 * @return the departureAirportCode
	 */
	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	/**
	 * @param departureAirportCode
	 *            the departureAirportCode to set
	 */
	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	/**
	 * @return the departureDate
	 */
	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate
	 *            the departureDate to set
	 */
	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * @return the destinationAirportCode
	 */
	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}

	/**
	 * @param destinationAirportCode
	 *            the destinationAirportCode to set
	 */
	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}

	/**
	 * @return the fare
	 */
	public Double getFare() {
		return fare;
	}

	/**
	 * @param fare
	 *            the fare to set
	 */
	public void setFare(Double fare) {
		this.fare = fare;
	}

	/**
	 * @return the suplier
	 */
	public String getSuplier() {
		return suplier.toString();
	}

	/**
	 * @param suplier
	 *            the suplier to set
	 */
	public void setSuplier(String suplier) {
		this.suplier = Suplier.valueOf(suplier);
	}

	/**
	 * 
	 * Comparator for {@link Flight#fare}
	 *
	 */
	private static class FlightFareComparator implements Comparator<Flight> {

		@Override
		public int compare(Flight o1, Flight o2) {
			return ComparisonChain.start().compare(o1.fare, o2.fare).result();
		}
	}

	public static FlightFareComparator flightFareComparator() {
		return new FlightFareComparator();
	}

	/**
	 * 
	 * Enum type of Supliers
	 *
	 */
	enum Suplier {
		CRAZY_AIR("CrazyAir"), THOUGH_JET("ToughJet");
		private String value;

		private Suplier(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

}
