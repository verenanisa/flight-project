package com.flight.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Ticket {
	private static int counter = 99;
	private String pnr = "";
	private Date travelDate;
	private Flight flight;
	private Map<Passenger, Integer> passangers = new TreeMap<Passenger, Integer>();

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(Date travelDate, Flight flight) {
		super();
		this.travelDate = travelDate;
		this.flight = flight;
		this.counter = counter + 1;

	}

	public double getTotal() {
		return calculateTotalTicketPrice();
	}

	public String generatePNR()

	{
		char sour = flight.getSource().charAt(0);
		char dest = flight.getDestination().charAt(0);
		@SuppressWarnings("deprecation")
		int y = Integer.parseInt(travelDate.toLocaleString().substring(8, 12));
		System.out.println(y + "ye substring wala year ha ");
		// int y = travelDate.getYear();
		int m = travelDate.getMonth();
		int d = travelDate.getDate();
		String datstr = y + "" + m + "" + d;

		pnr = "" + sour + "" + dest + "_" + datstr + "_" + counter;

		return pnr;

	}

	private double calcPassangerFare(Passenger passen) {
		if (passen.getAge() <= 12) {
			return flight.getTicketPrice() * 0.5;
		} else if (passen.getAge() >= 60) {
			return flight.getTicketPrice() * 0.6;
		} else {
			if ((passen.getGeneder() == 'F') && (passen.getAge() > 12) && (passen.getAge() < 60)) {
				return flight.getTicketPrice() - flight.getTicketPrice() * 0.25;
			}
			return flight.getTicketPrice();
		}

	}

	public void addPassenger(String name, int age, char gender) {

		Passenger p = new Passenger(name, age, gender);
		int fare = (int) calcPassangerFare(p);
		passangers.put(p, fare);
	}

	private double calculateTotalTicketPrice() {
		int total = 0;
		for (Integer price : passangers.values()) {
			total += price;
		}
		return (double) total;
	}

	public StringBuilder generateTicket() {
		StringBuilder tikt = new StringBuilder();

		tikt.append("PNR NUMBER : " + this.getPnr() + "\n");
		tikt.append("Flight Number : " + this.flight.getFlightNo() + "\n");
		tikt.append("Flight Name : " + this.flight.getFlightName() + "\n");
		tikt.append("Source : " + this.flight.getSource() + "\n");
		tikt.append("Destination : " + this.flight.getDestination() + "\n");
		tikt.append("Travel date : " + this.getTravelDate() + "\n");
		tikt.append("Passenger name " + "\t\t" + " Age " + "\t\t" + "      Gender " + "\t\t" + "Fare" + "\n");
		for (Passenger p : this.getPassangers().keySet()) {
			tikt.append("" + p.getName() + "\t\t\t " + p.getAge() + "\t\t\t " + p.getGeneder() + "\t\t "
					+ this.getPassangers().get(p) + "\n");

		}

		tikt.append("Total ticket Price : " + this.getTotal() + "\n");

		return tikt;
	}

	public void writeTicket() throws IOException, SQLException {

		String fileticket = generatePNR();
		BufferedWriter bwr = new BufferedWriter(
				new FileWriter(new File("C:\\Users\\rushikesh_bhattad\\Desktop\\" + fileticket + ".txt")));

		// write contents of StringBuffer to a file
		StringBuilder sbf = generateTicket();
		bwr.write(sbf.toString());

		// flush the stream
		bwr.flush();

		// close the stream
		bwr.close();

	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String prn) {
		this.pnr = pnr;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Map<Passenger, Integer> getPassangers() {
		return passangers;
	}

	public void setPassangers(TreeMap<Passenger, Integer> passangers) {
		this.passangers = passangers;
	}

	@Override
	public String toString() {
		return "Ticket [travelDate=" + travelDate + ", flight=" + flight + "]";
	}

}
