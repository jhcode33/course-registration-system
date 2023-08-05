package course_registratio.course_registration_system.domain;

import course_registratio.course_registration_system.entity.UserEntity;
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

    public UserEntity toEntity(){
        return UserEntity.builder()
                .username(this.username)
                .loginId(this.loginId)
                .password(this.password)
                .email(this.email)
                .phone_number(this.phoneNumber)
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
