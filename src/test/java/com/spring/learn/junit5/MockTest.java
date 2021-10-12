package com.spring.learn.junit5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;


public class MockTest {

    private MockMvc mockMvc;

    @BeforeAll
    public void beforeAll() {
        // mockMvc = MockMvcBuilders.webAppContextSetup(web)
    }


    @Test
    public void mockTest() throws Exception {
        // mockMvc.perform(
        // 		MockMvcRequestBuilders.post("http://localhost:8080/login")
        // 				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        // 				.param("username", "qqq")
        // 				.param("password", "www")
        //

        // String response = mockMvc.perform(get(""))
        // 		.andDo(print())
        // 		.andReturn()
        // 		.getResponse()
        // 		.getContentAsString();

        // System.out.println(response);
    }
}
