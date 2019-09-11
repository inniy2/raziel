package com.bae.raziel.ghost;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ghost")
public class GhostController {

	@Autowired
	GhostService ghostService;
	
	
	@PostMapping("/dryrun")
	public GhostDto dryRun(@Valid @RequestBody GhostDto ghostDto){
		return ghostService.dryRun(ghostDto);
	}
	
	
	@PostMapping("/execute")
	public GhostDto execute(@Valid @RequestBody GhostDto ghostDto){
		ghostService.execute(ghostDto);
		
		return ghostDto;
	}
	
}
