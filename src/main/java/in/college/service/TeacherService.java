package in.college.service;

import in.college.dto.MarkSheetDto;
import in.college.model.MarkSheet;
import in.college.model.User;
import in.college.repository.MarkSheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService {

    private final MarkSheetRepository markSheetRepository;
    private final UserService userService;

    public MarkSheet addMarks(MarkSheetDto markSheetDto,String username) {
        User user=userService.getUser(username);
        return markSheetRepository.save(dtoToObject(markSheetDto,user.getId()));
    }

    private MarkSheet dtoToObject(MarkSheetDto markSheetDto,String teacher_id){
        MarkSheet markSheet=new MarkSheet();
        markSheet.setMaximumMarks(markSheetDto.getMaximumMarks());
        markSheet.setObtainedMarks(markSheetDto.getObtainedMarks());
        markSheet.setPassingMarks(markSheetDto.getPassingMarks());
        markSheet.setStudent_id(markSheetDto.getStudent_id());
        markSheet.setTeacher_id(teacher_id);
        markSheet.setSub_id(markSheetDto.getSub_id());
        return markSheet;
    }
}
