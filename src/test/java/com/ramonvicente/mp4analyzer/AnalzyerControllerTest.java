package com.ramonvicente.mp4analyzer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class AnalzyerControllerTest {

  @Autowired
	private MockMvc mockMvc;

  @Test
  @DisplayName("Return status ok when analyze mp4 url.")
  public void returnStatusOkWhenAnalyzeMp4() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
										.get("/api/analyzes")
                    .param("url", "https://demo.castlabs.com/tmp/text0.mp4")
										.contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers
										.status()
										.isOk());
  }
}