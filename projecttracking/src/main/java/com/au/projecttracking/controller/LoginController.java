package com.au.projecttracking.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.au.projecttracking.dto.LoginDTO;
import com.au.projecttracking.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@GetMapping(value="/getUserByUsername/{username}")
	public LoginDTO getUserByUsername(@PathVariable String username) throws Exception{
		return loginService.getUserByUsername(username);
	}
	
	@PostMapping({"/login"})
    public LoginDTO checkUser(@RequestBody LoginDTO login) throws Exception{
       return this.loginService.checkUser(login);
    }
	
	@GetMapping("/getAllUsers")
	public List<LoginDTO> getAllUsers() throws Exception{
		return this.loginService.getAllUsers();
	}
	 
	@GetMapping(value="/go-back/{loginId}")
	public LoginDTO goBack(@PathVariable Integer loginId) throws Exception{
		return loginService.goBack(loginId);
	}
}
