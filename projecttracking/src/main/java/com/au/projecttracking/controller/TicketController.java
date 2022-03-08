package com.au.projecttracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.au.projecttracking.dto.TicketDTO;
import com.au.projecttracking.service.TicketService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@GetMapping(value = "/allTickets")
	public List<TicketDTO> getAllTickets() throws Exception{
		return this.ticketService.getAllTickets();
	}
	
	@PostMapping(path= "/addticket",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addTicket(@RequestBody TicketDTO ticketDTO) throws Exception{
		this.ticketService.addTicket(ticketDTO);
	}
	
	@GetMapping(value = "/ticketByUserId/{userId}")
	public List<TicketDTO> getTicketByUserId(@PathVariable Integer userId) throws Exception{
		return this.ticketService.getTicketByUserId(userId);
	}
	
	@GetMapping(value = "/ticketByTicketId/{ticketId}")
	public TicketDTO getTicketById(@PathVariable Integer ticketId) throws Exception{
		return this.ticketService.getTicketById(ticketId);
	}
	
	@PostMapping("/updateTicket")
	public void updateTicket(@RequestBody TicketDTO ticketDTO) throws Exception{
		this.ticketService.updateTicket(ticketDTO);
	}
	
	@GetMapping(value = "/getCompletedTicket/{userId}")
	public List<TicketDTO> getCompletedTicketByUserId(@PathVariable Integer userId) throws Exception{
		return this.ticketService.getCompletedTicket(userId);
		
	}
	
	
	
	
}
