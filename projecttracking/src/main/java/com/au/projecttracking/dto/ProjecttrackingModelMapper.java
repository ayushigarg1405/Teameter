package com.au.projecttracking.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.au.projecttracking.model.LogWork;
import com.au.projecttracking.model.Login;
import com.au.projecttracking.model.Ticket;


@Component
public class ProjecttrackingModelMapper {
	 private ProjecttrackingModelMapper() {
		 super();
	}
	public static LoginDTO toLoginDTO(Login login) {
		 ModelMapper mapper=new ModelMapper();
		 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 return mapper.map(login, LoginDTO.class);
		 
	 }
	 public static Login toLogin(LoginDTO loginDTO) {
		 ModelMapper mapper=new ModelMapper();
		 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 return mapper.map(loginDTO, Login.class);
		
	 }
	 
	 
	 public static LogWorkDTO toLogWorkDTO(LogWork logWork) {
		 ModelMapper mapper=new ModelMapper();
		 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 return mapper.map(logWork, LogWorkDTO.class);
		 
	 }
	 public static LogWork toLogWork(LogWorkDTO logWorkDTO) {
		 ModelMapper mapper=new ModelMapper();
		 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 return mapper.map(logWorkDTO, LogWork.class);
		 
	 }
	 
	 public static TicketDTO toticketDTO(Ticket ticket) {
		 ModelMapper mapper=new ModelMapper();
		 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 return mapper.map(ticket, TicketDTO.class);
		 
	 }
	 public static Ticket toTicket(TicketDTO ticketDTO) {
		 ModelMapper mapper=new ModelMapper();
		 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		 return mapper.map(ticketDTO, Ticket.class);
		 
	 }
	 
	 
	 
	 
}