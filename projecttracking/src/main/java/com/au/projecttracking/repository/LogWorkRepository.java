package com.au.projecttracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.au.projecttracking.model.LogWork;

@Repository
public interface LogWorkRepository extends CrudRepository<LogWork, Integer>{
	
	@Query(value = "SELECT t FROM LogWork t WHERE t.ticketId = ?1 order by t.date desc, t.timeRemaining asc")
	public List<LogWork> getLatestLog(int ticketId);

	public List<LogWork> findAllByTicketId(Integer ticketId);

	public void deleteById(Integer logId);

	public LogWork findByLogId(Integer logId);
 	
}
