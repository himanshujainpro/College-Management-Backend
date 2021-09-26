package in.college.repository;

import in.college.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {


    boolean existsUserByEmail(String email);

    User findUserByEmail(String email);

    Optional<User> findByEmail(String email);


    User findUserByRoleAndEmail(String role, String email);

    List<User> findUsersByRole(String role);

    void deleteUserByEmailAndRole(String email, String role);


}
