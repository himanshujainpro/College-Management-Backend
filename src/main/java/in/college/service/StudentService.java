package in.college.service;

import in.college.model.MarkSheet;
import in.college.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final UserService userService;
    private final MongoOperations mongoOperations;

    public List<MarkSheet> markSheet(String email) {

        User user = userService.getUser(email);
        String student_id = user.getId();

        Query query = new Query();
        query.addCriteria(Criteria.where("student_id").is(student_id));

        return mongoOperations.find(query, MarkSheet.class);

    }
}
