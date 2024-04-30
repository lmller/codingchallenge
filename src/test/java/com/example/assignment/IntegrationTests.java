package com.example.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class IntegrationTests {

	private static final String API_BASE_PATH = "/api/v1/quotes";

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Transactional
	@Sql("classpath:test-data.sql")
	void testGetAllQuotes_ok() throws Exception {
		MvcResult result = mockMvc.perform(get(API_BASE_PATH))
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"author\":\"Steve Jobs\",\"quote\":\"Stay hungry, stay foolish.\"}," +
						"{\"author\":\"Dean Pelton\",\"quote\":\"From now on, April 1st is now March 32nd.\"}]"))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	@Transactional
	@Sql("classpath:test-data.sql")
	void testGetQuotesByAuthor_ok() throws Exception {
		mockMvc.perform(get(API_BASE_PATH).param("author","Steve Jobs"))
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"author\":\"Steve Jobs\",\"quote\":\"Stay hungry, stay foolish.\"}]"));
	}

	@Test
	void testGetAllQuotes_noContent() throws Exception {
		mockMvc.perform(get(API_BASE_PATH))
				.andExpect(status().isNoContent());
	}

	@Test
	@Transactional
	@Sql("classpath:test-data.sql")
	void testGetQuotesByAuthor_noContent() throws Exception {
		mockMvc.perform(get(API_BASE_PATH).param("author","Steve"))
				.andExpect(status().isNoContent());
	}

	@Test
	@Transactional
	void testCreateQuote_created() throws Exception {
		mockMvc.perform(post(API_BASE_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"author\": \"Abed Nadir\",\"quote\": \"Six seasons and a movie!\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	@Transactional
	@Sql("classpath:test-data.sql")
	void testCreateDuplicateQuote_conflict() throws Exception {
		mockMvc.perform(post(API_BASE_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"author\": \"Steve Jobs\",\"quote\": \"Stay hungry, stay foolish.\"}"))
				.andExpect(status().isConflict());
	}

}
