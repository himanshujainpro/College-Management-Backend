package in.college.repository;

import in.college.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, Integer> {
    boolean existsByName(String name);

    Subject findByName(String name);

    void deleteByName(String name);

}
