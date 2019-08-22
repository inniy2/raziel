package com.bae.raziel.getosinfo;

import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class HostService {

	private static final Logger logger = LoggerFactory.getLogger(HostService.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	private HostRepository hostRepository;
	
	@Autowired
	private MySQLRepository mysqlRepository;
	

	public HostDTO saveHost(HostDTO hostDTO) {
		
		logger.debug("save host method called");
		
		return hostRepository.save(hostDTO);
		
	}
	
	
	public MySQLDTO saveMySQL(MySQLDTO mysqlDTO) {
		
		logger.debug("save mysql method called");
		
		return mysqlRepository.save(mysqlDTO);
		
	}
	
	
	
	
}
