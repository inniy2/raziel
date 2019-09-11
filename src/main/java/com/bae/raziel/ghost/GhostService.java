package com.bae.raziel.ghost;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GhostService {
	
	private static final Logger logger = LoggerFactory.getLogger(GhostService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	

	@Autowired
	GhostComponent ghostComponent;
	
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
	
	
	public GhostDto dryRun(GhostDto ghostDto){
		
		ghostDto.setOutputStrList(this.ghostRun(ghostDto, "--verbose"));
		
		return ghostDto;
		
	}
	
	
	
	public void execute( GhostDto ghostDto){
		
	
		
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
