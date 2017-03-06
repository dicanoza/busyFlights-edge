package com.diogenes.busyflights.model;

import static com.diogenes.busyflights.model.Flight.Suplier.THOUGH_JET;

import java.time.LocalDateTime;

public class ToughJetFlight {

	private String carrier;
	private Double basePrice;
	private Double tax;
	private Double discount;
	private Integer departureDay;
	private Integer departureMonth;
	private Integer departureYear;
	private Integer returnDay;
	private Integer returnMonth;
	private Integer returnYear;
	private String departureAirportName;
	private String arrivalAirportName;

	public Flight flight() {
		Double price = basePrice;
		if (tax != null) {
			price += tax;
		}
		if (discount != null) {
			price -= (price * discount / 100);
		}

		return new Flight().airline(carrier)
				.departureDate(LocalDateTime.of(departureYear, departureMonth, departureDay, 0, 0))
				.departureAirportCode(departureAirportName)
				.arrivalDate(LocalDateTime.of(returnYear, returnMonth, returnDay, 0, 0))
				.destinationAirportCode(arrivalAirportName).fare(price).suplier(THOUGH_JET);
	}

	/**
	 * @return the carrier
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * @param carrier
	 *            the carrier to set
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * @return the basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice
	 *            the basePrice to set
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * @return the tax
	 */
	public Double getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}

	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * @return the departureDay
	 */
	public Integer getDepartureDay() {
		return departureDay;
	}

	/**
	 * @param departureDay
	 *            the departureDay to set
	 */
	public void setDepartureDay(Integer departureDay) {
		this.departureDay = departureDay;
	}

	/**
	 * @return the departureMonth
	 */
	public Integer getDepartureMonth() {
		return departureMonth;
	}

	/**
	 * @param departureMonth
	 *            the departureMonth to set
	 */
	public void setDepartureMonth(Integer departureMonth) {
		this.departureMonth = departureMonth;
	}

	/**
	 * @return the departureYear
	 */
	public Integer getDepartureYear() {
		return departureYear;
	}

	/**
	 * @param departureYear
	 *            the departureYear to set
	 */
	public void setDepartureYear(Integer departureYear) {
		this.departureYear = departureYear;
	}

	/**
	 * @return the returnDay
	 */
	public Integer getReturnDay() {
		return returnDay;
	}

	/**
	 * @param returnDay
	 *            the returnDay to set
	 */
	public void setReturnDay(Integer returnDay) {
		this.returnDay = returnDay;
	}

	/**
	 * @return the returnMonth
	 */
	public Integer getReturnMonth() {
		return returnMonth;
	}

	/**
	 * @param returnMonth
	 *            the returnMonth to set
	 */
	public void setReturnMonth(Integer returnMonth) {
		this.returnMonth = returnMonth;
	}

	/**
	 * @return the returnYear
	 */
	public Integer getReturnYear() {
		return returnYear;
	}

	/**
	 * @param returnYear
	 *            the returnYear to set
	 */
	public void setReturnYear(Integer returnYear) {
		this.returnYear = returnYear;
	}

	/**
	 * @return the departureAirportName
	 */
	public String getDepartureAirportName() {
		return departureAirportName;
	}

	/**
	 * @param departureAirportName
	 *            the departureAirportName to set
	 */
	public void setDepartureAirportName(String departureAirportName) {
		this.departureAirportName = departureAirportName;
	}

	/**
	 * @return the arrivalAirportName
	 */
	public String getArrivalAirportName() {
		return arrivalAirportName;
	}

	/**
	 * @param arrivalAirportName
	 *            the arrivalAirportName to set
	 */
	public void setArrivalAirportName(String arrivalAirportName) {
		this.arrivalAirportName = arrivalAirportName;
	}
}
