package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.webapp.configs.AbstractControllerTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestTagResourceController extends AbstractControllerTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTags/After.sql"})
    public void getAllTrackedTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/tracked")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].name", Is.is("Java")))
                .andExpect(jsonPath("$[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$[1].id", Is.is(101)))
                .andExpect(jsonPath("$[1].name", Is.is("Kotlin")))
                .andExpect(jsonPath("$[1].description", Is.is("Tags Message 2")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTagsNoTrackedTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTagsNoTrackedTags/After.sql"})
    public void getAllTrackedTagsNoTrackedTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/tracked")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTagsNoTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTagsNoTags/After.sql"})
    public void getAllTrackedTagsNoTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/tracked")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTagsUserHasNoTrackedTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTrackedTagsUserHasNoTrackedTags/After.sql"})
    public void getAllTrackedTagsUserHasNoTrackedTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/tracked")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTags/After.sql"})
    public void getAllIgnoredTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/ignored")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].name", Is.is("Java")))
                .andExpect(jsonPath("$[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$[1].id", Is.is(101)))
                .andExpect(jsonPath("$[1].name", Is.is("Kotlin")))
                .andExpect(jsonPath("$[1].description", Is.is("Tags Message 2")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTagsNoIgnoredTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTagsNoIgnoredTags/After.sql"})
    public void getAllIgnoredTagsNoIgnoredTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/ignored")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTagsNoTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTagsNoTags/After.sql"})
    public void getAllIgnoredTagsNoTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/ignored")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTagsUserHasNoIgnoredTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllIgnoredTagsUserHasNoIgnoredTags/After.sql"})
    public void getAllIgnoredTagsUserHasNoIgnoredTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/ignored")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePagination/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePagination/After.sql"})
    public void getAllTagsOrderByNamePagination() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&items=10&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(2)))
                .andExpect(jsonPath("currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("totalPageCount", Is.is(1)))
                .andExpect(jsonPath("totalResultCount", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-05T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(0)))
                .andExpect(jsonPath("itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[1].id", Is.is(102)))
                .andExpect(jsonPath("$.items[1].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[1].description", Is.is("Tags Message 3")))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$.items[1].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountWeekDay", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoPage/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoPage/After.sql"})
    public void getAllTagsOrderByNamePaginationNoPage() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?items=10&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationPage2/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationPage2/After.sql"})
    public void getAllTagsOrderByNamePaginationPage2() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=2&items=1&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("totalPageCount", Is.is(2)))
                .andExpect(jsonPath("totalResultCount", Is.is(2)))
                .andExpect(jsonPath("itemsOnPage", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 3")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoItems/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoItems/After.sql"})
    public void getAllTagsOrderByNamePaginationNoItems() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(2)))
                .andExpect(jsonPath("currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("totalPageCount", Is.is(1)))
                .andExpect(jsonPath("totalResultCount", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-05T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(0)))
                .andExpect(jsonPath("itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[1].id", Is.is(102)))
                .andExpect(jsonPath("$.items[1].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[1].description", Is.is("Tags Message 3")))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$.items[1].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountWeekDay", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoFilter/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoFilter/After.sql"})
    public void getAllTagsOrderByNamePaginationNoFilter() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&items=10")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(3)))
                .andExpect(jsonPath("currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("totalPageCount", Is.is(1)))
                .andExpect(jsonPath("totalResultCount", Is.is(3)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-05T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(0)))
                .andExpect(jsonPath("itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[1].id", Is.is(102)))
                .andExpect(jsonPath("$.items[1].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[1].description", Is.is("Tags Message 3")))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$.items[1].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountWeekDay", Is.is(0)))
                .andExpect(jsonPath("$.items[2].id", Is.is(101)))
                .andExpect(jsonPath("$.items[2].name", Is.is("Kotlin")))
                .andExpect(jsonPath("$.items[2].description", Is.is("Tags Message 2")))
                .andExpect(jsonPath("$.items[2].persistDateTime", Is.is("2022-11-03T00:00:00")))
                .andExpect(jsonPath("$.items[2].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[2].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[2].questionCountWeekDay", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoTags/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoTags/After.sql"})
    public void getAllTagsOrderByNamePaginationNoTags() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&items=10")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoQuestion/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationNoQuestion/After.sql"})
    public void getAllTagsOrderByNamePaginationNoQuestion() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&items=10&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("totalPageCount", Is.is(1)))
                .andExpect(jsonPath("totalResultCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-05T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(0)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(0)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(0)))
                .andExpect(jsonPath("itemsOnPage", Is.is(10)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationQuestionCount3OneDayNoWeek/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationQuestionCount3OneDayNoWeek/After.sql"})
    public void getAllTagsOrderByNamePaginationQuestionCount3OneDayNoWeek() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&items=10&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("totalPageCount", Is.is(1)))
                .andExpect(jsonPath("totalResultCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-05T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(3)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(3)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(0)))
                .andExpect(jsonPath("itemsOnPage", Is.is(10)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationQuestionCountNoOneDay3Week/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationQuestionCountNoOneDay3Week/After.sql"})
    public void getAllTagsOrderByNamePaginationQuestionCountNoOneDay3Week() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&items=10&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("totalPageCount", Is.is(1)))
                .andExpect(jsonPath("totalResultCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-05T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(3)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(0)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(3)))
                .andExpect(jsonPath("itemsOnPage", Is.is(10)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationOrderBySizeCountQuestions/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestTagResourceController/getAllTagsOrderByNamePaginationOrderBySizeCountQuestions/After.sql"})
    public void getAllTagsOrderByNamePaginationOrderBySizeCountQuestions() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        get("/api/user/tag/name?page=1&items=10&filter=Java")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", Is.is(2)))
                .andExpect(jsonPath("currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("totalPageCount", Is.is(1)))
                .andExpect(jsonPath("totalResultCount", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Tags Message 2")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-04T00:00:00")))
                .andExpect(jsonPath("$.items[0].questionsCount", Is.is(2)))
                .andExpect(jsonPath("$.items[0].questionCountOneDay", Is.is(2)))
                .andExpect(jsonPath("$.items[0].questionCountWeekDay", Is.is(0)))
                .andExpect(jsonPath("itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[1].id", Is.is(100)))
                .andExpect(jsonPath("$.items[1].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[1].description", Is.is("Tags Message 1")))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2022-11-05T00:00:00")))
                .andExpect(jsonPath("$.items[1].questionsCount", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountOneDay", Is.is(1)))
                .andExpect(jsonPath("$.items[1].questionCountWeekDay", Is.is(0)));
    }

    @Test
    @Sql(scripts = "/script/TestTagResourceController/addTrackedTag/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestTagResourceController/addTrackedTag/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addTrackedTagDoesTagExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/tag/101/tracked")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/script/TestTagResourceController/addTrackedTag/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestTagResourceController/addTrackedTag/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addTrackedTagDoesTrackedTagExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/tag/100/tracked")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(
                        post("/api/user/tag/100/tracked")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestTagResourceController/addTrackedTag/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestTagResourceController/addTrackedTag/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addTrackedTagDoesDBQueryConfirmAddition() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/tag/100/tracked")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk());
        Assertions.assertTrue((Boolean)
                entityManager
                        .createNativeQuery("SELECT count(*) > 0 FROM tag_tracked WHERE tracked_tag_id = 100")
                        .getSingleResult()
        );
    }

    @Test
    @Sql(scripts = "/script/TestTagResourceController/addIgnoredTag/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestTagResourceController/addIgnoredTag/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addIgnoredTagDoesTagExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/tag/101/ignored")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/script/TestTagResourceController/addIgnoredTag/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestTagResourceController/addIgnoredTag/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addIgnoredTagDoesIgnoredTagExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/tag/100/ignored")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(
                        post("/api/user/tag/100/ignored")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestTagResourceController/addIgnoredTag/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestTagResourceController/addIgnoredTag/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addIgnoredTagDoesDBQueryConfirmAddition() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/tag/100/ignored")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk());
        Assertions.assertTrue((Boolean)
                entityManager
                        .createNativeQuery("SELECT count(*) > 0 FROM tag_ignore WHERE ignored_tag_id = 100")
                        .getSingleResult()
        );
    }
}
