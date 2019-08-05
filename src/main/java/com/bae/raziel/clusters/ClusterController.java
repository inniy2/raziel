package com.bae.raziel.clusters;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.test.HostDTO;


@RestController
@RequestMapping("/api/cluster")
public class ClusterController {
	
	@Autowired
	private ClusterRepository clusterRepository;
	
	
	@PostMapping("/save")
	public ClusterDTO createCluster(@Valid @RequestBody ClusterDTO cluster) {
		return clusterRepository.save(cluster);
	}
	


}
