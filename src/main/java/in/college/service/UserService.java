package in.college.service;

import in.college.dto.AddUserRequestDto;
import in.college.exception.DataNotExistException;
import in.college.exception.DuplicateEntityException;
import in.college.model.User;
import in.college.repository.UserRepository;
import in.college.util.Constants;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User saveUser(AddUserRequestDto addUserRequestDto,boolean isStudent) {
        String email = addUserRequestDto.getEmail();

        if (repository.existsUserByEmail(email)) {
            logger.error("User with email address: " + email + " is already exits");
            throw new DuplicateEntityException("User with email address: " + email + " is already exits");
        } else {
            User user = dtoToUser(addUserRequestDto,isStudent);
            logger.info("Adding User Data");
            return repository.insert(user);
        }
    }

    public User getUser(String email) {
        if (repository.existsUserByEmail(email)) {
            logger.info("Getting User Data from database");
            return repository.findUserByEmail(email);
        } else {
            logger.error("User with email address: " + email + " is does not exits");
            throw new DataNotExistException("User with email address: " + email + " is does not exits");
        }
    }

    public User getUser(String email,String role) {
        if (repository.existsUserByEmail(email)) {
            logger.info("Getting User Data from database");
            User user=repository.findUserByRoleAndEmail(role,email);
            logger.info(user.toString());
            return user;
        } else {
            logger.error("User with email address: " + email + " is does not exits");
            throw new DataNotExistException("User with email address: " + email + " is does not exits");
        }
    }

    public List<User> getUsersByRole(String role){
        return repository.findUsersByRole(role);
    }

    public List<User> getUsers() {
        logger.info("Getting Users Data from database");
        return repository.findAll();
    }

    private User dtoToUser(AddUserRequestDto addUserRequestDto,boolean isStudent) {
        User user = new User();
        if(isStudent) user.setRole(Constants.STUDENT);
        else user.setRole(addUserRequestDto.getRole());
        user.setName(addUserRequestDto.getName());
        user.setAddress(addUserRequestDto.getAddress());
        user.setEmail(addUserRequestDto.getEmail());
        user.setGender(addUserRequestDto.getGender());
        user.setPassword(addUserRequestDto.getPassword());
        return user;
    }

    public void removeUserByRoleAndEmail(String email,String role) {

        if (repository.existsUserByEmail(email)) {
            logger.info("Deleting User Data");
            repository.deleteUserByEmailAndRole(email,role);
        } else {
            logger.error("User with email address: " + email + " is does not exits");
            throw new DataNotExistException("User with email address: " + email + " is does not exits");
        }

    }

    public boolean isExists(String email){
        return repository.existsUserByEmail(email);
    }




}
