package in.college.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import in.college.dto.SubjectDto;
import in.college.exception.DataNotExistException;
import in.college.exception.DuplicateEntityException;
import in.college.model.Subject;
import in.college.model.User;
import in.college.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserService userService;

    private final MongoTemplate mongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(SubjectService.class);

    public Subject saveSubject(SubjectDto subjectDto) {
        String name = subjectDto.getName();

        if (subjectRepository.existsByName(name)) {
            logger.error("Subject with name: " + name + " is already exits");
            throw new DuplicateEntityException("Subject with name: " + name + " is already exits");
        } else {
            Subject subject = new Subject();
            subject.setName(name);
            logger.info("Adding Subject Data");
            return subjectRepository.insert(subject);
        }
    }

    public Subject getSubject(String name) {
        if (subjectRepository.existsByName(name)) {
            logger.info("Getting Subject Data from database");
            return subjectRepository.findByName(name);
        } else {
            logger.error("Subject with name: " + name + " is does not exits");
            throw new DataNotExistException("Subject with name: " + name + " is does not exits");
        }
    }

    public List<Subject> getSubjects() {
        logger.info("Getting Subjects Data from database");
        return subjectRepository.findAll();
    }


    public void removeSubject(String name) {

        if (subjectRepository.existsByName(name)) {
            logger.info("Deleting Subject Data");
            subjectRepository.deleteByName(name);
        } else {
            logger.error("Subject with name: " + name + " is does not exits");
            throw new DataNotExistException("Subject with name: " + name + " is does not exits");
        }

    }


    public boolean assignSubjectTeacher(String subjectName, String mail) {


        logger.info("Data Validating");
        if (subjectRepository.existsByName(subjectName) && userService.isExists(mail)) {
            logger.info("Getting Teacher Data");
            User user = userService.getUser(mail);

            Query query = new Query();
            query.addCriteria(Criteria.where("name").is(subjectName));

            Update update = new Update();
            update.push("users",user.getId());

            logger.info("Assigning Subject ");
            return mongoTemplate.updateFirst(query,update,Subject.class).wasAcknowledged();
        } else {
            throw new DataNotExistException("Either Subject or Teacher is does not exist");
        }
    }

    public List<SubjectDto> mySubjects(String email) {
        User user = userService.getUser(email);

        MongoCollection<Document> collection = mongoTemplate.getCollection("subjects");

        BasicDBObject eq = new BasicDBObject("$eq", user.getId());
        BasicDBObject elemMatch = new BasicDBObject("$elemMatch", eq);
        BasicDBObject users = new BasicDBObject("users", elemMatch);

        FindIterable<Document> documents=collection.find(users);

        List<SubjectDto> response = new ArrayList<>();

        for (Document doc:documents
        ) {
            SubjectDto subjectDto=new SubjectDto();
            String name= (String) doc.get("name");
            subjectDto.setName(name);
            response.add(subjectDto);
        }

        return response;
    }


}
