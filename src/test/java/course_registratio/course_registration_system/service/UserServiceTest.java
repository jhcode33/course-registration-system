package course_registratio.course_registration_system.service;

import course_registratio.course_registration_system.domain.UserSignUpDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    UserService userService;

    List<UserSignUpDomain> users;

    @BeforeEach
    public void init(){
        int createTestUser = 5;
        users = new ArrayList<>();

        for(int i = 0; i< createTestUser; i++){
            UserSignUpDomain userSignUpDomain = UserSignUpDomain.builder()
                    .username("testUser" + i)
                    .loginId("testLoginId" + i)
                    .password("testPassword" + i)
                    .passwordConfirm("testPassword" + i)
                    .email("test" + i + "@example.com")
                    .phoneNumber("123-4567-890" + i)
                    .build();

            users.add(userSignUpDomain);
        }

    }

    @Test
    public void joinTest() {

        for(int i = 0; i < users.size(); i++){
            userService.join(users.get(i));
        }
    }
}
