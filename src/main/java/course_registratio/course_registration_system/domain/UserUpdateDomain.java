package course_registratio.course_registration_system.domain;

import course_registratio.course_registration_system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDomain {

    private String loginId;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String phoneNumber;

    public User updateToEntity(User user){
        return User.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .role(user.getRole())
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
