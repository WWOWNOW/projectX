package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.webapp.configs.AbstractControllerTest;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TestAnswerResourceController extends AbstractControllerTest {


    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/After.sql",
            executionPhase = AFTER_TEST_METHOD)

    public void successfulAnsweredQuestionById() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(get("/api/user/question/100/answer")
//                        .param("questionId", "101")
                        .header(AUTHORIZATION, token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void illegalFormatQuestionId() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(get("/api/user/question/id/answer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/deleteAnswerById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/deleteAnswerById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteAnswerById() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(delete("/api/user/question/100/answer/200")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void notFoundAnswerId() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(delete("/api/user/question/100/answer/201")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/getAllAnswerByQuestionId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void illegalFormatAnswerId() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(delete("/api/user/question/100/answer/id")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/insertUpVote/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/insertUpVote/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void successfulVoteInserting() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/question/100/answer/200/upVote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/insertUpVote/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/insertUpVote/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void alreadyVoted() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/question/100/answer/id200/upVote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/insertUpVote/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/insertUpVote/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void answerNotFound() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/question/100/answer/300/upVote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/insertDownVote/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/insertDownVote/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void successfulDownVoteInserting() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        MvcResult mvcResult = mockMvc.perform(post("/api/user/question/100/answer/200/downVote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk())
                        .andReturn();
        Assertions.assertEquals("-1", mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/insertDownVote/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/insertDownVote/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void badReqDownVoteInserting() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/question/100/answer/id200/downVote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/insertDownVote/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/insertDownVote/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void notFoundDownVoteInserting() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/question/100/answer/201/downVote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/addAnswerByQuestionId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/addAnswerByQuestionId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void questionIdNotFoundAddAnswerByQuestion() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/question/666/answer/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token)
                        .content("{\"body\":\"test_body\"}"))
                .andExpect(status().isNotFound());
    }


    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/addAnswerByQuestionId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/addAnswerByQuestionId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void successfulAddedAnswer() throws Exception {
        String token = getToken("100@mail.com", "pass100");

        MvcResult mvcResult = mockMvc.perform(post("/api/user/question/100/answer/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token)
                        .content("{\"body\":\"test_body\"}"))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("test_body"));
    }

    @Test
    @Sql(scripts = "/script/TestAnswerResourceController/addAnswerByQuestionId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestAnswerResourceController/addAnswerByQuestionId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addAnswerByQuestionIdAnswerAlreadyCounted() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/question/100/answer/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token)
                        .content("{\"body\":\"test_body\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/user/question/100/answer/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token)
                        .content("{\"body\":\"test_body\"}"))
                .andExpect(status().isBadRequest());
    }


}

