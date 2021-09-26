package in.college.repository;

import in.college.model.MarkSheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkSheetRepository extends MongoRepository<MarkSheet, Integer> {
}
