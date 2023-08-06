package course_registratio.course_registration_system.service;

import course_registratio.course_registration_system.domain.UserSignUpDomain;
import course_registratio.course_registration_system.domain.UserUpdateDomain;
import course_registratio.course_registration_system.entity.Role;
import course_registratio.course_registration_system.entity.User;
import course_registratio.course_registration_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    List<UserSignUpDomain> signUpUsers;

    @BeforeEach
    public void setUp(){
        int createTestUser = 5;
        signUpUsers = new ArrayList<>();

        for(int i = 0; i< createTestUser; i++){
            UserSignUpDomain userSignUpDomain = UserSignUpDomain.builder()
                    .username("testUser" + i)
                    .loginId("testLoginId" + i)
                    .password("testPassword" + i)
                    .passwordConfirm("testPassword" + i)
                    .email("test" + i + "@example.com")
                    .phoneNumber("123-4567-890" + i)
                    .build();

            signUpUsers.add(userSignUpDomain);
        }
    }

    private void checkSameData(User putUser, User getUser){
        assertEquals(putUser.getUsername(), getUser.getUsername());
        assertEquals(putUser.getLoginId(), getUser.getLoginId());
        assertEquals(putUser.getPassword(), getUser.getPassword());
        assertEquals(putUser.getEmail(), getUser.getEmail());
        assertEquals(putUser.getPhoneNumber(), getUser.getPhoneNumber());
        assertNotNull(getUser.getCreatedDate());
        assertEquals(getUser.getRole(), Role.STUDENT);
    }

    @Test
    public void join_successful_registration() {

        // Given
        List<Long> successUserIdList = new ArrayList<>();

        // when
        for (int i = 0; i < signUpUsers.size(); i++) {
            successUserIdList.add(userService.join(signUpUsers.get(i)));
        }

        // Then
        for (int i = 0; i < signUpUsers.size(); i++){
            User user = userRepository.findById(
                    successUserIdList.get(i)).orElseThrow(
                            () -> new RuntimeException(("User not found")));

            checkSameData(signUpUsers.get(i).toEntity(), user);
        }
    }

    @Test
    public void update_successful(){

        List<UserUpdateDomain> updateDomain = new ArrayList<>();

        for (int i = 0; i < signUpUsers.size(); i++) {
            // given
            userService.join(signUpUsers.get(i));

            updateDomain.add(UserUpdateDomain.builder()
                                .username("update" + i)
                                .loginId("testLoginId" + i)
                                .phoneNumber("updatePhoneNumber" + i)
                                .email(i+"update.co.kr")
                                .build());
        }

        for (UserUpdateDomain user : updateDomain){
            // when
            Long userUpdateId = userService.update(user);

            // then
            User findUpdateUser = userRepository.findById(userUpdateId).orElseThrow(
                    () -> new IllegalArgumentException("Fail: User Not Found"));

            checkSameData(user.updateToEntity(findUpdateUser), findUpdateUser);
        }
    }
}
