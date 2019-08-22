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
		

	public HostDTO save(HostDTO host) {
		
		logger.debug("save method");
		
		logger.debug(host.toString());
		
		return hostRepository.save(host);
		
	}
	
	
}
