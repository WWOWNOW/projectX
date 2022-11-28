package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.webapp.configs.AbstractControllerTest;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.math.BigInteger;



public class TestUserResourceController extends AbstractControllerTest {

    //    test cases for API getTop10UserDtoForAnswer
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswer/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswer/After.sql"})
    public void getTop10UserDtoForAnswer() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswer")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(10)))
                .andExpect(jsonPath("$[0].id", Is.is(120)))
                .andExpect(jsonPath("$[0].email", Is.is("120@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 120")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 120")))
                .andExpect(jsonPath("$[0].city", Is.is("City 120")))
                .andExpect(jsonPath("$[0].reputation", Is.is(50)))
                .andExpect(jsonPath("$[4].id", Is.is(116)))
                .andExpect(jsonPath("$[4].email", Is.is("116@mail.com")))
                .andExpect(jsonPath("$[4].fullName", Is.is("FullName 116")))
                .andExpect(jsonPath("$[4].linkImage", Is.is("LinkImage 116")))
                .andExpect(jsonPath("$[4].city", Is.is("City 116")))
                .andExpect(jsonPath("$[4].reputation", Is.is(30)))
                .andExpect(jsonPath("$[9].id", Is.is(111)))
                .andExpect(jsonPath("$[9].email", Is.is("111@mail.com")))
                .andExpect(jsonPath("$[9].fullName", Is.is("FullName 111")))
                .andExpect(jsonPath("$[9].linkImage", Is.is("LinkImage 111")))
                .andExpect(jsonPath("$[9].city", Is.is("City 111")))
                .andExpect(jsonPath("$[9].reputation", Is.is(5)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoWithOutAnswers/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoWithOutAnswers/After.sql"})
    public void getTop10UserDtoWithOutAnswers() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswer")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(10)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].email", Is.is("100@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 100")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 100")))
                .andExpect(jsonPath("$[0].city", Is.is("City 100")))
                .andExpect(jsonPath("$[0].reputation", Is.is(0)))
                .andExpect(jsonPath("$[4].id", Is.is(104)))
                .andExpect(jsonPath("$[4].email", Is.is("104@mail.com")))
                .andExpect(jsonPath("$[4].fullName", Is.is("FullName 104")))
                .andExpect(jsonPath("$[4].linkImage", Is.is("LinkImage 104")))
                .andExpect(jsonPath("$[4].city", Is.is("City 104")))
                .andExpect(jsonPath("$[4].reputation", Is.is(0)))
                .andExpect(jsonPath("$[9].id", Is.is(109)))
                .andExpect(jsonPath("$[9].email", Is.is("109@mail.com")))
                .andExpect(jsonPath("$[9].fullName", Is.is("FullName 109")))
                .andExpect(jsonPath("$[9].linkImage", Is.is("LinkImage 109")))
                .andExpect(jsonPath("$[9].city", Is.is("City 109")))
                .andExpect(jsonPath("$[9].reputation", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerWithOnly1User/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerWithOnly1User/After.sql"})
    public void getTop10UserDtoForAnswerWithOnly1User() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswer")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].email", Is.is("100@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 100")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 100")))
                .andExpect(jsonPath("$[0].city", Is.is("City 100")))
                .andExpect(jsonPath("$[0].reputation", Is.is(0)));
    }

    //    test cases for API getTop10UserDtoForAnswerOnTheMonth
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheMonth/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheMonth/After.sql"})
    public void getTop10UserDtoForAnswerOnTheMonth() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswerOnTheMonth")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(10)))
                .andExpect(jsonPath("$[0].id", Is.is(120)))
                .andExpect(jsonPath("$[0].email", Is.is("120@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 120")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 120")))
                .andExpect(jsonPath("$[0].city", Is.is("City 120")))
                .andExpect(jsonPath("$[0].reputation", Is.is(50)))
                .andExpect(jsonPath("$[4].id", Is.is(116)))
                .andExpect(jsonPath("$[4].email", Is.is("116@mail.com")))
                .andExpect(jsonPath("$[4].fullName", Is.is("FullName 116")))
                .andExpect(jsonPath("$[4].linkImage", Is.is("LinkImage 116")))
                .andExpect(jsonPath("$[4].city", Is.is("City 116")))
                .andExpect(jsonPath("$[4].reputation", Is.is(30)))
                .andExpect(jsonPath("$[9].id", Is.is(111)))
                .andExpect(jsonPath("$[9].email", Is.is("111@mail.com")))
                .andExpect(jsonPath("$[9].fullName", Is.is("FullName 111")))
                .andExpect(jsonPath("$[9].linkImage", Is.is("LinkImage 111")))
                .andExpect(jsonPath("$[9].city", Is.is("City 111")))
                .andExpect(jsonPath("$[9].reputation", Is.is(5)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoOnTheMonthWithOutAnswers/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoOnTheMonthWithOutAnswers/After.sql"})
    public void getTop10UserDtoOnTheMonthWithOutAnswers() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswerOnTheMonth")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(10)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].email", Is.is("100@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 100")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 100")))
                .andExpect(jsonPath("$[0].city", Is.is("City 100")))
                .andExpect(jsonPath("$[0].reputation", Is.is(0)))
                .andExpect(jsonPath("$[4].id", Is.is(104)))
                .andExpect(jsonPath("$[4].email", Is.is("104@mail.com")))
                .andExpect(jsonPath("$[4].fullName", Is.is("FullName 104")))
                .andExpect(jsonPath("$[4].linkImage", Is.is("LinkImage 104")))
                .andExpect(jsonPath("$[4].city", Is.is("City 104")))
                .andExpect(jsonPath("$[4].reputation", Is.is(0)))
                .andExpect(jsonPath("$[9].id", Is.is(109)))
                .andExpect(jsonPath("$[9].email", Is.is("109@mail.com")))
                .andExpect(jsonPath("$[9].fullName", Is.is("FullName 109")))
                .andExpect(jsonPath("$[9].linkImage", Is.is("LinkImage 109")))
                .andExpect(jsonPath("$[9].city", Is.is("City 109")))
                .andExpect(jsonPath("$[9].reputation", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheMonthWithOnly1User/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheMonthWithOnly1User/After.sql"})
    public void getTop10UserDtoForAnswerOnTheMonthWithOnly1User() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswerOnTheMonth")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].email", Is.is("100@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 100")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 100")))
                .andExpect(jsonPath("$[0].city", Is.is("City 100")))
                .andExpect(jsonPath("$[0].reputation", Is.is(0)));
    }

    //    test cases for API getTop10UserDtoForAnswerOnTheYear
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheYear/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheYear/After.sql"})
    public void getTop10UserDtoForAnswerOnTheYear() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswerOnTheYear")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(10)))
                .andExpect(jsonPath("$[0].id", Is.is(120)))
                .andExpect(jsonPath("$[0].email", Is.is("120@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 120")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 120")))
                .andExpect(jsonPath("$[0].city", Is.is("City 120")))
                .andExpect(jsonPath("$[0].reputation", Is.is(50)))
                .andExpect(jsonPath("$[4].id", Is.is(116)))
                .andExpect(jsonPath("$[4].email", Is.is("116@mail.com")))
                .andExpect(jsonPath("$[4].fullName", Is.is("FullName 116")))
                .andExpect(jsonPath("$[4].linkImage", Is.is("LinkImage 116")))
                .andExpect(jsonPath("$[4].city", Is.is("City 116")))
                .andExpect(jsonPath("$[4].reputation", Is.is(30)))
                .andExpect(jsonPath("$[9].id", Is.is(111)))
                .andExpect(jsonPath("$[9].email", Is.is("111@mail.com")))
                .andExpect(jsonPath("$[9].fullName", Is.is("FullName 111")))
                .andExpect(jsonPath("$[9].linkImage", Is.is("LinkImage 111")))
                .andExpect(jsonPath("$[9].city", Is.is("City 111")))
                .andExpect(jsonPath("$[9].reputation", Is.is(5)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoOnTheYearWithOutAnswers/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoOnTheYearWithOutAnswers/After.sql"})
    public void getTop10UserDtoOnTheYearWithOutAnswers() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswerOnTheYear")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(10)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].email", Is.is("100@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 100")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 100")))
                .andExpect(jsonPath("$[0].city", Is.is("City 100")))
                .andExpect(jsonPath("$[0].reputation", Is.is(0)))
                .andExpect(jsonPath("$[4].id", Is.is(104)))
                .andExpect(jsonPath("$[4].email", Is.is("104@mail.com")))
                .andExpect(jsonPath("$[4].fullName", Is.is("FullName 104")))
                .andExpect(jsonPath("$[4].linkImage", Is.is("LinkImage 104")))
                .andExpect(jsonPath("$[4].city", Is.is("City 104")))
                .andExpect(jsonPath("$[4].reputation", Is.is(0)))
                .andExpect(jsonPath("$[9].id", Is.is(109)))
                .andExpect(jsonPath("$[9].email", Is.is("109@mail.com")))
                .andExpect(jsonPath("$[9].fullName", Is.is("FullName 109")))
                .andExpect(jsonPath("$[9].linkImage", Is.is("LinkImage 109")))
                .andExpect(jsonPath("$[9].city", Is.is("City 109")))
                .andExpect(jsonPath("$[9].reputation", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheYearWithOnly1User/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getTop10UserDtoForAnswerOnTheYearWithOnly1User/After.sql"})
    public void getTop10UserDtoForAnswerOnTheYearWithOnly1User() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/getTop10UserDtoForAnswerOnTheYear")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].email", Is.is("100@mail.com")))
                .andExpect(jsonPath("$[0].fullName", Is.is("FullName 100")))
                .andExpect(jsonPath("$[0].linkImage", Is.is("LinkImage 100")))
                .andExpect(jsonPath("$[0].city", Is.is("City 100")))
                .andExpect(jsonPath("$[0].reputation", Is.is(0)));
    }

    //    test cases for API outputOfAllTheLatestResponsesForTheWeek

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/outputOfAllTheLatestResponsesForTheWeek/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/outputOfAllTheLatestResponsesForTheWeek/After.sql"})
    public void outputOfAllTheLatestResponsesForTheWeek() throws Exception {

        String userToken = getToken("101@mail.com", "pass0");

        mockMvc.perform(get("/api/user/profile/answers/week")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(4)))
                .andExpect(jsonPath("$[0].answerId", Is.is(105)))
                .andExpect(jsonPath("$[0].questionId", Is.is(102)))
                .andExpect(jsonPath("$[0].countAnswerVote", Is.is(1)))
                .andExpect(jsonPath("$[0].persistDate", Matchers.containsString(LocalDate.now().minusDays(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$[0].htmlBody", Is.is("Question 102 Answer body 2")))
                .andExpect(jsonPath("$[3].answerId", Is.is(108)))
                .andExpect(jsonPath("$[3].questionId", Is.is(102)))
                .andExpect(jsonPath("$[3].countAnswerVote", Is.is(-1)))
                .andExpect(jsonPath("$[3].persistDate", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$[3].htmlBody", Is.is("Question 102 Answer body 3")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/outputIfNotExistResponses/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/outputIfNotExistResponses/After.sql"})
    public void outputIfNotExistResponses() throws Exception {

        String userToken = getToken("100@mail.com", "pass0");

        mockMvc.perform(get("/api/user/profile/answers/week")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserById/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserById/Before.sql"})
    @Test
    // Вызов существующего юзера
    public void  getUserById() throws Exception{
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/100").header(AUTHORIZATION, userToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(100))
                .andExpect(jsonPath("email").value("0@mail.com"))
                .andExpect(jsonPath("fullName").value("User0"))
                .andExpect(jsonPath("linkImage").value("ImageLink0"))
                .andExpect(jsonPath("city").value("city0"))
                .andExpect(jsonPath("reputation").value(0))
                .andExpect(jsonPath("topTags").isEmpty())
                .andDo(print());
    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getNotExistUserById/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getNotExistUserById/Before.sql"})
    @Test
    // Вызов несуществующего юзера
    public void  getNotExistUserById() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/104").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"/script/TestUserResourceController/getUserByIncorrectId/After.sql"})
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"/script/TestUserResourceController/getUserByIncorrectId/Before.sql"})
        @Test
        //Некорректно переданный id
        public void  getUserByIncorrectId() throws Exception {
            String userToken = getToken("0@mail.com", "pass0");
            mockMvc.perform(get("/api/user/104sadasd").header(AUTHORIZATION, userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                        scripts = {"/script/TestUserResourceController/getDeletedUserById/After.sql"})
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                        scripts = {"/script/TestUserResourceController/getDeletedUserById/Before.sql"})
        @Test
        //Удаленный пользователь
        public void  getDeletedUserById() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/101").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUsersOrderByPersistDatePaginationCorrect/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUsersOrderByPersistDatePaginationCorrect/Before.sql"})
    @Test
    // Правильный запрос
    public void getAllUsersOrderByPersistDatePaginationCorrect() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/new").param("page", "1").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(2)))
                .andExpect(jsonPath("items.length()").value(2))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].email", Is.is("1@mail.com")))
                .andExpect(jsonPath("$.items[1].email", Is.is("0@mail.com")))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("User1")))
                .andExpect(jsonPath("$.items[1].fullName", Is.is("User0")))
                .andExpect(jsonPath("$.items[0].linkImage", Is.is("ImageLink1")))
                .andExpect(jsonPath("$.items[1].linkImage", Is.is("ImageLink0")))
                .andExpect(jsonPath("$.items[0].city", Is.is("city1")))
                .andExpect(jsonPath("$.items[1].city", Is.is("city0")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(0)))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(0)))
                .andExpect(jsonPath("$.items[0].topTags").isEmpty())
                .andExpect(jsonPath("$.items[1].topTags").isEmpty())

                .andDo(print());
        Assertions.assertEquals("2009-03-26 00:00:00.0",
                entityManager
                        .createNativeQuery("SELECT persist_date FROM user_entity WHERE id = 101")
                        .getSingleResult().toString()
                );
        Assertions.assertEquals("1999-03-16 00:00:00.0",
                entityManager
                        .createNativeQuery("SELECT persist_date FROM user_entity WHERE id = 100")
                        .getSingleResult().toString()
        );
    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUsersOrderByPersistDatePaginationIncorrect/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUsersOrderByPersistDatePaginationIncorrect/Before.sql"})
    @Test
    // Не введен параметр page или некорректный запрос
    public void getAllUsersOrderByPersistDatePaginationIncorrect() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/new").param("page","sadasd").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUserSortedByReputationCorrect/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUserSortedByReputationCorrect/Before.sql"})
    @Test

    // Правильный запрос

    public void getPageAllUserSortedByReputationCorrect() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/reputation").param("page", "1").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("items.length()").value(4))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].id", Is.is(103)))
                .andExpect(jsonPath("$.items[2].id", Is.is(102)))
                .andExpect(jsonPath("$.items[3].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("User1")))
                .andExpect(jsonPath("$.items[1].fullName", Is.is("User3")))
                .andExpect(jsonPath("$.items[2].fullName", Is.is("User2")))
                .andExpect(jsonPath("$.items[3].fullName", Is.is("User0")))
                .andExpect(jsonPath("$.items[0].linkImage", Is.is("ImageLink1")))
                .andExpect(jsonPath("$.items[1].linkImage", Is.is("ImageLink3")))
                .andExpect(jsonPath("$.items[2].linkImage", Is.is("ImageLink2")))
                .andExpect(jsonPath("$.items[3].linkImage", Is.is("ImageLink0")))
                .andExpect(jsonPath("$.items[0].city", Is.is("city1")))
                .andExpect(jsonPath("$.items[1].city", Is.is("city3")))
                .andExpect(jsonPath("$.items[2].city", Is.is("city2")))
                .andExpect(jsonPath("$.items[3].city", Is.is("city0")))
                .andExpect(jsonPath("$.items[0].email", Is.is("1@mail.com")))
                .andExpect(jsonPath("$.items[1].email", Is.is("3@mail.com")))
                .andExpect(jsonPath("$.items[2].email", Is.is("2@mail.com")))
                .andExpect(jsonPath("$.items[3].email", Is.is("0@mail.com")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(10)))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(3)))
                .andExpect(jsonPath("$.items[2].reputation", Is.is(0)))
                .andExpect(jsonPath("$.items[3].reputation", Is.is(-5)))
                .andExpect(jsonPath("$.items[0].topTags").isEmpty())
                .andExpect(jsonPath("$.items[1].topTags").isEmpty())
                .andExpect(jsonPath("$.items[2].topTags").isEmpty())
                .andExpect(jsonPath("$.items[3].topTags").isEmpty())
                .andDo(print());
    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUserSortedByReputationIncorrect/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUserSortedByReputationIncorrect/Before.sql"})
    @Test
    // Не введен параметр page или некорректный запрос
    public void getPageAllUserSortedByReputationIncorrect() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/reputation").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());


    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUsersSortedByVoteCorrect/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUsersSortedByVoteCorrect/Before.sql"})
    @Test
    // Правильный запрос
    public void getPageAllUsersSortedByVoteCorrect() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/vote").param("page", "1").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("items.length()").value(4))
                .andExpect(jsonPath("$.items[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[1].id", Is.is(101)))
                .andExpect(jsonPath("$.items[2].id", Is.is(103)))
                .andExpect(jsonPath("$.items[3].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].email", Is.is("2@mail.com")))
                .andExpect(jsonPath("$.items[1].email", Is.is("1@mail.com")))
                .andExpect(jsonPath("$.items[2].email", Is.is("3@mail.com")))
                .andExpect(jsonPath("$.items[3].email", Is.is("0@mail.com")))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("User2")))
                .andExpect(jsonPath("$.items[1].fullName", Is.is("User1")))
                .andExpect(jsonPath("$.items[2].fullName", Is.is("User3")))
                .andExpect(jsonPath("$.items[3].fullName", Is.is("User0")))
                .andExpect(jsonPath("$.items[0].city", Is.is("city2")))
                .andExpect(jsonPath("$.items[1].city", Is.is("city1")))
                .andExpect(jsonPath("$.items[2].city", Is.is("city3")))
                .andExpect(jsonPath("$.items[3].city", Is.is("city0")))
                .andExpect(jsonPath("$.items[0].email", Is.is("2@mail.com")))
                .andExpect(jsonPath("$.items[1].email", Is.is("1@mail.com")))
                .andExpect(jsonPath("$.items[2].email", Is.is("3@mail.com")))
                .andExpect(jsonPath("$.items[3].email", Is.is("0@mail.com")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(0)))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(10)))
                .andExpect(jsonPath("$.items[2].reputation", Is.is(3)))
                .andExpect(jsonPath("$.items[3].reputation", Is.is(-5)))
                .andExpect(jsonPath("$.items[0].topTags").isEmpty())
                .andExpect(jsonPath("$.items[1].topTags").isEmpty())
                .andExpect(jsonPath("$.items[2].topTags").isEmpty())
                .andExpect(jsonPath("$.items[3].topTags").isEmpty())
                .andDo(print());

        // Проверка сортировки по сумме голосов
        Assertions.assertEquals(BigDecimal.valueOf(2),
                entityManager
                        .createNativeQuery(
                                "SELECT sum((SELECT COALESCE(sum(case vote  when 'UP_VOTE' then 1 else -1 end),0)" +
                                " FROM votes_on_questions JOIN question ON votes_on_questions.question_id = question.id" +
                                " WHERE question.user_id = 102) + " +
                                "(SELECT COALESCE(sum(case vote  when 'UP_VOTE' then 1 else -1 end),0)" +
                                " FROM votes_on_answers JOIN answer ON votes_on_answers.answer_id = answer.id " +
                                        "WHERE answer.user_id = 102))")
                        .getSingleResult()

        );

        Assertions.assertEquals(BigInteger.valueOf(1),
                entityManager
                        .createNativeQuery("SELECT COALESCE(sum(case vote  when 'UP_VOTE' then 1 else -1 end),0)" +
                                " FROM votes_on_questions JOIN question ON votes_on_questions.question_id = question.id" +
                                " WHERE question.user_id = 101")
                        .getSingleResult()

        );

        Assertions.assertEquals(BigInteger.valueOf(-1),
                entityManager
                        .createNativeQuery("SELECT COALESCE(sum(case vote  when 'UP_VOTE' then 1 else -1 end),0) " +
                                "FROM votes_on_questions JOIN question ON votes_on_questions.question_id = question.id " +
                                "WHERE question.user_id = 103")
                        .getSingleResult()

        );

        Assertions.assertEquals(BigDecimal.valueOf(-2),
                entityManager
                        .createNativeQuery(
                                "SELECT sum((SELECT COALESCE(sum(case vote  when 'UP_VOTE' then 1 else -1 end),0) " +
                                        "FROM votes_on_questions JOIN question ON votes_on_questions.question_id = question.id " +
                                        "WHERE question.user_id = 100) + " +
                                "(SELECT COALESCE(sum(case vote  when 'UP_VOTE' then 1 else -1 end),0)" +
                                        " FROM votes_on_answers JOIN answer ON votes_on_answers.answer_id = answer.id" +
                                        " WHERE answer.user_id = 100))")
                        .getSingleResult()

        );
    }
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUsersSortedByVoteIncorrect/After.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getPageAllUsersSortedByVoteIncorrect/Before.sql"})
    @Test
    // Не введен параметр page или некорректный запрос
    public void getPageAllUsersSortedByVoteIncorrect() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/vote").header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesUserExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/102/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("aA1#bB2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailIsUserChangingOwnPassword() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/101/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("aA1#bB2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesPasswordContainAnyLowercaseLetter() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("A1#B2#"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesPasswordContainAnyCapitalLetter() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("a1#b2#"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesPasswordContainNonLatinLetter() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("ЖaA1#bB2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesPasswordContainAnyDigit() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("aA#bB#"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesPasswordContainAnySpecialSymbols() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("aA1bB2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesPasswordContainValidSpecialSymbols() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("aA1~bB2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailIsPasswordTooShort() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("aA1#"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailIsPasswordTheSameAsPrevious() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("pass0"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/updatePasswordByEmail/After.sql"})
    public void updatePasswordByEmailDoesDBQueryReturnUpdatedPassword() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        put("/api/user/100/change/password")
                                .header(AUTHORIZATION, userToken)
                                .content("Aa1#bB2")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Assertions.assertTrue(passwordEncoder.matches("Aa1#bB2",
                (String) entityManager
                        .createNativeQuery("SELECT password FROM user_entity WHERE id = 100")
                        .getSingleResult()));
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserQuestions/Before.sql"})
    @Sql(executionPhase = AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserQuestions/After.sql"})
    public void getAllUserQuestionsDoesDataMatchApiResponse() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        get("/api/user/profile/questions")
                            .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].title", Is.is("Title 100")))
                .andExpect(jsonPath("$[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$[0].persistDate",
                        Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$[1].questionId", Is.is(101)))
                .andExpect(jsonPath("$[1].title", Is.is("Title 101")))
                .andExpect(jsonPath("$[1].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$[1].persistDate",
                        Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
    }

    @Test
    @Sql(scripts = "/script/TestUserResourceController/getAllBookMarks/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestUserResourceController/getAllBookMarks/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getAllBookMarksDoesDataMatchApiResponse() throws Exception {
        String userToken = getToken("100@mail.com", "pass0");
        mockMvc.perform(
                        get("/api/user/profile/bookmarks")
                            .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].title", Is.is("Title 100")))
                .andExpect(jsonPath("$[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$[0].countVote", Is.is(0)))
                .andExpect(jsonPath("$[0].countView", Is.is(0)))
                .andExpect(jsonPath("$[0].persistQuestionDate",
                        Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$[1].questionId", Is.is(101)))
                .andExpect(jsonPath("$[1].title", Is.is("Title 101")))
                .andExpect(jsonPath("$[1].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$[1].countVote", Is.is(0)))
                .andExpect(jsonPath("$[1].countView", Is.is(0)))
                .andExpect(jsonPath("$[1].persistQuestionDate",
                        Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserDeletedQuestions/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserDeletedQuestions/After.sql"})
    public void getAllUserDeletedQuestions() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/delete/questions")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$[0].questionId", Is.is(102)))
                .andExpect(jsonPath("$[2].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$[2].persistDate", Is.is("2022-11-02T00:00:00")))
                .andExpect(jsonPath("$[0].title", Is.is("Title 2")))
                .andExpect(jsonPath("$[2].title", Is.is("Title 0")))
                .andExpect(jsonPath("$[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$[2].countAnswer", Is.is(2)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserDeletedQuestionsNoQuestions/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserDeletedQuestionsNoQuestions/After.sql"})
    public void getAllUserDeletedQuestionsNoQuestions() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/delete/questions")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserDeletedQuestionsNoDeleteQuestions/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAllUserDeletedQuestionsNoDeleteQuestions/After.sql"})
    public void getAllUserDeletedQuestionsNoDeleteQuestions() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/delete/questions")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistory/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistory/After.sql"})
    public void getUserReputationHistory() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/reputation")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[2].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].reputation", Is.is(5)))
                .andExpect(jsonPath("$[2].reputation",Is.is(-15)))
                .andExpect(jsonPath("$[0].title", Is.is("Title")))
                .andExpect(jsonPath("$[2].title", Is.is("Title")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$[2].persistDate", Is.is("2022-11-02T00:00:00")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistoryQuestions/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistoryQuestions/After.sql"})
    public void getUserReputationHistoryQuestions() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/reputation")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[2].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].reputation", Is.is(5)))
                .andExpect(jsonPath("$[2].reputation", Is.is(-15)))
                .andExpect(jsonPath("$[0].title", Is.is("Title")))
                .andExpect(jsonPath("$[2].title", Is.is("Title")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$[2].persistDate", Is.is("2022-11-02T00:00:00")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistoryAnswer/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistoryAnswer/After.sql"})
    public void getUserReputationHistoryAnswer() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/reputation")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[2].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].reputation", Is.is(5)))
                .andExpect(jsonPath("$[2].reputation", Is.is(-15)))
                .andExpect(jsonPath("$[0].title", Is.is("Title")))
                .andExpect(jsonPath("$[2].title", Is.is("Title")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$[2].persistDate", Is.is("2022-11-02T00:00:00")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistoryNoReputation/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getUserReputationHistoryNoReputation/After.sql"})
    public void getUserReputationHistoryNoReputation() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/reputation")
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserId/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserId/After.sql"})
    public void getAmountAllAnswerWeekByUserId() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/answer/week")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(2)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdNoAnswerWeek/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdNoAnswerWeek/After.sql"})
    public void getAmountAllAnswerWeekByUserIdNoAnswerWeek() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/answer/week")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdDeleteUser/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdDeleteUser/After.sql"})
    public void getAmountAllAnswerWeekByUserIdDeleteUser() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/answer/week")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdAnswerDelete/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdAnswerDelete/After.sql"})
    public void getAmountAllAnswerWeekByUserIdAnswerDelete() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/answer/week")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdNoAnswer/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestUserResourceController/getAmountAllAnswerWeekByUserIdNoAnswer/After.sql"})
    public void getAmountAllAnswerWeekByUserIdNoAnswer() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/profile/answer/week")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(0)));
    }
}
