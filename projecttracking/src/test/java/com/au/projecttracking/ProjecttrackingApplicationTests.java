package com.au.projecttracking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.projecttracking.controller.LoginController;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProjecttrackingApplicationTests {

	@Autowired
	private LoginController loginController;

	@Test
	void contextLoads() throws Exception {
		assertThat(loginController).isNotNull();
	}

}
