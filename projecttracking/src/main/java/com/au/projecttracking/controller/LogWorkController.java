package com.au.projecttracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.au.projecttracking.dto.LogWorkDTO;
import com.au.projecttracking.service.LogWorkService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LogWorkController {
	
	@Autowired
	private LogWorkService logWorkService;
	
	@PostMapping("/saveLog")
	public LogWorkDTO saveLog(@RequestBody LogWorkDTO logWorkDTO) throws Exception{
		return this.logWorkService.saveLog(logWorkDTO);
	}

	@GetMapping(value = "/getLatestLog/{ticketId}")
	public int getLatestLogRecord(@PathVariable Integer ticketId) throws Exception{
		return this.logWorkService.getTicketLatestLog(ticketId);
	}
	
	@GetMapping(value = "/getLogByTicketId/{ticketId}")
	public List<LogWorkDTO> getLogByTicketId(@PathVariable Integer ticketId) throws Exception{
		return this.logWorkService.getLogByTicketId(ticketId);
	}
	
	@PostMapping("/deleteLog/{logId}")
	public void deleteLog(@PathVariable Integer logId) throws Exception{
		this.logWorkService.deleteLog(logId);
	}
}
