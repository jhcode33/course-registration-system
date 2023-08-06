package course_registratio.course_registration_system.domain;

import course_registratio.course_registration_system.entity.Role;
import course_registratio.course_registration_system.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSignUpDomain {
    private String username;
    private String loginId;
    private String password;
    private String passwordConfirm;

    private String email;
    private String phoneNumber;

    public User toEntity(){
        return User.builder()
                .username(this.username)
                .loginId(this.loginId)
                .password(this.password)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .role(Role.STUDENT)
                .build();
    }

    @Builder
    public UserSignUpDomain(String username, String loginId, String password, String passwordConfirm, String email, String phoneNumber) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
