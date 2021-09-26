package in.college.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkSheetDto {

    private int passingMarks;
    private int maximumMarks;
    private int obtainedMarks;

    private String sub_id;
    private String student_id;
}