package com.newkumar.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.newkumar.springboot.model.Skv;
import com.newkumar.springboot.model.User;
import com.newkumar.springboot.service.SkvService;
import com.newkumar.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/skv/api")
public class SkvRestApiController {

	public static final Logger logger = LoggerFactory.getLogger(SkvRestApiController.class);

	@Autowired
	SkvService skvService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/skv/", method = RequestMethod.GET)
	public ResponseEntity<List<Skv>> listAllUsers1() {
		List<Skv> skvs = skvService.findAllUsers();
		if (skvs.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Skv>>(skvs, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/skv/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser1(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Skv skv = skvService.findById(id);
		if (skv == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Skv>(skv, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------	

	@RequestMapping(value = "/skv/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser1(@RequestBody Skv skv, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", skv);

		if (skvService.isUserExist(skv)) {
			logger.error("Unable to create. A User with name {} already exist", skv.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			skv.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		skvService.saveUser(skv);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/skv/{id}").buildAndExpand(skv.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/skv/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser1(@PathVariable("id") long id, @RequestBody Skv skv) {
		logger.info("Updating User with id {}", id);

		Skv currentUser = skvService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(skv.getName());
		currentUser.setAge(skv.getAge());
		currentUser.setSalary(skv.getSalary());

		skvService.updateUser(currentUser);
		return new ResponseEntity<Skv>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/skv/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser1(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		Skv skv = skvService.findById(id);
		if (skv == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		skvService.deleteUserById(id);
		return new ResponseEntity<Skv>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/skv/", method = RequestMethod.DELETE)
	public ResponseEntity<Skv> deleteAllUsers1() {
		logger.info("Deleting All Users");

		skvService.deleteAllUsers();
		return new ResponseEntity<Skv>(HttpStatus.NO_CONTENT);
	}

}