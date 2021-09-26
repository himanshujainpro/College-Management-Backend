package in.college.controller;

import in.college.dto.SubjectDto;
import in.college.model.MarkSheet;
import in.college.model.User;
import in.college.service.StudentService;
import in.college.service.SubjectService;
import in.college.service.UserService;
import in.college.util.Constants;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final UserService userService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);


    @GetMapping("subjects")
    public List<SubjectDto> mySubjects(@CurrentSecurityContext(expression = "authentication?.name")
                                              String username) {
        return subjectService.mySubjects(username);
    }

    @GetMapping("/{email}")
    public User aboutMe(@PathVariable String email) {
        logger.info("Get Student Request Received");
        return userService.getUser(email, Constants.STUDENT);
    }

    @GetMapping("marks")
    public List<MarkSheet> markSheet(@CurrentSecurityContext(expression = "authentication?.name")
                                           String username){
        return studentService.markSheet(username);
    }
}