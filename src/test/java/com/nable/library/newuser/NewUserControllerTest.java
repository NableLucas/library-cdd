package com.nable.library.newuser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nable.library.shared.TestApi;

@SpringBootTest
@AutoConfigureMockMvc
public class NewUserControllerTest {
	
	@Autowired
	private TestApi api;
	
	
	@DisplayName("Should be create new user")
	@ParameterizedTest
	@CsvSource({
		"DEFAULT","RESSEARCH"
	})
	void testName(String userType) throws Exception {
		ResultActions actions = api.createUser(UserType.valueOf(userType));
		actions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

}
