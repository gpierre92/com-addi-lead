package com.addi.lead.adapter.in.controller;

import com.addi.lead.adapter.in.controller.model.EvaluateRequest;
import com.addi.lead.aplication.RunApplication;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(classes = RunApplication.class)
public class LeadRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    // public static WireMockServer wiremock = new WireMockServer(8080);

    @BeforeAll
    public static void beforeAll() {
        //wiremock.start();
    }

    @AfterAll
    public static void afterAll() {
        //wiremock.stop();
    }

    @Test
    void executeEvaluateOnSuccess() throws Exception {

        String identityNumber = "12345678";

        EvaluateRequest request = new EvaluateRequest();
        request.setIdentityNumber(identityNumber);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/evaluate")
                        .contentType("application/json")
                        .content(new Gson().toJson(request)))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(mvcResult))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void executeEvaluateOnSuccessWithDelay() throws Exception {

        String identityNumber = "12345679";

        EvaluateRequest request = new EvaluateRequest();
        request.setIdentityNumber(identityNumber);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/evaluate")
                        .contentType("application/json")
                        .content(new Gson().toJson(request)))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(mvcResult))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void executeEvaluateOnErrorWithDelay() throws Exception {

        String identityNumber = "12345681";

        EvaluateRequest request = new EvaluateRequest();
        request.setIdentityNumber(identityNumber);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/evaluate")
                        .contentType("application/json")
                        .content(new Gson().toJson(request)))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isGatewayTimeout());

    }

    @Test
    void executeEvaluateOnErrorByScore() throws Exception {

        String identityNumber = "12345680";

        EvaluateRequest request = new EvaluateRequest();
        request.setIdentityNumber(identityNumber);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/evaluate")
                        .contentType("application/json")
                        .content(new Gson().toJson(request)))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(mvcResult))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());

    }
}
