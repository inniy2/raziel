package com.bae.raziel.getosinfo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/host")
public class HostController {
	

	
	@Autowired
	private HostService hostSevice;
	
	@PostMapping("/savehost")
	public HostDTO createHost(@Valid @RequestBody HostDTO host) {
		return hostSevice.save(host);
	}

	
}
