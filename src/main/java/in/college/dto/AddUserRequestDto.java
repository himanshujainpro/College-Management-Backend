package in.college.dto;

import in.college.model.Address;
import in.college.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequestDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private User.Gender gender;
    private String role;
    private Address address;
}