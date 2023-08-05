package course_registratio.course_registration_system.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class UserEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column
    private String phone_number;

    @Column
    private Role role;

    @Builder
    public UserEntity(String loginId, String password, String email, String username, String phone_number, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.username = username;
        this.phone_number = phone_number;
        this.role = role;
    }
}
