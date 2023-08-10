package net.javaguides.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/docker")
public class DockerController {
	@GetMapping
   public ResponseEntity<String> dockerDemo(){
	   return new ResponseEntity<String>("Dockrizing Springboot",HttpStatus.OK);
   }
}
