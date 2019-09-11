package com.bae.raziel.admin;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MySQLHostService {

	
	@Autowired
	MySQLHostRepository mySQLHostRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MySQLHostService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	
	
	public List<MySQLHostDto> save(List<MySQLHostDto> mySQLHostDtoList) {
		
		List<MySQLHostEntity>  MySQLHostEntityList  = new ArrayList<MySQLHostEntity>();
		
		
		mySQLHostDtoList.forEach(e -> MySQLHostEntityList.add(constructMySQLHostEntity(e)) );
		
		
		mySQLHostRepository.saveAll(MySQLHostEntityList);
		
		
		return mySQLHostDtoList;
	}
	
	
	
	private MySQLHostEntity constructMySQLHostEntity(MySQLHostDto mySQLHostDto) {
		MySQLHostEntity mySQLHostEntity = new MySQLHostEntity();
		
		mySQLHostEntity.setClusterName(mySQLHostDto.getClusterName());
		mySQLHostEntity.setMysqlHostName(mySQLHostDto.getHostName());
		mySQLHostEntity.setHostType(mySQLHostDto.getHostType());
		
		return mySQLHostEntity;
	} 
	
	
}
