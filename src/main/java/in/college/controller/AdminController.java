package in.college.controller;

import in.college.dto.AddUserRequestDto;
import in.college.dto.AssignSubjectRequestDto;
import in.college.dto.SubjectDto;
import in.college.model.Subject;
import in.college.model.User;
import in.college.service.SubjectService;
import in.college.service.UserService;
import in.college.util.Constants;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final SubjectService subjectService;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }

    // Common for student and teacher as we can user role at the time of creation

    // User CRUD for Student and Teacher
    @PostMapping("users")
    public User addUser(@RequestBody AddUserRequestDto addStudentRequestDto) {
        logger.info("Add User Request Received");
        return userService.saveUser(addStudentRequestDto, false);
    }

    @GetMapping("users")
    public List<User> users() {
        return userService.getUsers();
    }


    //Starting student crud
    //Find Student by email
    @GetMapping("/students/{email}")
    public User findStudentByEmail(@PathVariable String email) {
        logger.info("Get Student Request Received");
        return userService.getUser(email, Constants.STUDENT);
    }

    //Get all Students
    @GetMapping("/students")
    public List<User> findStudents() {
        logger.info("Get all students");
        return userService.getUsersByRole(Constants.STUDENT);
    }

    @DeleteMapping("/students/{email}")
    public String deleteStudentByEmail(@PathVariable String email) {
        logger.info("delete Student Request Received");
        userService.removeUserByRoleAndEmail(email, Constants.STUDENT);
        return "Data Deleted";
    }
    //ending student crud


    //Starting teacher crud
    //Find teacher by email
    @GetMapping("/teachers/{email}")
    public User findTeacherByEmail(@PathVariable String email) {
        logger.info("Get Teacher Request Received");
        return userService.getUser(email, Constants.TEACHER);
    }

    //Get all teachers
    @GetMapping("/teachers")
    public List<User> getTeachers() {
        logger.info("Get all teachers");
        return userService.getUsersByRole(Constants.TEACHER);
    }

    @DeleteMapping("/teachers/{email}")
    public String deleteTeachersByEmail(@PathVariable String email) {
        logger.info("delete Teacher Request Received");
        userService.removeUserByRoleAndEmail(email, Constants.TEACHER);
        return "Data Deleted";
    }
    //ending teacher crud


    // Subject CRUD
    @PostMapping("subjects")
    public Subject addSubject(@RequestBody SubjectDto subjectDto) {
        logger.info("Add Subject Request Received");
        return subjectService.saveSubject(subjectDto);
    }

    @DeleteMapping("subjects/{name}")
    public String removeSubjectByName(@PathVariable String name) {
        logger.info("Delete Subject Request Received");
        subjectService.removeSubject(name);
        logger.info("Deleted Subject Data");
        return "Data Deleted";
    }

    @GetMapping("subjects")
    public List<Subject> getSubjects() {
        logger.info("Find all Subjects Request Received");
        return subjectService.getSubjects();
    }

    @GetMapping("subjects/{name}")
    public Subject findSubjectByName(@PathVariable String name) {
        logger.info("Get Subject Request Received");
        return subjectService.getSubject(name);
    }

    // Allocating subject to the user
    @PostMapping("subjects/user/subject")
    public String assignSubject(@RequestBody AssignSubjectRequestDto assignSubjectRequestDto) {
        logger.info("Subject assign request to the user received");
        if (subjectService.assignSubjectTeacher(assignSubjectRequestDto.getName(),
                assignSubjectRequestDto.getEmail())) {
            return "Subject Updated";
        } else {
            return "Internal Sever Error";
        }
    }
}