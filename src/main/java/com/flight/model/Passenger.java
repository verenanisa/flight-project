package com.flight.model;

public class Passenger implements Comparable<Passenger> {
	private String name;
	private int age;
	private char geneder;

	public Passenger(String name, int age, char geneder) {
		super();
		this.name = name;
		this.age = age;
		this.geneder = geneder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGeneder() {
		return geneder;
	}

	public void setGeneder(char geneder) {
		this.geneder = geneder;
	}

	@Override
	public int compareTo(Passenger o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.getName());
	}

	@Override
	public String toString() {
		return "Passenger [name=" + name + ", age=" + age + ", geneder=" + geneder + "]";
	}

}