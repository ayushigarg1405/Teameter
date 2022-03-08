package com.au.projecttracking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.au.projecttracking.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer>{
	
	public Login findByUsernameAndPassword(String username, String password);

	public List<Login> findAllByDesignation(String designation);

	public Login findByUsername(String username);
	
 	public Login findByLoginId(Integer loginId);
}
