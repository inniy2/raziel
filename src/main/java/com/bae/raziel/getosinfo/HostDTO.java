package com.bae.raziel.getosinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "host")
public class HostDTO {

	

	@Id
	private long id;

	@Column(name = "cpu_percentage", nullable = true)
	private float cpuPercentage;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCpuPercentage() {
		return cpuPercentage;
	}

	public void setCpuPercentage(float cpuPercentage) {
		this.cpuPercentage = cpuPercentage;
	}

	
	@Override
	public String toString() {
		return "Host [id=" + id + ", cpuPercentage=" + cpuPercentage + "]";
	}

	
	
}
