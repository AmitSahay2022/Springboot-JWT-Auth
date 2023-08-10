package com.sahay.JWT.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
   @GetMapping("/users")
   public ResponseEntity<String> user(){
	   return new ResponseEntity<String>("Amit Kumar Is The Best Java Developer",HttpStatus.OK);
   }
}
