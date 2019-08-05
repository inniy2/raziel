package com.bae.raziel.calltest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.test.HostDTO;

@RestController
@RequestMapping("/calltest")
public class CallTestController {

	@Autowired
	CallTestService callTestService;
		
	
	@GetMapping("/call1")
	public String getCall1() {
		return callTestService.call1();
	}
	
	@GetMapping("/call2")
	public String callClient1() {
		return callTestService.callClient1();
	}
	
	@PostMapping("/receiveCall")
	public String createEmployee(@Valid @RequestBody String message) {
		
		
		return message;
	}
	
}
