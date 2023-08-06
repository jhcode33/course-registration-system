package course_registratio.course_registration_system.domain;

import course_registratio.course_registration_system.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDomain {

    private Long userId;
    private String username;
    private String loginId;
    private String password;
    private String passwordConfirm;
    private String email;
    private String phoneNumber;

    public UserResponseDomain(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.loginId = user.getLoginId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
