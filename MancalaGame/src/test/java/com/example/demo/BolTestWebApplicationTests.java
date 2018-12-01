package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.main.BolTestWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BolTestWebApplication.class)
public class BolTestWebApplicationTests {

	private MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Before
	public void before() {
		setUp();
	}

	@Test
	public void contextLoads() {
		String uri = "/game";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	
	}
	
	@Test
	public void contextLoads2() {
		String uri = "/result";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int status = mvcResult.getResponse().getStatus();
		assertEquals(405, status);
		
	
	}

}
