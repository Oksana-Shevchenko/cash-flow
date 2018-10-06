package com.cash.cashflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {

	@RequestMapping(value = "/user/{userName}", method= RequestMethod.GET)
	public ResponseEntity<String> getName(@PathVariable("userName") String userName) {
		return new ResponseEntity<>("Hello "+userName, HttpStatus.OK);
	}
}
