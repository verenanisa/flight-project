package com.flight.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.flight.model.Flight;
import com.flight.model.User;
import com.flight.repository.AdminRepository;
import com.flight.repository.FlightRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminRepository adminRepo;

	@Autowired
	private FlightRepository trp;

	@RequestMapping(value = "/addFlight", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public HttpStatus AddFlight(@RequestBody Flight flight) {
		if (trp.FlightExistByFlightNo(flight.getFlightNo())) {
			System.out.println("Already Exist");

			return HttpStatus.BAD_REQUEST;

		} else {
			trp.save(flight);
			System.out.println("Flight Added SuccessFully !!!!");

			return HttpStatus.OK;
		}

	}

	@RequestMapping(value = "/deleteFlight/{trNo}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public HttpStatus deleteFlightData(@PathVariable int trNo) {
		System.out.println(trNo);

		Optional<Flight> tr = trp.findById(trNo);

		if (tr.isPresent()) {
			trp.deleteById(trNo);
			return HttpStatus.OK;
		}

		return HttpStatus.BAD_REQUEST;

	}

	@RequestMapping(value = "/addFlight", method = RequestMethod.GET)
	public ModelAndView AddFlightData() {
		ModelAndView model = new ModelAndView("addFlight.html");
		Flight flight = new Flight();
		model.addObject(flight);
		return model;

	}

	@RequestMapping(value = "/updateFlight/{trNo}", method = RequestMethod.GET)
	public ModelAndView UpdateFlightData(@PathVariable int trNo) {

		Optional<Flight> flight = trp.findById(trNo);

		if (flight.isPresent()) {
			ModelAndView model = new ModelAndView("updateFlight.html");
			model.addObject("flight", flight);
			return model;
		} else {
			ModelAndView model = new ModelAndView("index.html");
			return model;
		}

	}

	@RequestMapping(value = "/updateFlight", method = RequestMethod.POST)
	public String UpdateFlightData(@ModelAttribute Flight tr) {
		trp.save(tr);

		return "redirect:/welcome";

	}

	// @RequestMapping(value="/addFlight",method=RequestMethod.POST,produces =
	// "application/json", consumes = "application/json")
	// public ModelAndView addFlightData(@RequestBody Flight flight)
	// {
	// if(trp.FlightExistByFlightNo(flight.getFlightNo())) {
	// System.out.println("Already Exist");
	// ModelAndView model = new ModelAndView("addFlight.html");
	// model.addObject(new Flight());
	// return model;
	//
	// }
	// else {
	// trp.save(flight);
	// System.out.println("Flight Added SuccessFully !!!!");
	// ModelAndView model = new ModelAndView("addFlight.html");
	// return
	// }
	//
	// }

}
