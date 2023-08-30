package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	void indexTest() throws Exception {
//		mockMvc
//				.perform(get("/"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().string("Hello"));
//	}
//
//	@Test
//	void getWithParamTest() throws Exception {
//		mockMvc
//				.perform(get("/getwithparam").param("param", "taram"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().string("Your GET param: taram"));
//	}
//
//	@Test
//	void postTest() throws Exception {
//		mockMvc
//				.perform(post("/post").content("Aleksei"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.name").value("Aleksei"))
//				.andExpect(jsonPath("$.coder").value(true));
//	}
//
//	@Test
//	void postBadRequestTest() throws Exception {
//		mockMvc
//				.perform(post("/post").content("Aleksei test"))
//				.andDo(print())
//				.andExpect(status().isBadRequest());
//	}
}
