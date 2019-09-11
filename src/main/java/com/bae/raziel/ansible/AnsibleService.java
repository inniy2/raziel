package com.bae.raziel.ansible;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.raziel.admin.MySQLHostEntity;
import com.bae.raziel.admin.MySQLHostRepository;
import com.bae.raziel.ghost.GhostDto;
import com.bae.raziel.mysql.MySQLDao;
import com.bae.raziel.mysql.TargetMySQLRepository;




@Service
public class AnsibleService {
	
	
	@Autowired
	AnsibleComponent ansibleComponent;
	
	
	@Autowired
	MySQLHostRepository mySQLHostRepository;
	
	
	@Autowired
	TargetMySQLRepository targetMySQLRepository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(AnsibleService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	
	public List<String> find( GhostDto ghostDto){
		
		
		List<String> resultList = new ArrayList<String>();
		
		
		
		/*
		 * Read ghost host from admin
		 */
		Optional<MySQLHostEntity> mySQLHostEntityOptional = mySQLHostRepository.findById(ghostDto.getGhostHostName());
		
		MySQLHostEntity mySQLHostEntity = mySQLHostEntityOptional.get();
		
		
		
		
		
		/*
		 * Read target table info from production mysql
		 */
		MySQLDao mySQLDao = new MySQLDao();
		mySQLDao.setHostName(mySQLHostEntity.getMysqlHostName());
		mySQLDao.setUser("bae");
		mySQLDao.setPassword("bae");
		mySQLDao.setPort(3306);
		mySQLDao.setTableName(ghostDto.getTableName());
	
		List<MySQLDao> mySQLDaoList = targetMySQLRepository.getTableInfo(mySQLDao);
		
		mySQLDaoList.forEach(e -> resultList.add(e.toString()));
		
		
		
		
		
		
		
		
		/*
		 * Read disk usage from ghost host by ansible
		 */
		String[] findDiskUsage = new String[] {
				"ansible",                            // 0
				"-i",                                 // 1
				"hosts",                              // 2
				mySQLHostEntity.getClusterName(),     // 3
				"--become",                           // 4
				"-m",                                 // 5
				"shell",                              // 6
				"-a"                                  // 7
				+"df /mysql",                         // 8
				
		};
		
		List<String> ansibleResult =  ansibleComponent.find(findDiskUsage);
		
		ansibleResult.forEach(e -> resultList.add(e));
		
		return  resultList;
		
		
	}
	
	
	
	

}
