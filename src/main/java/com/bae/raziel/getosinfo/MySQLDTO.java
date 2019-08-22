package com.bae.raziel.getosinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mysql_node")
public class MySQLDTO {
	
	@Id
	private long id;
	
	@Column(name ="report_host_name", nullable = true)
	private String reportHostName;
	
	@Column(name ="mysql_version", nullable = true)
	private String mysqlVersion;
	
	@Column(name ="innodb_version", nullable = true)
	private String innodbVersion;
	
	@Column(name ="read_only", nullable = true)
	private Boolean readOnly;
	
	@Column(name ="master_active_count", nullable = true)
	private int masterActiveCount;
	
	@Column(name ="slave_count", nullable = true)
	private int slaveCount;
	
	@Column(name ="slave_host_name", nullable = true)
	private String slaveHostName;
	
	@Column(name ="master_host_name", nullable = true)
	private String masterHostName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReportHostName() {
		return reportHostName;
	}

	public void setReportHostName(String reportHostName) {
		this.reportHostName = reportHostName;
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

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public int getMasterActiveCount() {
		return masterActiveCount;
	}

	public void setMasterActiveCount(int masterActiveCount) {
		this.masterActiveCount = masterActiveCount;
	}

	public int getSlaveCount() {
		return slaveCount;
	}

	public void setSlaveCount(int slaveCount) {
		this.slaveCount = slaveCount;
	}

	public String getSlaveHostName() {
		return slaveHostName;
	}

	public void setSlaveHostName(String slaveHostName) {
		this.slaveHostName = slaveHostName;
	}

	public String getMasterHostName() {
		return masterHostName;
	}

	public void setMasterHostName(String masterHostName) {
		this.masterHostName = masterHostName;
	}

	@Override
	public String toString() {
		return "MySQLDTO [id=" + id + ", reportHostName=" + reportHostName + ", mysqlVersion=" + mysqlVersion
				+ ", innodbVersion=" + innodbVersion + ", readOnly=" + readOnly + ", masterActiveCount="
				+ masterActiveCount + ", slaveCount=" + slaveCount + ", slaveHostName=" + slaveHostName
				+ ", masterHostName=" + masterHostName + "]";
	}
	

	
	
	
}
