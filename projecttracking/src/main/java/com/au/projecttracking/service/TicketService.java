package com.au.projecttracking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.projecttracking.dto.ProjecttrackingModelMapper;
import com.au.projecttracking.dto.TicketDTO;
import com.au.projecttracking.model.LogWork;
import com.au.projecttracking.model.Ticket;
import com.au.projecttracking.repository.LogWorkRepository;
import com.au.projecttracking.repository.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private LogWorkRepository logWorkRepository;

	public List<TicketDTO> getTicketByUserId(int userId) throws Exception{
		List<Ticket> tickets = this.ticketRepository.findAllByUserId(userId);
		TicketDTO ticketDTO = null;
		List<TicketDTO> ticketDTOs = new ArrayList<>();
		for(Ticket ticket : tickets) {
			ticketDTO = ProjecttrackingModelMapper.toticketDTO(ticket);
			ticketDTOs.add(ticketDTO);
		}
		ticketDTO = new TicketDTO(0,0,"","","",0,"");
		if(!ticketDTOs.isEmpty())
			return ticketDTOs;
		ticketDTOs.add(ticketDTO);
		return ticketDTOs;
	}

	public TicketDTO getTicketById(int ticketId) throws Exception{
		Ticket ticket = this.ticketRepository.findById(ticketId);
		if(ticket!=null) {
		return ProjecttrackingModelMapper.toticketDTO(ticket);
		
		}
		return new TicketDTO(0,0,"","","",0,"");
		
	}

	public void updateTicket(TicketDTO ticketDTO) throws Exception{
		Ticket ticket = ProjecttrackingModelMapper.toTicket(ticketDTO);
		
		Ticket originalTicket = this.ticketRepository.findById(ticket.getTicketId());
		
		if(originalTicket.getEstimatedTime()!=ticket.getEstimatedTime()) {
			int diff = ticket.getEstimatedTime() - originalTicket.getEstimatedTime();
			List<LogWork> allLogsList = this.logWorkRepository.getLatestLog(ticket.getTicketId());
			if(!allLogsList.isEmpty()) {
				
				for(LogWork log : allLogsList) {
					
					log.setTimeRemaining(log.getTimeRemaining()+diff);
					logWorkRepository.save(log);
				}
			}
			else {
				this.ticketRepository.save(ticket);
			}
		}
		this.ticketRepository.save(ticket);
		
	}

	public void addTicket(TicketDTO ticketDTO) throws Exception{
		Ticket ticket = ProjecttrackingModelMapper.toTicket(ticketDTO);
		ticket.setStatus("To-Do");
		this.ticketRepository.save(ticket);
		
	}

	public List<TicketDTO> getCompletedTicket(Integer userId) {
		List<Ticket> completedTickets = this.ticketRepository.findAllByUserIdAndStatus(userId,"Done");
		TicketDTO ticketDTO = null;
		List<TicketDTO> ticketDTOs = new ArrayList<>();
		for(Ticket ticket : completedTickets) {
			ticketDTO = ProjecttrackingModelMapper.toticketDTO(ticket);
			ticketDTOs.add(ticketDTO);
		}
		ticketDTO = new TicketDTO(0,0,"","","",0,"");
		if(!ticketDTOs.isEmpty())
			return ticketDTOs;
		ticketDTOs.add(ticketDTO);
		return ticketDTOs;
	}

	public List<TicketDTO> getAllTickets() throws Exception{
		List<Ticket> allTicketsList = this.ticketRepository.findAll();
		TicketDTO ticketDTO = null;
		List<TicketDTO> ticketDTOs = new ArrayList<>();
		for(Ticket ticket : allTicketsList) {
			ticketDTO = ProjecttrackingModelMapper.toticketDTO(ticket);
			ticketDTOs.add(ticketDTO);
		}
		ticketDTO = new TicketDTO(0,0,"","","",0,"");
		if(!ticketDTOs.isEmpty())
			return ticketDTOs;
		ticketDTOs.add(ticketDTO);
		return ticketDTOs;
		
	}
}
