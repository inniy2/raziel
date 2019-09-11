package com.bae.raziel.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MySQLHostRepository extends JpaRepository<MySQLHostEntity, String>{
	
	
	
	
}
