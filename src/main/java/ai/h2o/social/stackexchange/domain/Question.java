package ai.h2o.social.stackexchange.domain;

import javax.json.bind.annotation.JsonbProperty;
import java.util.Objects;

public class Question {
    
    @JsonbProperty("score")
    public String score;
    @JsonbProperty("link")
    public String link;
    @JsonbProperty("last_activity_date")
    public String lastActivityDate;
    @JsonbProperty("is_answered")
    public Boolean isAnswered;
    @JsonbProperty("creation_date")
    public String creationDate;
    @JsonbProperty("answer_count")
    public String answerCount;
    @JsonbProperty("title")
    public String title;
    @JsonbProperty("question_id")
    public String questionId;
    @JsonbProperty("view_count")
    public String viewCount;
    public String[] tags;
    @JsonbProperty("last_edit_date")
    public String lastEditDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }
}
