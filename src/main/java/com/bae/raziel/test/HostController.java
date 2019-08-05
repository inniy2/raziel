package com.bae.raziel.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.common.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class HostController {
	
	
	@Autowired
	private HostRepository hostRepository;
	
	@GetMapping("/hosts")
	public List<HostDTO> getAllHost() {
		return hostRepository.findAll();
	}
	
	@PostMapping("/host")
	public HostDTO createEmployee(@Valid @RequestBody HostDTO host) {
		return hostRepository.save(host);
	}
	
	@GetMapping("/host/{id}")
	public ResponseEntity<HostDTO> getEmployeeById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		HostDTO host = hostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Host not found for this id :: " + id));
		return ResponseEntity.ok().body(host);
	}
	
	@PutMapping("/host/{id}")
	public ResponseEntity<HostDTO> updateEmployee(@PathVariable(value = "id") Long id,
			@Valid @RequestBody HostDTO hostDetails) throws ResourceNotFoundException {
		HostDTO host = hostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Host not found for this id :: " + id));

		host.setHostName(hostDetails.getHostName());
		
		final HostDTO updatedHost = hostRepository.save(host);
		return ResponseEntity.ok(updatedHost);
	}
	
	
	@DeleteMapping("/host/{id}")
	public Map<String, Boolean> deleteHost(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		HostDTO host = hostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Host not found for this id :: " + id));

		hostRepository.delete(host);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
