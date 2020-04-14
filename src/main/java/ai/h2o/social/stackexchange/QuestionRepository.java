package ai.h2o.social.stackexchange;

import ai.h2o.social.stackexchange.domain.Question;

import java.util.Set;

public interface QuestionRepository {
    Set<Question> getAllQuestions();
}
