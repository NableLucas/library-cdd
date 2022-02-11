package com.nable.library.lend;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nable.library.newinstance.Type;
import com.nable.library.newuser.UserType;
import com.nable.library.shared.TestApi;

import net.jqwik.api.ForAll;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.constraints.Unique;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class NewLendControllerTest {
	
	@Autowired
	private TestApi api;
	
	public void lendDefaultUser(
			@ForAll @AlphaChars @StringLength(min = 1, max = 255) String title,
			@ForAll @BigRange(min = "1", max = "100") BigDecimal price,
			@ForAll @Size(10) List<@NumericChars @Unique Character> listCharactersIsbn,
			@ForAll @IntRange(min=1,max=5) int instanceNumber,
			@ForAll @IntRange(min = 1,max = 60) int time)
			throws Exception {
		
		String isbn = listCharactersIsbn.stream()
				.map(c -> c.toString()).collect(Collectors.joining());
		
		String idBook = api.createBook(title, price, isbn).andReturn().getResponse().getContentAsString().trim();
		String idUser = api.createUser(UserType.DEFAULT).andReturn().getResponse().getContentAsString().trim();
		
		for (int i = 0; i < instanceNumber; i++) {
			api.createInstance(isbn, Type.FREE);
		}
		
		for (int i = 0; i < instanceNumber; i++) {
			api.createLend(Long.valueOf(idUser), Long.valueOf(idBook), time)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
	}
	
	public void lendRessearchUser(
			@ForAll @AlphaChars @StringLength(min = 1, max = 255) String title,
			@ForAll @BigRange(min = "1", max = "100") BigDecimal price,
			@ForAll @Size(10) List<@NumericChars @Unique Character> listCharactersIsbn,
			@ForAll @IntRange(min=1,max=5) int instanceNumber,
			@ForAll @IntRange(min = 1,max = 60) int time,
			@ForAll Type typeInstance)
			throws Exception {
		
		String isbn = listCharactersIsbn.stream()
				.map(c -> c.toString()).collect(Collectors.joining());
		
		String idBook = api.createBook(title, price, isbn).andReturn().getResponse().getContentAsString().trim();
		String idUser = api.createUser(UserType.RESSEARCH).andReturn().getResponse().getContentAsString().trim();
		
		for (int i = 0; i < instanceNumber; i++) {
			api.createInstance(isbn, typeInstance);
		}
		
		for (int i = 0; i < instanceNumber; i++) {
			api.createLend(Long.valueOf(idUser), Long.valueOf(idBook), time)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
	}
	
	

}
