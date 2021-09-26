package in.college.controller;

import in.college.dto.AddUserRequestDto;
import in.college.dto.MarkSheetDto;
import in.college.dto.SubjectDto;
import in.college.model.MarkSheet;
import in.college.model.User;
import in.college.service.SubjectService;
import in.college.service.TeacherService;
import in.college.service.UserService;
import in.college.util.Constants;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/")
@AllArgsConstructor
public class TeacherController {

    private final SubjectService subjectService;
    private final UserService userService;
    private final Logger logger=LoggerFactory.getLogger(TeacherController.class);
    private final TeacherService teacherService;

    @GetMapping("subjects")
    public List<SubjectDto> mySubjects(@CurrentSecurityContext(expression = "authentication?.name")
                                                  String username) {
        return subjectService.mySubjects(username);
    }


    @GetMapping("/{email}")
    public User aboutMe(@PathVariable String email) {
        logger.info("Get Teacher Request Received");
        return userService.getUser(email, Constants.STUDENT);
    }

    //teachers can add students
    @PostMapping("students")
    public User addUser(@RequestBody AddUserRequestDto addStudentRequestDto) {
        logger.info("Add User Request Received");
        return userService.saveUser(addStudentRequestDto,true);
    }

    @PostMapping("marks")
    public MarkSheet addMarks(@RequestBody MarkSheetDto markSheetDto
    ,@CurrentSecurityContext(expression = "authentication?.name")
                                             String username){
        logger.info("Allocate marks request received");
        return teacherService.addMarks(markSheetDto,username);
    }
}