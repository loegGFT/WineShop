package com.example.wineshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TypeControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private int typeId = 23;

    @Test
    void testTypeNormal() throws Exception {
        mockMvc.perform(get("/api/type/"+typeId)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testTypeHAL() throws Exception {
        mockMvc.perform(get("/api/type/hal/"+typeId)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testTypeHAL2() throws Exception {
        mockMvc.perform(get("/api/type/hal2/"+typeId)).andDo(print()).andExpect(status().is4xxClientError());
    }
}