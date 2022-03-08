package com.au.projecttracking.controller;

import java.time.LocalDate;

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

import com.au.projecttracking.dto.LogWorkDTO;
import com.au.projecttracking.service.LogWorkService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class)
public class LogWorkControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LogWorkService logWorkService;
// Date date=new Date();
	LocalDate date = LocalDate.of(2021, 3, 13);
	LogWorkDTO mockLogWork = new LogWorkDTO(101, 102, "on LogWorkController test", 10, 10, date);

	@Test
	public void saveLog() throws Exception {
		Mockito.when(logWorkService.saveLog(Mockito.any())).thenReturn(mockLogWork);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveLog").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{logId:101,ticketId:102,description:on LogWorkController test,timeSpent:10,timeRemaining:10,date:2021-03-13}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getLatestLogRecord() throws Exception {
		Mockito.when(logWorkService.getTicketLatestLog(Mockito.any())).thenReturn(mockLogWork.getTicketId());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/saveLog").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{ticketId:102}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}