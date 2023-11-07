package com.myProject.springboot;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerWiremockStub {

	@Autowired
	protected MockMvc mockMvc;

	@RegisterExtension
	static WireMockExtension wireMockServer = WireMockExtension.newInstance()
			.options(
				wireMockConfig()
					.dynamicPort()
					.usingFilesUnderClasspath("wiremock")
			)
			.build();

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		System.out.println("baseUrl:" + wireMockServer.baseUrl());
		registry.add("https://jsonplaceholder.typicode.com/todos/1", wireMockServer::baseUrl);
	}

	@Test
	void shouldGetUserProfile() throws Exception {
		

		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(1)))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.title", is("delectus aut autem")))
		    .andExpect(jsonPath("$.completed", is(false)));
	}
}
