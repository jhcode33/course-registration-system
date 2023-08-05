package course_registratio.course_registration_system.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    STUDENT("ROLE_STUDENT", "학생"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String value;
}
