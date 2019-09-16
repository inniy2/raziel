package com.bae.raziel.ghost;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GhostService {
	
	private static final Logger logger = LoggerFactory.getLogger(GhostService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	

	@Autowired
	GhostComponent ghostComponent;
	
	
	@Autowired
	GhostRepository ghostRepository;
	
	
	String[] ghostRunComand = new String[] {
			"gh-ost",                                    // 0
			"--max-load=Threads_running=50",             // 1
			"--critical-load=Threads_running=1500",      // 2
			"--chunk-size=500",                          // 3
			"--max-lag-millis=1500",                     // 4
			"--conf=debian.cnf",                         // 5
			"--host=",                                   // 6
			"--throttle-control-replicas=",              // 7
			"--database=",                               // 8
			"--table=",                                  // 9
			"--alter=",                                  // 10
			"--switch-to-rbr",                           // 11
			"--cut-over=default",                        // 12
			"--exact-rowcount",                          // 13
			"--concurrent-rowcount",                     // 14
			"--default-retries=120",                     // 15
			"--timestamp-old-table",                     // 16
			""                                           // 17
	};
	
	
	
	public List<GhostDto> findAll(GhostDto ghostDto){
		
		List<GhostDto> ghostDtoList = new ArrayList<GhostDto>();
		
		List<GhostEntity> ghostEntityList = ghostRepository.findAll();
		
		ghostEntityList.forEach(e -> ghostDtoList.add(this.mapToGhostDto(e, ghostEntityList.indexOf(e))));
		
		return ghostDtoList;
		
	}
	
	
	public List<GhostDto> findAllByProgress(GhostDto ghostDto){
		
		List<GhostDto> ghostDtoList = new ArrayList<GhostDto>();
		
		List<GhostEntity> ghostEntityList = ghostRepository.findHistoryAllByProgressStatus(ghostDto.getProgressStatus());
		
		ghostEntityList.forEach(e -> ghostDtoList.add(this.mapToGhostDto(e, ghostEntityList.indexOf(e))));
		
		return ghostDtoList;
		
	}
	
	
	

	
	public GhostDto dryRun(GhostDto ghostDto){
		
		ghostDto.setOutputStrList(this.ghostRun(ghostDto, "--verbose"));
		
		return ghostDto;
		
	}
	
	
	
	public void execute(GhostDto ghostDto){
		
	
		
		GhostEntity ghostEntity = null;
		
		
		/*
		 * Search history table to confirm alter status
		 * 0. Is it the first time?
		 * 1. Is it in progress?
		 * 2. Is it completed?
		 */
		
		/*
		 * read
		 */
		ghostEntity = ghostRepository.findHistoryByPrimary(ghostDto.getTableName(),ghostDto.getClusterName(),ghostDto.getTableSchema());
		
		
		
		
		/*
		 * Is it the first time?
		 */
		if(ghostEntity == null) {
			
			logger.debug("-------------------------------------------------------");
			logger.debug("The first time for alter");
			logger.debug("-------------------------------------------------------");
			
			/*
			 * create fields
			 */
			ghostEntity = this.mapToGhostEntity(ghostDto);
			ghostEntity.setProgressStatus(1);     // in progress
			ghostEntity.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
			/*
			 * read
			 */
			ghostEntity = ghostRepository.findHistoryByPrimary(ghostDto.getTableName(),ghostDto.getClusterName(),ghostDto.getTableSchema());
			
			
			/*
			 * Alter
			 */
			this.ghostRun(ghostDto, "--execute");
			
			/*
			 * update fields
			 */
			ghostEntity.setProgressStatus(2); // alter completed
			ghostEntity.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
			
			ghostRepository.save(ghostEntity);
			
			/*
			 * 1. Is it in progress?
			 */	
		}else if(ghostEntity.getProgressStatus() == 1) {  // in progress
			logger.debug("-------------------------------------------------------");
			logger.debug("Alter in progress");
			logger.debug("-------------------------------------------------------");
			
			
			logger.info(ghostDto.getClusterName() +" "+ ghostDto.getTableSchema() +" "+ ghostDto.getTableName() + " alter in progress ... ");
			
			/*
			 * 2. Is it completed?
			 */	
		}else if(ghostEntity.getProgressStatus() == 2) {  // alter completed
			logger.debug("-------------------------------------------------------");
			logger.debug("New alter has started");
			logger.debug("-------------------------------------------------------");
			
			
			/*
			 * Update fields
			 */
			ghostEntity.setAlterStatement(ghostDto.getAlterStatement());
			ghostEntity.setCheckReplicaList(ghostDto.getCheckReplicaList());
			ghostEntity.setProgressStatus(1); // in progress
			ghostEntity.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
			
			/*
			 * read
			 */
			ghostEntity = ghostRepository.findHistoryByPrimary(ghostDto.getTableName(),ghostDto.getClusterName(),ghostDto.getTableSchema());
			
			
			/*
			 * Alter
			 */
			this.ghostRun(ghostDto, "--execute");
			
			
			
			/*
			 * Update fields
			 */
			ghostEntity.setProgressStatus(2); // alter completed
			ghostEntity.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
		}else {
			
			logger.error("Alter status is not defined");
			
		}
		
		
	}
	
	
	
	private GhostDto mapToGhostDto(GhostEntity ghostEntity, int orderId) {
		
		GhostDto ghostDto = null;
		
		if( ghostEntity != null ) {
			ghostDto = new GhostDto();
			ghostDto.setOrderId(orderId);
			ghostDto.setClusterName(ghostEntity.getClusterName());
			ghostDto.setTableSchema(ghostEntity.getTableSchema());
			ghostDto.setTableName(ghostEntity.getTableName());
			ghostDto.setGhostHostName(ghostEntity.getGhostHostName());
			ghostDto.setCheckReplicaList(ghostEntity.getCheckReplicaList());
			ghostDto.setAlterStatement(ghostEntity.getAlterStatement());
			ghostDto.setRegisterEmail(ghostEntity.getRegisterEmail());
			ghostDto.setProgressStatus(ghostEntity.getProgressStatus());
			ghostDto.setCreateTimestamp(ghostEntity.getCreateTimestamp());
			ghostDto.setUpdateTimestamp(ghostEntity.getUpdateTimestamp());
		}
		
		return ghostDto;
		
	}
	
	
	private GhostEntity mapToGhostEntity(GhostDto ghostDto) {
		
		GhostEntity ghostEntity = null;

		if( ghostDto != null ) {
			ghostEntity = new GhostEntity();
			ghostEntity.setClusterName(ghostDto.getClusterName());
			ghostEntity.setTableSchema(ghostDto.getTableSchema());
			ghostEntity.setTableName(ghostDto.getTableName());
			ghostEntity.setGhostHostName(ghostDto.getGhostHostName());
			ghostEntity.setCheckReplicaList(ghostDto.getCheckReplicaList());
			ghostEntity.setAlterStatement(ghostDto.getAlterStatement());
			ghostEntity.setRegisterEmail(ghostDto.getRegisterEmail());
			ghostEntity.setProgressStatus(ghostDto.getProgressStatus());
			ghostEntity.setCreateTimestamp(ghostDto.getCreateTimestamp());
			ghostEntity.setUpdateTimestamp(ghostDto.getUpdateTimestamp());
		}
		
		
		return ghostEntity;
		
	}
	
	
	private List<String> ghostRun(GhostDto ghostDto, String verbose) {
		
		StringBuilder database      = new StringBuilder("--database=");
		StringBuilder host          = new StringBuilder("--host=");
		StringBuilder table         = new StringBuilder("--table=");
		StringBuilder alterStatment = new StringBuilder("--alter=");
		StringBuilder checkReplica  = new StringBuilder("--throttle-control-replicas=");
		
		
		logger.debug("cmd option length: "+ghostRunComand.length);
		
		/*
		 * database
		 */
		database.append(ghostDto.getTableSchema());

		
		/*
		 * host
		 */
		host.append(ghostDto.getGhostHostName());
		
		/*
		 * table
		 */
		table.append(ghostDto.getTableName());
		
		/*
		 * checkReplica
		 */
		ArrayList<String> checkReplicaList = ghostDto.getCheckReplicaList();
		for(int i = 0; i < checkReplicaList.size(); i++) {			
			if (i != 0) checkReplica.append(",");
			checkReplica.append(checkReplicaList.get(i));
		}
		
		
		/*
		 * alterStatment
		 */
		ArrayList<String> alterStatementList = ghostDto.getAlterStatement();
		for(int i = 0; i < alterStatementList.size(); i++) {			
			if (i != 0) alterStatment.append(",");
			alterStatment.append(alterStatementList.get(i));
		}
		
		
		
		for(int i= 0; i < ghostRunComand.length; i++) {
			if(i == 6) {        ghostRunComand[i] = host.toString();
			}else if (i == 8) { ghostRunComand[i] = database.toString();
			}else if (i == 9) { ghostRunComand[i] = table.toString();
			}else if (i == 7) { ghostRunComand[i] = checkReplica.toString();
			}else if (i == 10){ ghostRunComand[i] = alterStatment.toString();
			}else if (i == 17){ ghostRunComand[i] = verbose;
			}
		
			logger.info(ghostRunComand[i]);
		}
		
		
		return ghostComponent.runProcess(ghostRunComand);
		
	}
	
	
}
