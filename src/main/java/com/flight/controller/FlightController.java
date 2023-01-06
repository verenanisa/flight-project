package com.flight.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.flight.controller.UserController;
import com.flight.model.Passenger;
import com.flight.model.Ticket;
import com.flight.model.Flight;
import com.flight.model.User;
import com.flight.repository.FlightRepository;
import com.flight.repository.UserRepository;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightRepository trp;

	@Autowired
	UserRepository userRepo;

	Flight trraaaiin;
	String trvldate;

	@RequestMapping(value = "/flightData/{trNo}", method = RequestMethod.GET)
	public Flight getFlight(@PathVariable int trNo) {

		Optional<Flight> tr = trp.findById(trNo);

		if (tr.isPresent()) {
			Flight t = tr.get();
			trraaaiin = t;
			return t;
		} else {

			return null;
		}

	}

	@RequestMapping(value = "/trvelDate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus getFlight(@RequestBody String trvdate) {

		trvldate = trvdate.substring(15, 25);
		return (HttpStatus.OK);

	}

	// trying through thymleaf feature and spring boot

	@RequestMapping(value = "/getFlight/{trNo}", method = RequestMethod.GET)
	public ModelAndView getflightlist(@PathVariable int trNo) {
		Optional<Flight> tr = trp.findById(trNo);

		ModelAndView model = new ModelAndView("AvailableFlightList.html");

		if (tr.isPresent()) {
			Flight t = tr.get();

			model.addObject("flight", t);
			return model;
		} else {
			ModelAndView model2 = new ModelAndView("loggedIn.html");
			return model2;
		}
	}

	@RequestMapping(value = "/bookFlight/{trNo}", method = RequestMethod.GET)
	public ModelAndView BookTicket(@PathVariable int trNo) {
		Optional<Flight> tr = trp.findById(trNo);

		ModelAndView model = new ModelAndView("Booking.html");

		if (tr.isPresent()) {
			Flight t = tr.get();
			model.addObject("flight", t);
			return model;
		} else {
			ModelAndView model2 = new ModelAndView("AvailableFlightList.html");
			Flight t = tr.get();
			model2.addObject("flight", t);
			return model2;
		}
	}

	@RequestMapping(value = "/addPassenger", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus getFlight(@RequestBody List<Passenger> p) {

		System.out.println("flight object ha ye :" + trraaaiin);
		System.out.println("String Format me javaScript se aya hua date ha " + trvldate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date travelllDate = format.parse(trvldate);
			System.out.println("conversion k bad " + travelllDate);
			Ticket tkkt = new Ticket(travelllDate, trraaaiin);
			for (Passenger psng : p) {
				tkkt.addPassenger(psng.getName(), psng.getAge(), psng.getGeneder());
			}
			try {
				tkkt.writeTicket();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		} catch (ParseException e) {

			e.printStackTrace();
		}

		System.out.println(p);

		return (HttpStatus.OK);

	}

	@RequestMapping(value = "/logout/{email}", method = RequestMethod.GET)
	public HttpStatus logOutUserData(@PathVariable String email) {
		System.out.println(email);
		User usr = userRepo.getUser(email);
		usr.setLogStatus(false);
		userRepo.save(usr);

		return HttpStatus.OK;

	}

	@RequestMapping(value = "/getSource", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getFlightSource() {

		List<String> sources = trp.getFlightsources();
		return new ResponseEntity<List<String>>(sources, HttpStatus.OK);

	}

	@RequestMapping(value = "/getDestinations", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getFlightDestinations() {

		List<String> sources = trp.getFlightdestination();
		return new ResponseEntity<List<String>>(sources, HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllFlightNo", method = RequestMethod.GET)
	public ResponseEntity<List<Integer>> getAllFlightNumbers() {

		List<Integer> FlightNolist = trp.getAllFlightNo();
		return new ResponseEntity<List<Integer>>(FlightNolist, HttpStatus.OK);

	}

	@GetMapping("/searchFlight/{source}/{dest}")
	public String getflightlist(@PathVariable String source, @PathVariable String dest, Model model) {

		List<Flight> listflights = trp.getAllFlight(source, dest);
		model.addAttribute("flights", listflights);

		return "flightList.html";

	}

}
