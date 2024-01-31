package com.wipro.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/return")
	public ResponseEntity<?> Return(){
		String s = " hi u r deployed";
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}
	

}
