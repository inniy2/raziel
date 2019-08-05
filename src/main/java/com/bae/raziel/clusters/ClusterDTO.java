package com.bae.raziel.clusters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cluster")
public class ClusterDTO {
	
	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	// OS
	
	@Column(name = "cluster_name", nullable = false)
	private String clusterName;
	
	@Column(name = "collect_timestamp", nullable = true)
	private String collectTimestamp;
	
	@Column(name = "cpu_percentage", nullable = true)
	private float cpuPercentage;
	
	@Column(name = "free_disk_percentage", nullable = true)
	private float freeDiskPercentage;
	
	@Column(name = "total_disk_size", nullable = true)
	private long totalDiskSize;
	
	@Column(name = "free_disk_size", nullable = true)
	private long freeDiskSize;
	
	@Column(name = "mysql_data_size", nullable = true)
	private long mysqlDataSize;
	
	// GHOST	
	
	@Column(name = "ghost_version", nullable = true)
	private String ghostVersion;
	
	@Column(name = "ghost_running", nullable = true)
	private boolean ghostRunning;
	
	@Column(name = "ghost_socket_count", nullable = true)
	private int ghostSockCount;
	
	@Column(name = "ghost_postpone_file", nullable = true)
	private boolean ghostPostponeFile;
	
	// MYSQL	
	
	@Column(name = "mysql_version", nullable = true)
	private String mysqlVersion;
	
	@Column(name = "innodb_version", nullable = true)
	private String innodbVersion;
	
	@Column(name = "mysql_pid", nullable = true)
	private boolean mysqlPid;
	
	@Column(name = "mysql_running", nullable = true)
	private boolean mysqlRunning;
	
	@Column(name = "mysql_socket", nullable = true)
	private boolean mysqlSock;
	
	@Column(name = "report_host_name", nullable = true)
	private String reportHostName;
	
	@Column(name = "read_only", nullable = true)
	private String readOnly;
	
	
	// REPLICATION	
	
	@Column(name = "master_active_count", nullable = true)
	private int masterActiveCount;
	
	@Column(name = "master_host_name", nullable = true)
	private String masterHostName;
	
	@Column(name = "slave_count", nullable = true)
	private int slaveCount;
	
	
	// TARGET
	
	@Column(name = "target_table_name", nullable = true)
	private String targetTableName;
	
	@Column(name = "target_table_count", nullable = true)
	private int targetTableCount;
	
	@Column(name = "possible_schema_name", nullable = true)
	private String possibleSchemaName;
	
	@Column(name = "target_schema_name", nullable = true)
	private String targetSchemaName;
	
	@Column(name = "target_table_size", nullable = true)
	private long targetTableSize;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getCollectTimestamp() {
		return collectTimestamp;
	}

	public void setCollectTimestamp(String collectTimestamp) {
		this.collectTimestamp = collectTimestamp;
	}

	public float getCpuPercentage() {
		return cpuPercentage;
	}

	public void setCpuPercentage(float cpuPercentage) {
		this.cpuPercentage = cpuPercentage;
	}

	public float getFreeDiskPercentage() {
		return freeDiskPercentage;
	}

	public void setFreeDiskPercentage(float freeDiskPercentage) {
		this.freeDiskPercentage = freeDiskPercentage;
	}

	public long getTotalDiskSize() {
		return totalDiskSize;
	}

	public void setTotalDiskSize(long totalDiskSize) {
		this.totalDiskSize = totalDiskSize;
	}

	public long getFreeDiskSize() {
		return freeDiskSize;
	}

	public void setFreeDiskSize(long freeDiskSize) {
		this.freeDiskSize = freeDiskSize;
	}

	public long getMysqlDataSize() {
		return mysqlDataSize;
	}

	public void setMysqlDataSize(long mysqlDataSize) {
		this.mysqlDataSize = mysqlDataSize;
	}

	public String getGhostVersion() {
		return ghostVersion;
	}

	public void setGhostVersion(String ghostVersion) {
		this.ghostVersion = ghostVersion;
	}

	public boolean getGhostRunning() {
		return ghostRunning;
	}

	public void setGhostRunning(boolean ghostRunning) {
		this.ghostRunning = ghostRunning;
	}

	public int getGhostSockCount() {
		return ghostSockCount;
	}

	public void setGhostSockCount(int ghostSockCount) {
		this.ghostSockCount = ghostSockCount;
	}

	public boolean getGhostPostponeFile() {
		return ghostPostponeFile;
	}

	public void setGhostPostponeFile(boolean ghostPostponeFile) {
		this.ghostPostponeFile = ghostPostponeFile;
	}

	public String getMysqlVersion() {
		return mysqlVersion;
	}

	public void setMysqlVersion(String mysqlVersion) {
		this.mysqlVersion = mysqlVersion;
	}

	public String getInnodbVersion() {
		return innodbVersion;
	}

	public void setInnodbVersion(String innodbVersion) {
		this.innodbVersion = innodbVersion;
	}

	public boolean getMysqlPid() {
		return mysqlPid;
	}

	public void setMysqlPid(boolean mysqlPid) {
		this.mysqlPid = mysqlPid;
	}

	public boolean getMysqlRunning() {
		return mysqlRunning;
	}

	public void setMysqlRunning(boolean mysqlRunning) {
		this.mysqlRunning = mysqlRunning;
	}

	public boolean getMysqlSock() {
		return mysqlSock;
	}

	public void setMysqlSock(boolean mysqlSock) {
		this.mysqlSock = mysqlSock;
	}

	public String getReportHostName() {
		return reportHostName;
	}

	public void setReportHostName(String reportHostName) {
		this.reportHostName = reportHostName;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public int getMasterActiveCount() {
		return masterActiveCount;
	}

	public void setMasterActiveCount(int masterActiveCount) {
		this.masterActiveCount = masterActiveCount;
	}

	public String getMasterHostName() {
		return masterHostName;
	}

	public void setMasterHostName(String masterHostName) {
		this.masterHostName = masterHostName;
	}

	public int getSlaveCount() {
		return slaveCount;
	}

	public void setSlaveCount(int slaveCount) {
		this.slaveCount = slaveCount;
	}

	public String getTargetTableName() {
		return targetTableName;
	}

	public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}

	public int getTargetTableCount() {
		return targetTableCount;
	}

	public void setTargetTableCount(int targetTableCount) {
		this.targetTableCount = targetTableCount;
	}

	public String getPossibleSchemaName() {
		return possibleSchemaName;
	}

	public void setPossibleSchemaName(String possibleSchemaName) {
		this.possibleSchemaName = possibleSchemaName;
	}

	public String getTargetSchemaName() {
		return targetSchemaName;
	}

	public void setTargetSchemaName(String targetSchemaName) {
		this.targetSchemaName = targetSchemaName;
	}

	public long getTargetTableSize() {
		return targetTableSize;
	}

	public void setTargetTableSize(long targetTableSize) {
		this.targetTableSize = targetTableSize;
	}

	@Override
	public String toString() {
		return "ClusterDTO [id=" + id + ", clusterName=" + clusterName + ", collectTimestamp=" + collectTimestamp
				+ ", cpuPercentage=" + cpuPercentage + ", freeDiskPercentage=" + freeDiskPercentage + ", totalDiskSize="
				+ totalDiskSize + ", freeDiskSize=" + freeDiskSize + ", mysqlDataSize=" + mysqlDataSize
				+ ", ghostVersion=" + ghostVersion + ", ghostRunning=" + ghostRunning + ", ghostSockCount="
				+ ghostSockCount + ", ghostPostponeFile=" + ghostPostponeFile + ", mysqlVersion=" + mysqlVersion
				+ ", innodbVersion=" + innodbVersion + ", mysqlPid=" + mysqlPid + ", mysqlRunning=" + mysqlRunning
				+ ", mysqlSock=" + mysqlSock + ", reportHostName=" + reportHostName + ", readOnly=" + readOnly
				+ ", masterActiveCount=" + masterActiveCount + ", masterHostName=" + masterHostName + ", slaveCount="
				+ slaveCount + ", targetTableName=" + targetTableName + ", targetTableCount=" + targetTableCount
				+ ", possibleSchemaName=" + possibleSchemaName + ", targetSchemaName=" + targetSchemaName
				+ ", targetTableSize=" + targetTableSize + "]";
	}

	
	
}
