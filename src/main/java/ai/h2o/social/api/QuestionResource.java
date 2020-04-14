package ai.h2o.social.api;


import ai.h2o.social.stackexchange.QuestionRepository;
import ai.h2o.social.stackexchange.domain.Question;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/h2o-questions")
public class QuestionResource {
    
    
    @Inject
    QuestionRepository questionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }
}