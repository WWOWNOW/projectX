package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.ChatDto;
import com.javamentor.qa.platform.models.dto.CreateGroupChatDto;
import com.javamentor.qa.platform.models.dto.CreateSingleChatDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.SingleChatDto;
import com.javamentor.qa.platform.models.entity.chat.Chat;
import com.javamentor.qa.platform.models.entity.chat.ChatType;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.ChatDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.GroupChatDtoService;
import com.javamentor.qa.platform.service.abstracts.model.GroupChatService;
import com.javamentor.qa.platform.service.abstracts.model.SingleChatService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.service.impl.dto.SingleChatDtoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Objects;


@RestController
@Api("Chats Api")
@RequestMapping("api/user/chat")
@AllArgsConstructor
public class  ChatResourceController {

    private final SingleChatDtoServiceImpl singleChatDtoService;
    private final SingleChatService singleChatService;
    private final GroupChatDtoService groupChatDtoService;
    private final UserService userService;
    private final GroupChatService groupChatService;
    private final ChatDtoService chatDtoService;


    @GetMapping("/single")
    @ApiOperation("Возвращает SingleChatDto авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Получены все SingleChatDto авторизованного пользователя"),
            @ApiResponse(code = 400, message = "Неправильные параметры запроса"),
    })
    public ResponseEntity<PageDto<SingleChatDto>> receiveAllSingleChatOfUser(
            @RequestParam("page") Integer page,
            @RequestParam("items") Integer items,
            @RequestParam(value = "sortAscendingFlag", required = false, defaultValue = "false") Boolean sortAscendingFlag)
    {

        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        Map<String, Object> params = new HashMap<>();
        params.put("currentPageNumber", page);
        params.put("itemsOnPage", items);
        params.put("userId", userId);
        params.put("sortAscendingFlag", sortAscendingFlag);

        return new ResponseEntity<>(singleChatDtoService.getPageDto("paginationAllSingleChatsOfUser",
                params), HttpStatus.OK);
    }

    @GetMapping("/group")
    @ApiOperation("Возвращает объект PageDto с items <GroupChatDto> с учетом заданных параметров пагинации.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Получены все сообщения с учетом заданных параметров пагинации."),
            @ApiResponse(code = 400, message = "Необходимо ввести обязательный параметр: номер страницы."),
            @ApiResponse(code = 500, message = "Страницы под номером page=* пока не существует")
    })
    public ResponseEntity<?> getGroupChatOutPutWithAllMessage(
            @RequestParam("page") Integer currentPage,
            @RequestParam(value = "items", defaultValue = "10") Integer items)
    {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        Map<String, Object> params = new HashMap<>();
        params.put("currentPageNumber", currentPage);
        params.put("itemsOnPage", items);
        params.put("userId", userId);

        return new ResponseEntity<>(groupChatDtoService.getPageDto(
                "paginationGroupChat", params), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Возвращает сообщения в Single и Group чатам по заданному параметру")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Выполнен поиск по заданной строке!"),
            @ApiResponse(code = 400, message = "Необходимо ввести обязательный параметр поиска!"),
            @ApiResponse(code = 500, message = "По данному запросу ничего не было найдено")
    })
    public ResponseEntity<List<ChatDto>> findStringInSingleAndGroupChats(
            @RequestParam(value = "findMessage") String findMessages) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return new ResponseEntity<>(chatDtoService.getChatsByString(userId, findMessages), HttpStatus.OK);
    }

    @PostMapping("/group")
    @ApiOperation("Добавление нового группового чата. В RequestBody ожидает объект CreateGroupChatDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Групповой чат успешно добавлен"),
            @ApiResponse(code = 400, message = "Поле объекта CreateGroupChatDto " +
                    "userIds должно быть заполнено")
    })
    public ResponseEntity<?> addGroupChat(@Valid @RequestBody CreateGroupChatDto createGroupChatDto) {
        GroupChat groupChat = new GroupChat();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        groupChat.setAuthor(user);
        Set<User> users = new HashSet<>();
        users.addAll(userService.getUsersByIds(createGroupChatDto.getUserIds()));
        groupChat.setUsers(users);
        Chat chat = new Chat(ChatType.GROUP);
        groupChat.setTitle(createGroupChatDto.getChatName());
        groupChat.setChat(chat);
        groupChatService.persist(groupChat);
        return new ResponseEntity<>("Групповой чат успешно добавлен", HttpStatus.OK);
    }
    @PostMapping(value = "/single")
    @ResponseBody
    @ApiOperation(value = "Добавление singleChat")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SingleChat добавлен"),
            @ApiResponse(code = 400, message = "SingleChat не был добавлен")
    })
    public ResponseEntity<?> addSingleChat(@Valid @RequestBody CreateSingleChatDto createSingleChatDto){
        User sender = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Optional<User> user = userService.getById(createSingleChatDto.getUserRecipientId());
        if(user.isEmpty()) {
            return ResponseEntity.badRequest().body("UserRecipientId doesn't exist");
        }

        SingleChat singleChat = new SingleChat();
        singleChat.setUserOne(sender);
        singleChat.setUseTwo(user.get());
        singleChatService.persist(singleChat);
        singleChatService.addSingleChatAndMessage(singleChat, createSingleChatDto.getMessage());
        return new ResponseEntity<>("SingleChat чат успешно добавлен",HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаления чата по Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Чат успешно удален"),
            @ApiResponse(code = 400, message = "Чат не принадлежит текущему пользователю"),
            @ApiResponse(code = 404, message = "Чат или пользователь не найден")})
    public ResponseEntity<?> deleteChatById(@PathVariable Long id){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (groupChatService.existsById(id)){
            groupChatService.deleteChatFromUser(id, user);
            return new ResponseEntity<>("Чат успешно удален", HttpStatus.OK);
        }

        if (singleChatService.existsById(id)) {
            singleChatService.deleteChatFromUser(id, user);
            return new ResponseEntity<>("Чат успешно удален", HttpStatus.OK);
        }

        return new ResponseEntity<>("Ошибка удаления. Чат не найден.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/group/{id}/join")
    @ApiOperation("Добавляет пользователя в групповой чат")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь успешно добавлен"),
            @ApiResponse(code = 403, message = "Пользователь может присоединиться только если его добавляет автор этого чата"),
            @ApiResponse(code = 404, message = "Пользователь или групповой чат не найден"),
            @ApiResponse(code = 409, message = "Пользователь уже есть в групповом чате")})
    public ResponseEntity<?> joinGroupChat(@PathVariable("id") Long chatId, @RequestBody Long userId) {

        /*api/user/chat/group/{id}/join*/

        /*Нужно добавить проверку, что пользователь может присоединиться только если его добавляет автор этого чата*/

        /*Если это не так, то кинуть BadRequest, "Данный пользователь не может приглашать других пользователей"*/
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userService.getById(userId);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();

        Optional<GroupChat> chatOptional = groupChatService.getGroupChatWithUsersById(chatId);
        if (chatOptional.isEmpty()) {
            return new ResponseEntity<>("Групповой чат не найден", HttpStatus.BAD_REQUEST);
        }
        GroupChat chat = chatOptional.get();

        Set<User> chatUsers = chat.getUsers();
        if (chatUsers.contains(user)) {
            return new ResponseEntity<>("Пользователь уже есть в групповом чате", HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(author.getId(), chat.getAuthor().getId())) {
            return new ResponseEntity<>("Пользователь может присоединиться только если его добавляет автор этого чата", HttpStatus.BAD_REQUEST);
        }
        chatUsers.add(user);
        chat.setUsers(chatUsers);
        groupChatService.update(chat);
        return new ResponseEntity<>("Пользователь успешно добавлен в групповой чат", HttpStatus.OK);


    }

    @PatchMapping("/{id}/group/image")
    @ApiOperation("Изменяет картинку в групповом чате")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Картинка успешно обновлена"),
            @ApiResponse(code = 404, message = "Групповой чат не найден")})
    public ResponseEntity<?> updateImageInGroupChat(@PathVariable("id") Long id, @RequestBody String image) {
        Optional<GroupChat> chatOptional = groupChatService.getGroupChatWithUsersById(id);
        if (chatOptional.isEmpty()) {
            return new ResponseEntity<>("Групповой чат не найден", HttpStatus.BAD_REQUEST);
        }
        GroupChat groupChat = chatOptional.get();
        groupChat.setImageChat(image);
        groupChatService.update(groupChat);
        return new ResponseEntity<>("Картинка группового чата успешно обновлена", HttpStatus.OK);
    }
    @PostMapping("/{groupChatID}/group/moder")
    @ApiOperation("Добавление нового модератора группового чата.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Модератор чата успешно добавлен"),
            @ApiResponse(code = 400, message = "Пользователь не находятся в чате"),
            @ApiResponse(code = 404, message = "Пользователь или чат не найден"),
    })
    public ResponseEntity<?> addModeratorsOfGroupChat(@PathVariable("groupChatID") Long groupChatID, @RequestParam Long userId) {
        Optional<User> userOptional = userService.getById(userId);
        Optional<GroupChat> chatOptional = groupChatService.getGroupChatWithUsersById(groupChatID);
        if (userOptional.isEmpty()  || chatOptional.isEmpty()) {
            return new ResponseEntity<>("Пользователь или чат не найден", HttpStatus.NOT_FOUND);
        }
        User userFromOptional = userOptional.get();
        Set<User> chatModers = new HashSet<>();
        GroupChat groupChat = chatOptional.get();
        chatModers.add(userFromOptional);
        if (!(groupChat.getUsers().contains(userFromOptional))) {
            return new ResponseEntity<>("У текущего пользователя нет группового чата с groupId = " + groupChatID ,
                    HttpStatus.BAD_REQUEST);
        }
        groupChat.setModerators(chatModers);
        groupChatService.update(groupChat);
        return new ResponseEntity<>("Модератор чата успешно добавлен", HttpStatus.OK);



    }


    @DeleteMapping("/{groupId}/group")
    @ApiOperation("Удаляет юзера из группового чата по UserId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Юзер успешно удален из группового чата"),
            @ApiResponse(code = 404, message = "Юзер или чат не найден"),
            @ApiResponse(code = 400, message = "Чат не принадлежит текущему пользователю")
    })
    public ResponseEntity<?> deleteUserFromGroupChatByUserId(@PathVariable("groupId") Long groupId ,
                                                             @RequestParam(value = "userId") Long userId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userService.getById(userId);
        Optional<GroupChat> chatOptional = groupChatService.getGroupChatWithUsersById(groupId);
        if (userOptional.isEmpty() || chatOptional.isEmpty()) {
            return new ResponseEntity<>("Пользователь или чат не найден", HttpStatus.NOT_FOUND);
        }
        GroupChat groupChat = chatOptional.get();
        User deletedUser = userOptional.get();
        Set<User> chatUsers = groupChat.getUsers();

        if (!(chatUsers.contains(user))) {
            return new ResponseEntity<>("У текущего пользователя нет групового чата с groupId = " + groupId ,
                    HttpStatus.NOT_FOUND);
        }
        if (!(chatUsers.contains(deletedUser))) {
            return new ResponseEntity<>("Пользователя с id = " + userId + " нет в групповом чате с groupId = " + groupId,
                    HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(groupChat.getAuthor().getId(), user.getId())) {
            return new ResponseEntity<>("Чат не принадлежит текущему пользователю", HttpStatus.BAD_REQUEST);
        }

        chatUsers.remove(deletedUser);
        groupChat.setUsers(chatUsers);
        groupChatService.update(groupChat);
        return new ResponseEntity<>("Пользователь с id = " + userId + " успешно удален из группового чата", HttpStatus.OK);
    }

}