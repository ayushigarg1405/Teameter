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

import com.au.projecttracking.dto.TicketDTO;
import com.au.projecttracking.service.TicketService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TicketController.class)
public class TicketControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TicketService ticketService;

	TicketDTO mockTicketDTO = new TicketDTO(10, 10, "TicketDTO", "this is TicketDTO", "comment this is TicketDTO", 40,
			"ToDo");
	List<TicketDTO> mockTicketDTOList = new ArrayList<TicketDTO>();

	@Test
	public void getUserByUsername() throws Exception {
		mockTicketDTOList.add(mockTicketDTO);
		Mockito.when(ticketService.getTicketByUserId(Mockito.anyInt())).thenReturn(mockTicketDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUserByUsername/mani")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{[" + "ticketId:10,userId:10,title:TicketDTO,description:this is TicketDTO,"
				+ "comments:comment this is TicketDTO,estimatedTime:40,status:ToDo]}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getTicketById() throws Exception {
		Mockito.when(ticketService.getTicketById(Mockito.anyInt())).thenReturn(mockTicketDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUserByUsername/mani")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{" + "ticketId:10,userId:10,title:TicketDTO,description:this is TicketDTO,"
				+ "comments:comment this is TicketDTO,estimatedTime:40,status:ToDo}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getCompletedTicketByUserId() throws Exception {
		mockTicketDTOList.add(mockTicketDTO);
		Mockito.when(ticketService.getCompletedTicket(Mockito.anyInt())).thenReturn(mockTicketDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUserByUsername/mani")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{[" + "ticketId:10,userId:10,title:TicketDTO,description:this is TicketDTO,"
				+ "comments:comment this is TicketDTO,estimatedTime:40,status:ToDo]}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}