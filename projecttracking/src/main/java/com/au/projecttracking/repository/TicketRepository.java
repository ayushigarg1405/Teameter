package com.au.projecttracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.au.projecttracking.model.Ticket;
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer>{
	@Query(value = "SELECT t FROM Ticket t where t.userId = ?1 and t.status in ('To-Do', 'In-Progress')")
	List<Ticket> findAllByUserId(int userId);
	
 	Ticket findById(int ticketId);

	List<Ticket> findAllByUserIdAndStatus(Integer userId, String string);
	
	List<Ticket> findAll();
}
