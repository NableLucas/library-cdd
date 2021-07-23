package com.nable.library.shared;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nable.library.newinstance.Type;
import com.nable.library.newuser.UserType;

@Component
public class TestApi {

	@Autowired
	private MockMvc mvc;
	
	public ResultActions createBook(String title, BigDecimal price, String isbn) throws Exception {
		String payload = new  ObjectMapper()
				.writeValueAsString(
						Map.of("title", title,
								"price", price,
								"isbn", isbn));
		System.out.println(payload);
		
		return mvc.perform(MockMvcRequestBuilders.post("/book")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(payload));
	}
	
	public ResultActions createUser(UserType userType) throws Exception {
		String payload = new ObjectMapper()
				.writeValueAsString(
						Map.of("type", userType));
		
		System.out.println(payload);
		
		return mvc.perform(MockMvcRequestBuilders.post("/api/users")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(payload))
				.andDo(handler -> {
					System.out.println(handler.getResponse().getContentAsString());
				});
	}
	
	public ResultActions createInstance(String isbn, Type type) throws Exception {
		String payload = new ObjectMapper()
				.writeValueAsString(
						Map.of("type", type));
		
		System.out.println(payload);
		
		return mvc.perform(MockMvcRequestBuilders.post("/book/{isbn}/instances", isbn)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(payload));
	}
	
	public ResultActions createLend(long idUser, long idBook, int time) throws Exception {
		String payload = new ObjectMapper()
				.writeValueAsString(
						Map.of("idUser", idUser,
								"idBook", idBook,
								"time", time));
		
		System.out.println(payload);
		
		return mvc.perform(MockMvcRequestBuilders.post("/api/lend")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(payload));
	}
	

	
}
