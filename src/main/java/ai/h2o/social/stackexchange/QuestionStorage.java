package ai.h2o.social.stackexchange;

import ai.h2o.social.stackexchange.domain.Question;
import io.quarkus.scheduler.Scheduled;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.*;

@ApplicationScoped
class QuestionStorage  implements QuestionRepository{
    
    private Set<Question> questions = new HashSet<>();
    private static final int PAGE_SIZE = 100;

    @Inject
    @StackExchange
    WebTarget stackExchangeTarget;
    
    @PostConstruct
    @Scheduled(every = "12h")
    public void downloadAndSaveQuestions(){
        questions.addAll(downloadQuestions());
    }
    
    public List<Question> downloadQuestions() {
        final List<Question> questions = new ArrayList<>();
        final Jsonb jsonBuilder = JsonbBuilder.create();

        boolean repeat = true;
        int page = 1;

        while (repeat) {
            final JsonObject jsonObject = stackExchangeTarget.path("/search")
                    .queryParam("order", "desc")
                    .queryParam("sort", "creation")
                    .queryParam("tagged", "h2o")
                    .queryParam("site", "stackoverflow")
                    .queryParam("page", page)
                    .queryParam("pagesize", PAGE_SIZE)
                    .request()
                    .acceptEncoding("GZIP,COMPRESS")
                    .get(JsonObject.class);

            questions.addAll(jsonBuilder.fromJson(jsonObject.get("items").toString(), new ArrayList<Question>() {
            }.getClass().getGenericSuperclass()));
            page++;
            repeat = jsonObject.getBoolean("has_more", false);
        }
        
        return questions;
    }

    @Override
    public Set<Question> getAllQuestions() {
        return Collections.unmodifiableSet(questions);
    }
}
