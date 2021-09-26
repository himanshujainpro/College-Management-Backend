package in.college.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "mark_sheet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkSheet {

    private int passingMarks;
    private int maximumMarks;
    private int obtainedMarks;

    private String sub_id;
    private String student_id;
    private String teacher_id;
}