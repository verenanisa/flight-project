package com.flight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flight.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	@Query("select count(t) = 1 from Flight t where flightNo = ?1")
	public boolean FlightExistByFlightNo(int trNo);

	@Query("select t.flightNo from Flight t")
	public List<Integer> getAllFlightNo();

	@Query("select distinct t.source from Flight t")
	public List<String> getFlightsources();

	@Query("select distinct t.destination from Flight t")
	public List<String> getFlightdestination();

	@Query("select t from Flight t where source=?1 and destination = ?2")
	public List<Flight> getAllFlight(String source, String destination);
}
