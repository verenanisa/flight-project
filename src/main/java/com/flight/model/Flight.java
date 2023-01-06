package com.flight.model;

import jakarta.persistence.*;

@Entity
public class Flight {
	@Id
	private int flightNo;
	private String flightName;
	private String source;
	private String destination;
	private double ticketPrice;

	public Flight(int flightNo, String flightName, String source, String destination, double ticketPrice) {
		super();
		this.flightNo = flightNo;
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
		this.ticketPrice = ticketPrice;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Flight() {
		super();
	}

	@Override
	public String toString() {
		return "Flight [flightNo=" + flightNo + ", flightName=" + flightName + ", source=" + source + ", destination="
				+ destination + ", ticketPrice=" + ticketPrice + "]";
	}

}
