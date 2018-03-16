package com.einfochips.currencyexchange.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.einfochips.currencyexchange.domain.Users;
import com.einfochips.currencyexchange.dto.UsersDTO;
import com.einfochips.currencyexchange.services.UserService;
import com.einfochips.currencyexchange.utils.PasswordUtility;
import com.einfochips.currencyexchange.utils.UserTokenParser;

/**
 * @author akash.shinde
 *
 */
@RestController
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	/**
	 * 
	 */
	@Autowired
	private UserService userService;

	/**
	 * @param user
	 *            This method used to add users to database
	 */
	@RequestMapping(value = "/add-user", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			"application/json" })
	public ResponseEntity<Users> addUser(@RequestBody Users user) {
		// LOGGER.info("Add user " + request);
		ResponseEntity<Users> response;
		// Integer userId = UserTokenParser.getUserIdFromToken(request);
		// LOGGER.info("User id from token: " + userId);
		user.setPassword(PasswordUtility.passwordEncoder(user.getPassword()));
		user.setUpdationTime(new Date());
		user.setCreationTime(new Date());
		Users u = userService.findUserByEmailId(user.getEmail());
		if (u == null) {
			LOGGER.info("user saved");
			response = new ResponseEntity<>(userService.save(user), HttpStatus.OK);
		} else {
			LOGGER.info("user already exists".toUpperCase() + user.getEmail());
			response = new ResponseEntity<>(HttpStatus.IM_USED);
		}

		return response;
	}

	/**
	 * @param user
	 *            This method used to update users to database
	 */
	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public ResponseEntity<Users> editUser(@RequestBody Users userRecord, HttpServletRequest httpServletRequest) {
		LOGGER.info("------IN UPDATE  USER START-----");
		LOGGER.info("user :" + userRecord);
		Integer userId = UserTokenParser.getUserIdFromToken(httpServletRequest);
		Users u = userService.getUser(userRecord.getEmail());
		u.setFirstName(userRecord.getFirstName());
		u.setLastName(userRecord.getLastName());
		u.setAddress(userRecord.getAddress());
		u.setUpdationTime(new Date());
		u = userService.editUser(u);
		LOGGER.info("------IN UPDATE  USER END -----");
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	/**
	 * @param user
	 * @return current user details.
	 */
	@RequestMapping(value = "/get-loggedin-user", method = RequestMethod.GET)
	public ResponseEntity<UsersDTO> getCurrentLoggedInUser(HttpServletRequest request) {
		ResponseEntity<UsersDTO> response;
		LOGGER.info("----- IN get logged in user details  -----");
		Users users = userService.getUser(UserTokenParser.getUserIdFromToken(request));
		UsersDTO userdto = new UsersDTO();
		if (users != null) {
			LOGGER.info(users);
			users = userService.getUser(users.getUserId());
			userdto.setFirstName(users.getFirstName());
			userdto.setLastName(users.getLastName());
			LOGGER.info("-----IN GET logged in  user details -----");
			response = new ResponseEntity<>(userdto, HttpStatus.OK);
		} else {
			LOGGER.info("-----user not logged in-----");
			response = new ResponseEntity<>(userdto, HttpStatus.FORBIDDEN);
		}
		return response;
	}
}