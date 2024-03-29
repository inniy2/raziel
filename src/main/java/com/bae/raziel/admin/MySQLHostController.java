package com.bae.raziel.admin;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/mysqlhost")
public class MySQLHostController {

	
	@Autowired
	MySQLHostService mySQLHostService;
	
	
	@PostMapping("/save")
	public List<MySQLHostDto> save(@Valid @RequestBody List<MySQLHostDto> mySQLHostDtoList){
		return mySQLHostService.save(mySQLHostDtoList);
	}
	
	
	@PostMapping("/updateReadOnly")
	public List<MySQLHostDto> updateReadOnly(@Valid @RequestBody MySQLHostDto mySQLHostDto){
		return mySQLHostService.updateReadOnly(mySQLHostDto);
	}
	
	
	@PostMapping("/updateGhostHost")
	public List<MySQLHostDto> updateGhostHost(@Valid @RequestBody MySQLHostDto mySQLHostDto){
		return mySQLHostService.updateGhostHost(mySQLHostDto);
	}
	
	
	
}
