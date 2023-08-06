package course_registratio.course_registration_system.entity;

import course_registratio.course_registration_system.domain.UserUpdateDomain;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DynamicInsert
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public void update(UserUpdateDomain userUpdateDomain){
        this.username = userUpdateDomain.getUsername();
        this.password = userUpdateDomain.getPassword();
        this.email = userUpdateDomain.getEmail();
        this.phoneNumber = userUpdateDomain.getPhoneNumber();
    }
}
