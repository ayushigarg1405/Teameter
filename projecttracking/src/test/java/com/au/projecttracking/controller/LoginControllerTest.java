package com.au.projecttracking.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.au.projecttracking.dto.LoginDTO;
import com.au.projecttracking.service.LoginService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginService loginService;

	LoginDTO mockLogin = new LoginDTO(101, "mani", "Hrudhay", "user");
	List<LoginDTO> mockLoginList = new ArrayList<LoginDTO>();

	@Test
	public void getUserByUsername() throws Exception {
		Mockito.when(loginService.getUserByUsername(Mockito.anyString())).thenReturn(mockLogin);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUserByUsername/mani")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{id:101,name:mani,designation:user}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void checkUser() throws Exception {
		Mockito.when(loginService.checkUser(Mockito.any())).thenReturn(mockLogin);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{id:101,name:mani,designation:user}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getAllUsers() throws Exception {
		mockLoginList.add(mockLogin);
		Mockito.when(loginService.getAllUsers()).thenReturn(mockLoginList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllUsers").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{id:101,name:mani,designation:user}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void goBack() throws Exception {
		mockLoginList.add(mockLogin);
		Mockito.when(loginService.goBack(Mockito.anyInt())).thenReturn(mockLogin);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllUsers").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{id:101,name:mani,designation:user}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}