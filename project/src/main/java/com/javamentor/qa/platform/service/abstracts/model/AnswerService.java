package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;

public interface AnswerService extends ReadWriteService<Answer, Long> {
    Boolean getIfNotExists(Long questionId, Long userId);
    Boolean isAnswerExistInQuestion(Long answerId, Long questionId);
}
