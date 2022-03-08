package com.au.projecttracking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.projecttracking.dto.LoginDTO;
import com.au.projecttracking.dto.ProjecttrackingModelMapper;
import com.au.projecttracking.model.Login;
import com.au.projecttracking.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		super();
		this.loginRepository = loginRepository;
	}

	public LoginDTO checkUser(LoginDTO login) throws Exception{
		Login userLogin = loginRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		LoginDTO loginDTO = new LoginDTO(0,"","","");
		if(userLogin!=null) {
			return ProjecttrackingModelMapper.toLoginDTO(userLogin);
		}
		return loginDTO;
	}

	public List<LoginDTO> getAllUsers() throws Exception{
		List<Login> allUsersList = this.loginRepository.findAllByDesignation("user");
		LoginDTO loginDTO = null;
		List<LoginDTO> allUsersDtos = new ArrayList<>();
		for(Login login : allUsersList) {
			loginDTO = ProjecttrackingModelMapper.toLoginDTO(login);
			allUsersDtos.add(loginDTO);
		}
		if(!allUsersDtos.isEmpty()) {
			return allUsersDtos;
		}
		loginDTO = new LoginDTO(0,"","","");
		allUsersDtos.add(loginDTO);
		return allUsersDtos;
	}

	public LoginDTO getUserByUsername(String username) throws Exception{
		Login login = this.loginRepository.findByUsername(username);
		if(login!=null) {
			return ProjecttrackingModelMapper.toLoginDTO(login);
			
		}
		return new LoginDTO(0,"","","");
	}

	public LoginDTO goBack(Integer loginId) throws Exception{
		Login login = this.loginRepository.findByLoginId(loginId);
		return ProjecttrackingModelMapper.toLoginDTO(login);
		
	}

}
