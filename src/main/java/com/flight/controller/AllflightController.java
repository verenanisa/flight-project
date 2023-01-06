package com.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.model.Flight;
import com.flight.repository.FlightRepository;

@Controller
public class AllflightController {

	@Autowired
	private FlightRepository trp;

	@GetMapping("/searchFlight/{source}/{dest}")
	public String getflightlist(@PathVariable String source, @PathVariable String dest, Model model) {

		List<Flight> listflights = trp.getAllFlight(source, dest);
		model.addAttribute("flights", listflights);

		return "flightList.html";

	}

}
