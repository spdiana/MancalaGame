package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.WebController;
import com.example.main.BolTestWebApplication;
import com.example.model.Player;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BolTestWebApplication.class)
@WebMvcTest(WebController.class)
public class BolTestWebControllerTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void getTest() throws Exception{

		int[] array = {0, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6};

		this.mockMvc.perform(get("/game"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("currentPlayer", Player.PLAYER_1.name()))
		.andExpect(model().attribute("showBoard", array));
		//.andDo(print());
	}

	@Test
	public void postPlayTest() throws Exception {

		int[] array1 = {0, 0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6};

		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("currentPlayer", Player.PLAYER_1.name()))
		.andExpect(model().attribute("message", ""))
		.andExpect(model().attribute("showBoard", array1));


		int[] array2 = {0, 0, 0, 8, 8, 8, 8, 2, 7, 7, 6, 6, 6, 6};

		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "2"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("currentPlayer", Player.PLAYER_2.name()))
		.andExpect(model().attribute("message", ""))
		.andExpect(model().attribute("showBoard", array2));

		
		
		int[] array3 = {1, 1, 0, 8, 8, 8, 8, 2, 0, 8, 7, 7, 7, 7};

		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "8"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("currentPlayer", Player.PLAYER_1.name()))
		.andExpect(model().attribute("message", ""))
		.andExpect(model().attribute("showBoard", array3));

		
		int[] array4 = {1, 0, 0, 8, 8, 8, 8, 10, 0, 8, 7, 7, 0, 7};

		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("currentPlayer", Player.PLAYER_2.name()))
		.andExpect(model().attribute("message", ""))
		.andExpect(model().attribute("showBoard", array4));
		
		
		int[] array5 = {2, 1, 1, 9, 8, 8, 8, 10, 0, 0, 8, 8, 1, 8};

		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "9"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("currentPlayer", Player.PLAYER_1.name()))
		.andExpect(model().attribute("message", ""))
		.andExpect(model().attribute("showBoard", array5));
	}

	@Test
	public void postInvalidDigitTest() throws Exception {
		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "a"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("message", "It's not a number. Enter a number. :)"));
		//.andDo(print());
	}
	
	@Test
	public void postInvalidPitPositionPlayer1Test() throws Exception {
		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "9"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("message", "Enter a number between 1-6. :)"));
	}
	
	@Test
	public void postInvalidPitPositionPlayer2Test() throws Exception {
		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "2"));
		
		
		this.mockMvc.perform(post("/result")
				.contentType(MediaType.APPLICATION_JSON)
				.param("pitPosition", "3"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("message", "Enter a number between 8-13. :)"));
	}

}
