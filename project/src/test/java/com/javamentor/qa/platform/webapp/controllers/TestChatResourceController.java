package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.webapp.configs.AbstractControllerTest;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestChatResourceController extends AbstractControllerTest {

    @Test
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void joinGroupChatDoesUserExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/chat/group/100/join")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken)
                                .content("101"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void joinGroupChatDoesChatExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/chat/group/101/join")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken)
                                .content("100"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void joinGroupChatIsUserAlreadyJoined() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/chat/group/100/join")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken)
                                .content("100"))
                .andExpect(status().isOk());
        mockMvc.perform(
                        post("/api/user/chat/group/100/join")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken)
                                .content("100"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/joinGroupChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void joinGroupChatJoinUserAndConfirm() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        post("/api/user/chat/group/100/join")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, userToken)
                                .content("100"))
                .andExpect(status().isOk());
        Assertions.assertEquals(BigInteger.valueOf(100),
                entityManager
                        .createNativeQuery("SELECT user_id FROM groupchat_has_users WHERE chat_id = 100")
                        .getSingleResult()
        );
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/updateImageInGroupChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/updateImageInGroupChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void updateImageInGroupChatDoesChatExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        patch("/api/user/chat/101/group/image")
                                .header(AUTHORIZATION, userToken)
                                .content("new_image"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/updateImageInGroupChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/updateImageInGroupChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void updateImageInGroupChatUpdateImageAndConfirm() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        patch("/api/user/chat/100/group/image")
                                .header(AUTHORIZATION, userToken)
                                .content("new_image"))
                .andExpect(status().isOk());
        Assertions.assertEquals("new_image",
                entityManager
                        .createNativeQuery("SELECT image_chat FROM group_chat WHERE chat_id = 100")
                        .getSingleResult()
        );
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Некорректный запрос
    public void receiveAllSingleChatOfUserBadRequest() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/chat/single").param("page", "1").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Ни одного SingleChat у авторизованного пользователя
    public void receiveNotExistSingleChatOfUser() throws Exception {
        String token = getToken("2@mail.com", "pass2");
        mockMvc.perform(get("/api/user/chat/single").param("page", "1")
                        .param("items", "10").header(AUTHORIZATION, token)
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
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Один SingleChat у авторизованного пользователя
    public void receiveOneSingleChatOfUser() throws Exception {
        String token = getToken("1@mail.com", "pass1");
        mockMvc.perform(get("/api/user/chat/single").param("page", "1").param("items", "10").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(1)))
                .andExpect(jsonPath("$.items.length()").value(1))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].userRecipientId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].userSenderId", Is.is(101)))
                .andExpect(jsonPath("$.items[0].name", Is.is("nickname0")))
                .andExpect(jsonPath("$.items[0].image", Is.is("imageLink0")))
                .andExpect(jsonPath("$.items[0].lastMessage", Is.is("message101")))
                .andExpect(jsonPath("$.items[0].persistDateTimeLastMessage", Is.is("2022-10-30T16:19:30.538221")))
                .andDo(print());

    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/receiveAllSingleChatOfUser/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Несколько SingleСhats у авторизованного пользователя
    public void receiveAllSingleChatOfUser() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/chat/single").param("page", "1").param("items", "10").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(2)))
                .andExpect(jsonPath("$.items.length()").value(2))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].userRecipientId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].userSenderId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].name", Is.is("nickname0")))
                .andExpect(jsonPath("$.items[0].image", Is.is("imageLink0")))
                .andExpect(jsonPath("$.items[0].lastMessage", Is.is("message100")))
                .andExpect(jsonPath("$.items[0].persistDateTimeLastMessage", Is.is("2022-10-30T16:19:31.538221")))
                .andExpect(jsonPath("$.items[1].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].userRecipientId", Is.is(100)))
                .andExpect(jsonPath("$.items[1].userSenderId", Is.is(101)))
                .andExpect(jsonPath("$.items[1].name", Is.is("nickname1")))
                .andExpect(jsonPath("$.items[1].image", Is.is("imageLink1")))
                .andExpect(jsonPath("$.items[1].lastMessage", Is.is("message101")))
                .andExpect(jsonPath("$.items[1].persistDateTimeLastMessage", Is.is("2022-10-30T16:19:30.538221")))
                .andDo(print());

    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/getGroupChatOutPutWithAllMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/getGroupChatOutPutWithAllMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Один GroupChat у авторизованного пользователя
    public void getGroupChatOutPutWithAllMessage() throws Exception {
        String token = getToken("1@mail.com", "pass1");
        mockMvc.perform(get("/api/user/chat/group").param("page", "1").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(1)))
                .andExpect(jsonPath("$.items.length()").value(1))
                .andExpect(jsonPath("$.items[0].id").value(101))
                .andExpect(jsonPath("$.items[0].chatName").value("Some group chat 101"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.id").value(103))
                .andExpect(jsonPath("$.items[0].lastMessageDto.message").value("message103"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.nickName").value("nickname0"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.userId").value(100))
                .andExpect(jsonPath("$.items[0].lastMessageDto.image").value("imageLink0"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.persistDateTime").value("2022-10-30T00:00:00"))
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/getGroupChatOutPutWithAllMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/getGroupChatOutPutWithAllMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Несколько GroupChats у авторизованного пользователья
    public void getSomeGroupChatsOutPutWithAllMessage() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(get("/api/user/chat/group").param("page", "1").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(2)))
                .andExpect(jsonPath("$.items.length()").value(2))
                .andExpect(jsonPath("$.items[0].id").value(100))
                .andExpect(jsonPath("$.items[0].chatName").value("Some group chat 100"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.id").value(101))
                .andExpect(jsonPath("$.items[0].lastMessageDto.message").value("message101"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.nickName").value("nickname1"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.userId").value(101))
                .andExpect(jsonPath("$.items[0].lastMessageDto.image").value("imageLink1"))
                .andExpect(jsonPath("$.items[0].lastMessageDto.persistDateTime").value("2022-10-28T00:00:00"))
                .andExpect(jsonPath("$.items[1].id").value(101))
                .andExpect(jsonPath("$.items[1].chatName").value("Some group chat 101"))
                .andExpect(jsonPath("$.items[1].lastMessageDto.id").value(103))
                .andExpect(jsonPath("$.items[1].lastMessageDto.message").value("message103"))
                .andExpect(jsonPath("$.items[1].lastMessageDto.nickName").value("nickname0"))
                .andExpect(jsonPath("$.items[1].lastMessageDto.userId").value(100))
                .andExpect(jsonPath("$.items[1].lastMessageDto.image").value("imageLink0"))
                .andExpect(jsonPath("$.items[1].lastMessageDto.persistDateTime").value("2022-10-30T00:00:00"))
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/getGroupChatOutPutWithAllMessage/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/getGroupChatOutPutWithAllMessage/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Ни одного GroupChat у авторизованного пользователя
    public void getGroupChatOutPutWithAllMessageWhereUserDoNotHaveChats() throws Exception {
        String token = getToken("2@mail.com", "pass2");
        mockMvc.perform(get("/api/user/chat/group").param("page", "1").header(AUTHORIZATION, token)
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
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Удаление НЕСУЩЕСТВУЮЩЕГО пользователя из СУЩЕСТВУЮЩЕГО ЧАТА
    public void deleteNotExistUserFromGroupChatByUserId() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(delete("/api/user/chat/100/group").param("userId", "103").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Пользователь или чат не найден"))
                .andDo(print());

    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Удаление СУЩЕСТВУЮЩЕГО пользователя из НЕСУЩЕСТВУЮЩЕГО чата
    public void deleteUserFromNotExistGroupChatByUserId() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(delete("/api/user/chat/101/group")
                        .param("userId", "101")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Пользователь или чат не найден"))
                .andDo(print());

    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // У текущего пользователя нет чата c переданным id
    public void deleteUserFromNotFoundGroupChatByUserId() throws Exception {
        String token = getToken("2@mail.com", "pass2");
        mockMvc.perform(delete("/api/user/chat/100/group")
                        .param("userId", "101")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("У текущего пользователя нет групового чата с groupId = 100"))
                .andDo(print());

    }

    @Test
    @Order(3)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Пользователь с заданным id существует, но его нет в групповом чате
    public void deleteNotFoundUserFromGroupChatByUserId() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(delete("/api/user/chat/100/group")
                        .param("userId", "102")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())  //404
                .andExpect(content().string("Пользователя с id = 102 нет в групповом чате с groupId = 100"))
                .andDo(print());

    }

    @Test
    @Order(2)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    // Чат не принадлежит текущему пользователю
    public void deleteUserFromNotUsersGroupChatByUserId() throws Exception {
        String token = getToken("1@mail.com", "pass1");
        mockMvc.perform(delete("/api/user/chat/100/group").param("userId", "101").header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Чат не принадлежит текущему пользователю"))
                .andDo(print());

    }

    @Test
    @Order(4)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteUserFromGroupChatByUserId/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteUserFromGroupChatByUserId() throws Exception {
        String token = getToken("0@mail.com", "pass0");
        mockMvc.perform(delete("/api/user/chat/100/group")
                        .param("userId", "101")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь с id = 101 успешно удален из группового чата"))
                .andDo(print());
    }

    @Test
    @Order(1)
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteChatByIdDoesChatExist() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        delete("/api/user/chat/102")
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteChatByIdDoesSingleChatBelongToUser() throws Exception {
        String userToken = getToken("102@mail.com", "pass102");
        mockMvc.perform(
                        delete("/api/user/chat/100")
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteChatByIdDoesGroupChatBelongToUser() throws Exception {
        String userToken = getToken("102@mail.com", "pass102");
        mockMvc.perform(
                        delete("/api/user/chat/101")
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteChatByIdDoesDBQueryConfirmSingleChatRemovalByUserOne() throws Exception {
        String userToken = getToken("100@mail.com", "pass100");
        mockMvc.perform(
                        delete("/api/user/chat/100")
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertTrue((Boolean)
                entityManager
                        .createNativeQuery(
                                "SELECT is_delete_one " +
                                        "FROM single_chat " +
                                        "WHERE chat_id = 100 ")
                        .getSingleResult()
        );
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteChatByIdDoesDBQueryConfirmSingleChatRemovalByUserTwo() throws Exception {
        String userToken = getToken("101@mail.com", "pass101");
        mockMvc.perform(
                        delete("/api/user/chat/100")
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertTrue((Boolean)
                entityManager
                        .createNativeQuery(
                                "SELECT is_delete_two " +
                                        "FROM single_chat " +
                                        "WHERE chat_id = 100 ")
                        .getSingleResult()
        );
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/deleteChatById/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void deleteChatByIdDoesDBQueryConfirmGroupChatRemoval() throws Exception {
        String userToken = getToken("101@mail.com", "pass101");

        mockMvc.perform(
                        delete("/api/user/chat/101")
                                .header(AUTHORIZATION, userToken))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertTrue((Boolean)
                entityManager
                        .createNativeQuery(
                                "SELECT COUNT(*) = 0 " +
                                        "FROM groupchat_has_users " +
                                        "WHERE chat_id = 101 AND user_id = 101 ")
                        .getSingleResult()
        );
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void findStringInGroupChats() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/chat").param("findMessage", "group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$[0].id", Is.is(104)))
                .andExpect(jsonPath("$[0].name", Is.is("Test group chat 104")))
                .andExpect(jsonPath("$[0].image", Is.is("Image for test chat 104")))
                .andExpect(jsonPath("$[0].lastMessage", Is.is("Test message by id = 104")))
                .andExpect(jsonPath("$[0].isChatPin", Is.is(false)))
                .andExpect(jsonPath("$[0].persistDateTimeLastMessage", Matchers.containsString(LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void findNonExistentStringInSingleAndGroupChats() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/chat").param("findMessage", "qwerty")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(0)));
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void findEmptyStringInSingleAndGroupChats() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/chat").param("findMessage", "")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)));
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/findStringInSingleAndGroupChats/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void findStringInGroupChatsReturning3values() throws Exception {
        String userToken = getToken("1@mail.com", "pass1");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/chat").param("findMessage", "group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)));
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addGroupChat() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"chatName\": \"Some group chat 3\", \"userIds\": [\"100\", \"101\"] }"))
                .andExpect(status().isOk());
        BigInteger countGC = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM group_chat").getSingleResult();
        BigInteger countGCHU = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM groupchat_has_users").getSingleResult();
        Assertions.assertEquals(countGC.intValue(), 1);
        Assertions.assertEquals(countGCHU.intValue(), 2);
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addGroupChatNonExistentUser() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"chatName\": \"Some group chat 3\", \"userIds\": [\"102\", \"103\"] }"))
                .andExpect(status().isOk());
        BigInteger countGC = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM group_chat").getSingleResult();
        BigInteger countGCHU = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM groupchat_has_users").getSingleResult();
        Assertions.assertEquals(countGC.intValue(), 1);
        Assertions.assertEquals(countGCHU.intValue(), 0);
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addGroupChatWithoutUserIds() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"chatName\": \"Some group chat 3\"}"))
                .andExpect(status().isBadRequest());
        BigInteger countGC = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM group_chat").getSingleResult();
        BigInteger countGCHU = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM groupchat_has_users").getSingleResult();
        Assertions.assertEquals(countGC.intValue(), 0);
        Assertions.assertEquals(countGCHU.intValue(), 0);
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addGroupChatWithoutChatName() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userIds\": [\"100\", \"101\"]}"))
                .andExpect(status().isBadRequest());
        BigInteger countGC = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM group_chat").getSingleResult();
        BigInteger countGCHU = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM groupchat_has_users").getSingleResult();
        Assertions.assertEquals(countGC.intValue(), 0);
        Assertions.assertEquals(countGCHU.intValue(), 0);
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/reAddGroupChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/reAddGroupChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void reAddGroupChat() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"chatName\": \"Some group chat 3\", \"userIds\": [\"100\", \"101\"]}"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/user/chat/group")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"chatName\": \"Some group chat 3\", \"userIds\": [\"100\", \"101\"]}"))
                .andExpect(status().isOk());
        BigInteger countGC = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM group_chat").getSingleResult();
        BigInteger countGCHU = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM groupchat_has_users").getSingleResult();
        Assertions.assertEquals(countGC.intValue(), 3);
        Assertions.assertEquals(countGCHU.intValue(), 6);
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addSingleChat() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/single")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userRecipientId\": \"101\", \"message\": \"Test message\" }"))
                .andExpect(status().isOk());
        BigInteger countSC = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM single_chat").getSingleResult();
        BigInteger countM = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM message").getSingleResult();
        Assertions.assertEquals(countSC.intValue(), 1);
        Assertions.assertEquals(countM.intValue(), 1);
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addSingleChatNonExistentUserRecipient() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/single")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userRecipientId\": \"102\", \"message\": \"Test message\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addSingleChatWithoutUserRecipientId() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/single")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"message\": \"Test message\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void addSingleChatWithoutMessage() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/single")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userRecipientId\": \"101\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/script/TestChatResourceController/addChat/Before.sql",
            executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/script/TestChatResourceController/addChat/After.sql",
            executionPhase = AFTER_TEST_METHOD)
    public void reAddSingleChat() throws Exception {
        String userToken = getToken("0@mail.com", "pass0");
        mockMvc.perform(post("/api/user/chat/single")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userRecipientId\": \"101\", \"message\": \"Test message\" }"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/user/chat/single")
                        .header(AUTHORIZATION, userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userRecipientId\": \"101\", \"message\": \"Test message\" }"))
                .andExpect(status().isOk());
        BigInteger countSC = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM single_chat").getSingleResult();
        BigInteger countM = (BigInteger) super.entityManager.createNativeQuery("SELECT COUNT(*) FROM message").getSingleResult();
        Assertions.assertEquals(countSC.intValue(), 2);
        Assertions.assertEquals(countM.intValue(), 2);
    }
}
