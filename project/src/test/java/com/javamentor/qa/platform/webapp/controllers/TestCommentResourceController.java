package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.webapp.configs.AbstractControllerTest;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestCommentResourceController extends AbstractControllerTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addCommentByAnotherUserToQuestion/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addCommentByAnotherUserToQuestion/After.sql"})
    public void addCommentByAnotherUserToQuestion() throws Exception {

        String userToken = getToken("1@mail.com", "pass1");

        mockMvc.perform(
                        post("/api/user/comment/question/100")
                                .header(AUTHORIZATION, userToken)
                                .content("new comment")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        String result = super.entityManager.createQuery("SELECT cq.comment.text FROM CommentQuestion cq WHERE cq.question.id = 100 AND cq.comment.user.id = 101", String.class)
                .getSingleResult();

        Assertions.assertEquals(result, "new comment");
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addCommentToExistingCommentsToQuestion/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addCommentToExistingCommentsToQuestion/After.sql"})
    public void addCommentToExistingCommentsToQuestion() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        post("/api/user/comment/question/100")
                                .header(AUTHORIZATION, userToken)
                                .content("new comment")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        int result = super.entityManager.createQuery("FROM CommentQuestion cq WHERE cq.question.id = 100 AND cq.comment.user.id = 100")
                .getResultList()
                .size();

        Assertions.assertEquals(result, 3);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addEmptyCommentToQuestion/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addEmptyCommentToQuestion/After.sql"})
    public void addEmptyCommentToQuestion() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        post("/api/user/comment/question/100")
                                .header(AUTHORIZATION, userToken)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addCommentToNotExistQuestion/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/addCommentToNotExistQuestion/After.sql"})
    public void addCommentToNotExistQuestion() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(
                        post("/api/user/comment/question/100")
                                .header(AUTHORIZATION, userToken)
                                .content("new comment")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithOutPageParam/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithOutPageParam/After.sql"})
    public void getCommentsQuestionByQuestionIdPaginationWithOutPageParam() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/question/100")
                        .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithOutItemsParam/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithOutItemsParam/After.sql"})
    public void getCommentsQuestionByQuestionIdPaginationWithOutItemsParam() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/question/100?currentPage=1")
                        .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("comment 100")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[0].dateAdded", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.items[4].id", Is.is(104)))
                .andExpect(jsonPath("$.items[4].comment", Is.is("comment 104")))
                .andExpect(jsonPath("$.items[4].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[4].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[4].dateAdded", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.items[9].id", Is.is(109)))
                .andExpect(jsonPath("$.items[9].comment", Is.is("comment 109")))
                .andExpect(jsonPath("$.items[9].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[9].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[9].dateAdded", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithPage2Items1/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithPage2Items1/After.sql"})
    public void getCommentsQuestionByQuestionIdPaginationWithPage2Items1() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(get("/api/user/comment/question/100?currentPage=2&items=1")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(22)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("comment 101")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[0].dateAdded", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(1)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithPage1Items50/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsQuestionByQuestionIdPaginationWithPage1Items50/After.sql"})
    public void getCommentsQuestionByQuestionIdPaginationWithPage1Items50() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(get("/api/user/comment/question/100?currentPage=1&items=50")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(22)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("comment 100")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[0].dateAdded", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.items[11].id", Is.is(111)))
                .andExpect(jsonPath("$.items[11].comment", Is.is("comment 111")))
                .andExpect(jsonPath("$.items[11].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[11].fullName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[11].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[11].dateAdded", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.items[21].id", Is.is(121)))
                .andExpect(jsonPath("$.items[21].comment", Is.is("comment 121")))
                .andExpect(jsonPath("$.items[21].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[21].fullName", Is.is("user 100")))
                .andExpect(jsonPath("$.items[21].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[21].dateAdded", Matchers.containsString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(50)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsNotExistQuestionByQuestionId/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestCommentResourceController/getCommentsNotExistQuestionByQuestionId/After.sql"})
    public void getCommentsNotExistQuestionByQuestionId() throws Exception {

        String userToken = getToken("0@mail.com", "pass0");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/question/101?currentPage=1&items=10")
                        .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getCommentsPaginationByAnswerId() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/answer/100?currentPage=1&items=3")
                        .header(AUTHORIZATION, userToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(8)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(3)))
                .andExpect(jsonPath("$.items[0].id", Is.is(121)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("Test comment by answerId = 121")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[1].id", Is.is(120)))
                .andExpect(jsonPath("$.items[1].comment", Is.is("Test comment by answerId = 120")))
                .andExpect(jsonPath("$.items[1].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(15)))
                .andExpect(jsonPath("$.items[2].id", Is.is(119)))
                .andExpect(jsonPath("$.items[2].comment", Is.is("Test comment by answerId = 119")))
                .andExpect(jsonPath("$.items[2].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[2].reputation", Is.is(15)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(3)));
    }

    @Test
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getCommentsPaginationByAnswerIdSortCheck() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/answer/100?currentPage=1&items=5")
                        .header(AUTHORIZATION, userToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(5)))
                .andExpect(jsonPath("$.items[0].id", Is.is(121)))
                .andExpect(jsonPath("$.items[0].dateAdded", Is.is("2022-01-22T00:00:00")))
                .andExpect(jsonPath("$.items[1].id", Is.is(120)))
                .andExpect(jsonPath("$.items[1].dateAdded", Is.is("2022-01-21T00:00:00")))
                .andExpect(jsonPath("$.items[2].id", Is.is(119)))
                .andExpect(jsonPath("$.items[2].dateAdded", Is.is("2022-01-20T00:00:00")))
                .andExpect(jsonPath("$.items[3].id", Is.is(118)))
                .andExpect(jsonPath("$.items[3].dateAdded", Is.is("2022-01-19T00:00:00")))
                .andExpect(jsonPath("$.items[4].id", Is.is(117)))
                .andExpect(jsonPath("$.items[4].dateAdded", Is.is("2022-01-18T00:00:00")))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(5)));
    }

    @Test
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationForNonExistentAnswer/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationForNonExistentAnswer/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getCommentsPaginationForNonExistentAnswer() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/answer/100?currentPage=1&items=10")
                        .header(AUTHORIZATION, userToken))
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithoutPageParameters/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithoutPageParameters/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getCommentsPaginationByAnswerIdWithoutPageParameters() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/question/100")
                        .header(AUTHORIZATION, userToken))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithoutItemParameters/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithoutItemParameters/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getCommentsPaginationByAnswerIdWithoutItemParameters() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/comment/answer/100?currentPage=1")
                        .header(AUTHORIZATION, userToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(121)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("Test comment by answerId = 121")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)));
    }

    @Test
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithPage1Items50/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithPage1Items50/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getCommentsPaginationByAnswerIdWithPage1Items50() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/comment/answer/100?currentPage=1&items=50")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(22)))
                .andExpect(jsonPath("$.items[0].id", Is.is(121)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("Test comment by answerId = 121")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(50)));
    }

    @Test
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithPage2Items1/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestCommentResourceController/" +
            "getCommentsPaginationByAnswerIdWithPage2Items1/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void getCommentsPaginationByAnswerIdWithPage2Items1() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/comment/answer/100?currentPage=2&items=1")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(22)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(22)))
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(120)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("Test comment by answerId = 120")))
                .andExpect(jsonPath("$.items[0].userId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(1)));
    }
}
