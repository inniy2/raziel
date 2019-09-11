package com.bae.raziel.ghost;

import java.util.ArrayList;
import java.util.List;

public class GhostDto {

	private String clusterName;
	private String tableSchema;
	private String tableName;
	private String ghostHostName;
	
	private ArrayList<String> checkReplicaList;
	private ArrayList<String> alterStatement;
	
	private String dataDir;
	private long availableSpace;
	private float diskUsages;
	
	private List<String> outputStrList;
	
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getGhostHostName() {
		return ghostHostName;
	}
	public void setGhostHostName(String ghostHostName) {
		this.ghostHostName = ghostHostName;
	}
	public ArrayList<String> getCheckReplicaList() {
		return checkReplicaList;
	}
	public void setCheckReplicaList(ArrayList<String> checkReplicaList) {
		this.checkReplicaList = checkReplicaList;
	}
	public ArrayList<String> getAlterStatement() {
		return alterStatement;
	}
	public void setAlterStatement(ArrayList<String> alterStatement) {
		this.alterStatement = alterStatement;
	}
	public String getDataDir() {
		return dataDir;
	}
	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}
	public long getAvailableSpace() {
		return availableSpace;
	}
	public void setAvailableSpace(long availableSpace) {
		this.availableSpace = availableSpace;
	}
	public float getDiskUsages() {
		return diskUsages;
	}
	public void setDiskUsages(float diskUsages) {
		this.diskUsages = diskUsages;
	}
	
	
	public List<String> getOutputStrList() {
		return outputStrList;
	}
	public void setOutputStrList(List<String> outputStrList) {
		this.outputStrList = outputStrList;
	}
	
	@Override
	public String toString() {
		return "GhostDto [clusterName=" + clusterName + ", tableSchema=" + tableSchema + ", tableName=" + tableName
				+ ", ghostHostName=" + ghostHostName + ", checkReplicaList=" + checkReplicaList + ", alterStatement="
				+ alterStatement + ", dataDir=" + dataDir + ", availableSpace=" + availableSpace + ", diskUsages="
				+ diskUsages + "]";
	}
	
	
	
}
