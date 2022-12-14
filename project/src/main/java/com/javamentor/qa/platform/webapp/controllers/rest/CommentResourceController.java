package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.CommentDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import com.javamentor.qa.platform.service.abstracts.model.CommentQuestionService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/comment")
@Api("Comment Api")
@AllArgsConstructor
public class CommentResourceController {

    private final CommentDtoService commentDtoService;
    private final CommentQuestionService commentQuestionService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/question/{questionId}")
    @ApiOperation(value = "?????????????????? ?????????????????????? ?? ?????????????? ?? questionId=*")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ?????????????????????? ?? ?????????????? ?? questionId=*"),
            @ApiResponse(code = 400, message = "?????????????????????? ???????????? ???????? ???????????????? ???????????? ???????????????????? questionId. ?????????????????? ?????????? ?????????????????????????? ?????????? > 0"),
            @ApiResponse(code = 404, message = "???????????? ?? questionId=* ???? ????????????")
    })
    public ResponseEntity<?> addCommentByQuestionId(@Valid @RequestBody String comment, @PathVariable("questionId") Long questionId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Question> question = questionService.getById(questionId);

        if (question.isEmpty()) {
            return new ResponseEntity<>("???????????? ?? id=" + questionId + " ???? ????????????", HttpStatus.NOT_FOUND);
        }

        if (comment.isBlank()) {
            return new ResponseEntity<>("?????????????????????? ????????????", HttpStatus.BAD_REQUEST);
        }

        CommentQuestion commentQuestion = new CommentQuestion();
        commentQuestion.setUser(user);
        commentQuestion.setText(comment);
        commentQuestion.setQuestion(question.get());
        commentQuestionService.persist(commentQuestion);
        return new ResponseEntity<>("?????????????????????? ?? ?????????????? ?? id=" + questionId + " ?????????????? ????????????????", HttpStatus.OK);
    }

    @GetMapping("/question/{questionId}")
    @ApiOperation(value = "???????????????????? ???????????? PageDto ?? items <CommentDto> ?? ???????????? ???????????????? ???????????????????? ??????????????????")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ?????? ?????????????????????? ?? ?????????????? ?? questionId=*"),
            @ApiResponse(code = 400, message = "???????????????????? ???????????? ???????????????????????? ????????????????: ?????????? ????????????????"),
            @ApiResponse(code = 404, message = "???????????? ?? questionId=* ???? ????????????")
    })
    public ResponseEntity<?> getCommentsPaginationByQuestionId(
            @PathVariable Long questionId,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(required = false, name = "items", defaultValue = "10") Integer itemsOnPage) {

        if (!questionService.existsById(questionId)) {
            return new ResponseEntity<>("???????????? ?? questionId=" + questionId + " ???? ????????????", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("questionId", questionId);
        params.put("currentPageNumber", currentPage);
        params.put("itemsOnPage", itemsOnPage);

        PageDto<CommentDto> pageDto = commentDtoService.getPageDto("paginationCommentsQuestion", params);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @GetMapping("/answer/{answerId}")
    @ApiOperation(value = "???????????????????? ???????????? PageDto ?? items <CommentDto> ?? ???????????? ???????????????? ???????????????????? ??????????????????")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ?????? ?????????????????????? ?? ???????????? ?? answerId=*"),
            @ApiResponse(code = 400, message = "???????????????????? ???????????? ???????????????????????? ????????????????: ?????????? ????????????????"),
            @ApiResponse(code = 404, message = "?????????? ?? answerId=* ???? ????????????")
    })
    public ResponseEntity<?> getCommentsPaginationByAnswerId(
            @PathVariable Long answerId,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(required = false, name = "items", defaultValue = "10") Integer itemsOnPage) {

        if (!answerService.existsById(answerId)) {
            return new ResponseEntity<>("?????????? ?? answerId=" + answerId + " ???? ????????????", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("answerId", answerId);
        params.put("currentPageNumber", currentPage);
        params.put("itemsOnPage", itemsOnPage);

        return new ResponseEntity<>(commentDtoService.getPageDto("paginationCommentAnswer", params), HttpStatus.OK);
    }

}
