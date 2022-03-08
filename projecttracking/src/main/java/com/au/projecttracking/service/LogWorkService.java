package com.au.projecttracking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.projecttracking.dto.LogWorkDTO;
import com.au.projecttracking.dto.ProjecttrackingModelMapper;
import com.au.projecttracking.model.LogWork;
import com.au.projecttracking.model.Ticket;
import com.au.projecttracking.repository.LogWorkRepository;
import com.au.projecttracking.repository.TicketRepository;
@Service
public class LogWorkService {
	
	@Autowired
	private LogWorkRepository logWorkRepository;
	
	@Autowired
	private TicketRepository ticketRepository;

	public LogWorkDTO saveLog(LogWorkDTO logWorkDTO) throws Exception{
		LogWork logWork = ProjecttrackingModelMapper.toLogWork(logWorkDTO);
		List<LogWork> allLogsOfTicket = this.logWorkRepository.findAllByTicketId(logWork.getTicketId());
		for(LogWork log : allLogsOfTicket) {
			log.setTimeRemaining(logWorkDTO.getTimeRemaining());
			logWorkRepository.save(log);
		}
		logWorkRepository.save(logWork);
		
		return logWorkDTO;
	}

	public int getTicketLatestLog(int ticketId) throws Exception{
		List<LogWork> logWork = this.logWorkRepository.getLatestLog(ticketId);
		if(logWork.isEmpty()) {
			Ticket ticket = this.ticketRepository.findById(ticketId);
			return ticket.getEstimatedTime();
		}
		LogWorkDTO logWorkDTO = ProjecttrackingModelMapper.toLogWorkDTO(logWork.get(0));
		return logWorkDTO.getTimeRemaining();
	}

	public List<LogWorkDTO> getLogByTicketId(Integer ticketId) throws Exception{
		List<LogWork> logs = this.logWorkRepository.findAllByTicketId(ticketId);
		LogWorkDTO logWorkDTO = null;
		List<LogWorkDTO> logsDtos = new ArrayList<>();
		for(LogWork logWork : logs) {
			logWorkDTO = ProjecttrackingModelMapper.toLogWorkDTO(logWork);
			logsDtos.add(logWorkDTO);
		}
		logWorkDTO = new LogWorkDTO(0,0,"",0,0, null);
		if(!logsDtos.isEmpty())
			return logsDtos;
		logsDtos.add(logWorkDTO);
		return logsDtos;
	}

	public void deleteLog(Integer logId) throws Exception{
		
		LogWork logWork = this.logWorkRepository.findByLogId(logId);
		int timeToAdd = logWork.getTimeSpent();
		int ticketId = logWork.getTicketId();
		List<LogWork> latestLogWork = this.logWorkRepository.getLatestLog(ticketId);
		if(latestLogWork.size()==1 && latestLogWork.get(0).getLogId()==logId) {
			this.logWorkRepository.deleteById(logId);
			System.out.println("Deleted log");
		}
		else if(latestLogWork.size()==1) {
			
		}
		else {
			
			this.logWorkRepository.deleteById(logId);
			System.out.println("Deleted log");
			List<LogWork> getLogs = this.logWorkRepository.getLatestLog(ticketId);
			for(LogWork log : getLogs) {
				
				log.setTimeRemaining(log.getTimeRemaining()+timeToAdd);
				logWorkRepository.save(log);
			}
			
		}
		
		
	}
	

}
