package com.ap.ectswebsite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EctswebsiteApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void whenValidInput_Return200() throws Exception {
		MvcResult result = mockMvc.perform(get("/opleidingen/{schoolYear}", "2018-19")).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void whenInvalidInput_SchoolYear_Return404() throws Exception {
		MvcResult result = mockMvc.perform(get("/opleidingen/{schoolYear}", "2018-20")).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(404, status);
	}

	@Test
	public void whenValidInput_ProgramCode_Return200() throws Exception {

	}

}
