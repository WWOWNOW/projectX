package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.webapp.configs.AbstractControllerTest;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class TestMessageResourceController extends AbstractControllerTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithOutTextParam/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithOutTextParam/After.sql"})
    public void findMessagesInGlobalChatPaginationWithOutTextParam() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(get("/api/user/message/global/find?currentPage=1&items=10")
                        .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithOutPageParam/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithOutPageParam/After.sql"})
    public void findMessagesInGlobalChatPaginationWithOutPageParam() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(get("/api/user/message/global/find?text=text&items=10")
                        .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithOutItemsParam/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithOutItemsParam/After.sql"})
    public void findMessagesInGlobalChatPaginationWithOutItemsParam() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(get("/api/user/message/global/find?text=text&currentPage=1")
                        .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithPage2Items1/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithPage2Items1/After.sql"})
    public void findMessagesInGlobalChatPaginationWithPage2Items1() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(get("/api/user/message/global/find?text=message&currentPage=2&items=1")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(22)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].message", Is.is("message 101")))
                .andExpect(jsonPath("$.items[0].nickName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].image", Is.is("image 100")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(1)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithPage1Items50/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/findMessagesInGlobalChatPaginationWithPage1Items50/After.sql"})
    public void findMessagesInGlobalChatPaginationWithPage1Items50() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(get("/api/user/message/global/find?text=message&currentPage=1&items=50")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(22)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].message", Is.is("message 100")))
                .andExpect(jsonPath("$.items[0].nickName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].image", Is.is("image 100")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.items[11].id", Is.is(111)))
                .andExpect(jsonPath("$.items[11].message", Is.is("message 111")))
                .andExpect(jsonPath("$.items[11].nickName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[11].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[11].image", Is.is("image 100")))
                .andExpect(jsonPath("$.items[11].persistDateTime", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.items[21].id", Is.is(121)))
                .andExpect(jsonPath("$.items[21].message", Is.is("message 121")))
                .andExpect(jsonPath("$.items[21].nickName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[21].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[21].image", Is.is("image 100")))
                .andExpect(jsonPath("$.items[21].persistDateTime", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(50)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatMessageDontExistItems/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatMessageDontExistItems/After.sql"})
    public void getGlobalChatMessageDontExistItems() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user/message/global?page=1")
                        .header(HttpHeaders.AUTHORIZATION, userToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(2)))
                .andExpect(jsonPath("$.items.length()", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].id", Is.is(100)))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2022-10-31T00:00:00")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-01T00:00:00")))
                .andExpect(jsonPath("$.items[0].message", Is.is("Global test message 1")))
                .andExpect(jsonPath("$.items[1].message", Is.is("Global test message 2")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[1].userId", Is.is(100)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatMessageDontExistPage/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatMessageDontExistPage/After.sql"})
    public void getGlobalChatMessageDontExistPage() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user/message/global?items=1")
                                .header(HttpHeaders.AUTHORIZATION, userToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatMessageOk/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatMessageOk/After.sql"})
    public void getGlobalChatMessageOk() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user/message/global?page=1&items=10")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(2)))
                .andExpect(jsonPath("$.items.length()", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].id", Is.is(100)))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2022-10-31T00:00:00")))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-01T00:00:00")))
                .andExpect(jsonPath("$.items[0].message", Is.is("Global test message 1")))
                .andExpect(jsonPath("$.items[1].message", Is.is("Global test message 2")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[1].userId", Is.is(100)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatNoMessage/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMessageResourceController/getGlobalChatNoMessage/After.sql"})
    public void getGlobalChatNoMessage() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/user/message/global?page=1&items=1")
                                .header(HttpHeaders.AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(0)))
                .andExpect(jsonPath("$.items.length()", Is.is(0)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(1)));
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Некорректный запрос
    public void getAllMessageDtoInSingleChatSortedByPersistDateBadRequest() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/message/single/100").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Несуществующий SingleChat
    public void getAllMessageDtoInNotFoundSingleChatSortedByPersistDate() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/message/single/103").param("page", "1").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // SingleChat без сообщений
    public void getAllMessageDtoInEmptySingleChatSortedByPersistDate() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/message/single/102").param("page", "1").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(0)))
                .andExpect(jsonPath("$.items.length()").value(0))
                .andExpect(jsonPath("$.items").isEmpty())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // SingleChat с одним сообщением
    public void getAllMessageDtoInSingleChatWithOneMessageSortedByPersistDate() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/message/single/101").param("page", "1").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(1)))
                .andExpect(jsonPath("$.items.length()").value(1))
                .andExpect(jsonPath("$.items[0].id").value(105))
                .andExpect(jsonPath("$.items[0].message").value("message105"))
                .andExpect(jsonPath("$.items[0].nickName").value("nickname2"))
                .andExpect(jsonPath("$.items[0].userId").value(102))
                .andExpect(jsonPath("$.items[0].image").value("imageLink2"))
                .andExpect(jsonPath("$.items[0].persistDateTime").value("2022-10-31T00:00:00"))
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/getAllMessageDtoInSingleChatSortedByPersistDate/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // SingleChat с несколькими сообщениями, отсортированных по дате
    public void getAllMessageDtoInSingleChatSortedByPersistDate() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/message/single/100").param("page", "1").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.items.length()").value(5))
                .andExpect(jsonPath("$.items[0].id").value(104))
                .andExpect(jsonPath("$.items[0].message").value("message104"))
                .andExpect(jsonPath("$.items[0].nickName").value("nickname1"))
                .andExpect(jsonPath("$.items[0].userId").value(101))
                .andExpect(jsonPath("$.items[0].image").value("imageLink1"))
                .andExpect(jsonPath("$.items[0].persistDateTime").value("2022-10-30T00:00:00"))
                .andExpect(jsonPath("$.items[1].id").value(101))
                .andExpect(jsonPath("$.items[1].message").value("message101"))
                .andExpect(jsonPath("$.items[1].nickName").value("nickname0"))
                .andExpect(jsonPath("$.items[1].userId").value(100))
                .andExpect(jsonPath("$.items[1].image").value("imageLink0"))
                .andExpect(jsonPath("$.items[1].persistDateTime").value("2022-10-29T00:00:00"))
                .andExpect(jsonPath("$.items[2].id").value(102))
                .andExpect(jsonPath("$.items[2].message").value("message102"))
                .andExpect(jsonPath("$.items[2].nickName").value("nickname1"))
                .andExpect(jsonPath("$.items[2].userId").value(101))
                .andExpect(jsonPath("$.items[2].image").value("imageLink1"))
                .andExpect(jsonPath("$.items[2].persistDateTime").value("2022-10-28T00:00:00"))
                .andExpect(jsonPath("$.items[3].id").value(100))
                .andExpect(jsonPath("$.items[3].message").value("message100"))
                .andExpect(jsonPath("$.items[3].nickName").value("nickname0"))
                .andExpect(jsonPath("$.items[3].userId").value(100))
                .andExpect(jsonPath("$.items[3].image").value("imageLink0"))
                .andExpect(jsonPath("$.items[3].persistDateTime").value("2022-10-27T00:00:00"))
                .andExpect(jsonPath("$.items[4].id").value(103))
                .andExpect(jsonPath("$.items[4].message").value("message103"))
                .andExpect(jsonPath("$.items[4].nickName").value("nickname0"))
                .andExpect(jsonPath("$.items[4].userId").value(100))
                .andExpect(jsonPath("$.items[4].image").value("imageLink0"))
                .andExpect(jsonPath("$.items[4].persistDateTime").value("2022-10-26T00:00:00"))
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void searchByChatMessageDoesChatExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");

        mockMvc.perform(
                        get("/api/user/message/find/110?items=20&page=1&word=message")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(0)));
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void searchByChatMessageIsParameterPageMissing() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");

        mockMvc.perform(
                        get("/api/user/message/find/100?items=20&word=message")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void searchByChatMessageIsParameterWordMissing() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");

        mockMvc.perform(
                        get("/api/user/message/find/100?items=20&page=1")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void searchByChatMessageIsPaginationWorking() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");

        mockMvc.perform(
                        get("/api/user/message/find/100?items=20&page=2&word=message")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(25)))
                .andExpect(jsonPath("$.items.length()", Is.is(5)))
                .andExpect(jsonPath("$.items[0].id", Is.is(125)))
                .andExpect(jsonPath("$.items[0].message", Is.is("message125")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2022-11-03T00:00:00")))
                .andExpect(jsonPath("$.items[1].id", Is.is(123)))
                .andExpect(jsonPath("$.items[1].message", Is.is("message123")))
                .andExpect(jsonPath("$.items[1].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2022-11-02T22:22:22")))
                .andExpect(jsonPath("$.items[2].id", Is.is(124)))
                .andExpect(jsonPath("$.items[2].message", Is.is("message124")))
                .andExpect(jsonPath("$.items[2].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[2].persistDateTime", Is.is("2022-11-02T11:11:11")))
                .andExpect(jsonPath("$.items[3].id", Is.is(122)))
                .andExpect(jsonPath("$.items[3].message", Is.is("message122")))
                .andExpect(jsonPath("$.items[3].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[3].persistDateTime", Is.is("2022-11-01T22:22:22")))
                .andExpect(jsonPath("$.items[4].id", Is.is(121)))
                .andExpect(jsonPath("$.items[4].message", Is.is("message121")))
                .andExpect(jsonPath("$.items[4].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[4].persistDateTime", Is.is("2022-11-01T11:11:11")))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(20)));
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/searchByChatMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void searchByChatMessageIsCustomParameterOfItemsAccepted() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");

        mockMvc.perform(
                        get("/api/user/message/find/100?items=10&page=3&word=message")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(3)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(25)))
                .andExpect(jsonPath("$.items.length()", Is.is(5)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)));
    }
    @Test
    @Sql(scripts = "/script/TestMessageResourceController/deleteMessageStarById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/deleteMessageStarById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void successfullyDeleteMessageStarById() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(delete("/api/user/message/star")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "100")
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    @Sql(scripts = "/script/TestMessageResourceController/deleteMessageStarById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/deleteMessageStarById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void messageStarNotFoundById() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(delete("/api/user/message/star")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "101")
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
    @Test
    @Sql(scripts = "/script/TestMessageResourceController/deleteMessageStarById/Before400.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/deleteMessageStarById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void messageBelongsToAnotherUser() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(delete("/api/user/message/star")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "101")
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
    @Test
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void messageAddedToMessageStarByMessageId() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/message/100/star")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void userIsNotAChatMember() throws Exception {
        String token = getToken("1@mail.com", "pass101");
        mockMvc.perform(post("/api/user/message/100/star")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void messageNotFound() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/message/102/star")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/Before3msg.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestMessageResourceController/insertMessageToMessageStarByMessageId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void noMoreThanThreeMessageFromOneUser() throws Exception {
        String token = getToken("100@mail.com", "pass100");
        mockMvc.perform(post("/api/user/message/100/star")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}
