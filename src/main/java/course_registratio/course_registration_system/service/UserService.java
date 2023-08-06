package course_registratio.course_registration_system.service;

import course_registratio.course_registration_system.domain.UserSignUpDomain;
import course_registratio.course_registration_system.domain.UserUpdateDomain;
import course_registratio.course_registration_system.entity.User;
import course_registratio.course_registration_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserSignUpDomain userSignUpDomain){
        userRepository.findByLoginId(userSignUpDomain.getLoginId())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Failed: Already Exist ID!");
                });

        if (!userSignUpDomain.getPassword().equals(userSignUpDomain.getPasswordConfirm())) {
            throw new IllegalArgumentException("Failed: Please Check Password!");
        }

        // Domain -> Entity 변환
        User user = userSignUpDomain.toEntity();
        return userRepository.saveAndFlush(user).getUserId();
    }

    @Transactional
    public Long update(UserUpdateDomain userUpdateDomain){
        User findUser = userRepository.findByLoginId(userUpdateDomain.getLoginId()).orElseThrow(
                () -> new IllegalArgumentException("Failed: No User Info")
        );

        if (userUpdateDomain.getUsername() == null){
            userUpdateDomain.setUsername(findUser.getUsername());
        } else if (userUpdateDomain.getPassword() == null){
            userUpdateDomain.setPassword(findUser.getPassword());
        } else if (userUpdateDomain.getEmail() == null){
            userUpdateDomain.setEmail(findUser.getEmail());
        }

        findUser.update(userUpdateDomain);
        return findUser.getUserId();
    }
}
