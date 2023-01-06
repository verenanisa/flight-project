package com.flight.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.flight.model.Flight;
import com.flight.model.User;
import com.flight.repository.FlightRepository;
import com.flight.repository.UserRepository;

@RestController
// @RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private FlightRepository trp;

	User user = new User();

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public HttpStatus getUserData(@RequestBody User user) {
		System.out.println(user);
		if (user.getEmail().equals(userRepo.findExistByEmail(user.getEmail()))) {
			return HttpStatus.BAD_REQUEST;
		}
		userRepo.save(user);

		return HttpStatus.OK;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView getUserlogData(@ModelAttribute("user") User ussr) {

		boolean existing = userRepo.findExistByEmail(ussr.getEmail());
		System.out.println(existing + "user existinng check" + "came from logggggggggin");

		if (existing) {
			User user1 = userRepo.getUser(ussr.getEmail());

			String originalPasswd = userRepo.getPasswordByEmail(ussr.getEmail());

			if (originalPasswd.equals(ussr.getPassword())) {

				user1.setLogStatus(true);
				userRepo.save(user1);
				user = user1;

			} else {
				ModelAndView model1 = new ModelAndView("loggedIn.html");
				user.setLogStatus(false);
				user.setEmail("");
				model1.addObject(user);
				return model1;
			}

			ModelAndView model = new ModelAndView("loggedIn.html");
			model.addObject(user);
			System.out.println(user + "this user exist");
			return model;
		} else {
			user.setEmail("");
			user.setLogStatus(false);
			user.setAdmin(false);

			ModelAndView model = new ModelAndView("loggedIn.html");
			model.addObject(user);
			return model;
		}

	}

	@GetMapping({ "/logout" })
	public ModelAndView logout() {
		User user1 = userRepo.getUser(user.getEmail());
		user1.setLogStatus(false);
		userRepo.save(user1);
		user.setEmail("");
		user.setLogStatus(false);
		user.setAdmin(false);
		ModelAndView model = new ModelAndView("loggedIn.html");
		model.addObject(user);
		return model;

	}

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
			ModelAndView model = new ModelAndView("loggedIn.html");
			model.addObject(user);
			return model;
		}

	}

	@RequestMapping(value = "/updateFlight", method = RequestMethod.POST)
	public ModelAndView UpdateFlightData(@ModelAttribute Flight tr) {
		trp.save(tr);

		ModelAndView model = new ModelAndView("loggedIn.html");
		model.addObject(user);
		return model;

	}

	@GetMapping({ "/welcome", "/" })
	public ModelAndView welcome() {
		if (user.isLogStatus()) {
			ModelAndView model = new ModelAndView("loggedIn.html");
			model.addObject(user);
			return model;
		} else {

			User user = new User();
			user.setEmail("");
			user.setLogStatus(false);
			user.setAdmin(false);
			ModelAndView model = new ModelAndView("loggedIn.html");
			model.addObject(user);
			return model;
		}
	}

	public User getUser() {
		return user;
	}

	/*
	 * @RequestMapping(value="/loginDataBack",method=RequestMethod.POST,produces =
	 * "application/json", consumes = "application/json")
	 * public ResponseEntity<User> loginDataBack(@RequestBody User user)
	 * {
	 * boolean existing = userRepo.findExistByEmail(user.getEmail());
	 * System.out.println(existing+"user existinng check"+"came from loginDataBack"
	 * );
	 * 
	 * if (existing) {
	 * User usr = userRepo.getUser(user.getEmail());
	 * 
	 * String originalPasswd = userRepo.getPasswordByEmail(user.getEmail());
	 * 
	 * if (originalPasswd.equals(user.getPassword())) {
	 * 
	 * usr.setLogStatus(true);
	 * userRepo.save(usr);
	 * 
	 * return new ResponseEntity<User>(usr,HttpStatus.OK);
	 * }
	 * else {
	 * return new ResponseEntity<User>(usr,HttpStatus.BAD_REQUEST);
	 * }
	 * }
	 * else {
	 * user.setLogStatus(false);
	 * return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
	 * }
	 * 
	 * 
	 * }
	 * 
	 */

}
