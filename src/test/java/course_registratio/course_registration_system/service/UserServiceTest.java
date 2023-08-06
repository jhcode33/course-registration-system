package course_registratio.course_registration_system.service;

import course_registratio.course_registration_system.domain.UserSignUpDomain;
import course_registratio.course_registration_system.entity.Role;
import course_registratio.course_registration_system.entity.User;
import course_registratio.course_registration_system.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@Transactional
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    List<UserSignUpDomain> users;

    @BeforeEach
    public void setUp(){
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
    public void join_successful_registration() {

        // Given
        List<Long> successUserList = new ArrayList<>();

        // when
        for (int i = 0; i < users.size(); i++) {
            successUserList.add(userService.join(users.get(i)));
        }

        // Then
        for (int i = 0; i < users.size(); i++){
            User user = userRepository.findById(
                    successUserList.get(i)).orElseThrow(
                            () -> new RuntimeException(("User not found")));

            checkSameData(users.get(i).toEntity(), user);
        }
    }

    private void checkSameData(User putUser, User getUser){
        assertEquals(putUser.getUsername(), getUser.getUsername());
        assertEquals(putUser.getLoginId(), getUser.getLoginId());
        assertEquals(putUser.getPassword(), getUser.getPassword());
        assertEquals(putUser.getEmail(), getUser.getEmail());
        assertEquals(putUser.getPhoneNumber(), getUser.getPhoneNumber());
        System.out.println(getUser.getCreatedDate());
        //assertNotNull(getUser.getCreatedDate());
        assertEquals(getUser.getRole(), Role.STUDENT);
    }
}
