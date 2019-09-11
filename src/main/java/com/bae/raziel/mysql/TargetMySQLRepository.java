package com.bae.raziel.mysql;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class TargetMySQLRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(TargetMySQLRepository.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	public List<MySQLDao> getTableInfo(MySQLDao mySQLDao){
		
		
		Connection con = null;
		
		List<MySQLDao> MySQLDaoList = new ArrayList<MySQLDao>();
		
		try {
			
			
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+mySQLDao.getHostName()+":"+mySQLDao.getPort()+"/information_schema",mySQLDao.getUser(),mySQLDao.getPassword());
			
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select table_schema, table_name, ((data_length + index_length + data_free)/1024)as data_length from information_schema.tables where table_name = '"+mySQLDao.getTableName()+"'");  
			
			while(rs.next()){
				MySQLDao result = new MySQLDao();
				result.setHostName(mySQLDao.getHostName());
				result.setTableSchema(rs.getString("table_schema"));
				result.setTableName(rs.getString("table_name"));
				result.setTableLength(rs.getLong("data_length"));
				MySQLDaoList.add(result);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return MySQLDaoList;
	}

}
