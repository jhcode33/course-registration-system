package course_registratio.course_registration_system.service;

import course_registratio.course_registration_system.domain.UserSignUpDomain;
import course_registratio.course_registration_system.entity.UserEntity;
import course_registratio.course_registration_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public Long join(UserSignUpDomain userSignUpDomain){
        userRepository.findByLoginId(userSignUpDomain.getLoginId())
                .ifPresent(userEntity -> {
                    throw new IllegalArgumentException("Failed: Already Exist ID!");
                });

        if (!userSignUpDomain.getPassword().equals(userSignUpDomain.getPasswordConfirm())) {
            throw new IllegalArgumentException("Failed: Please Check Password!");
        }

        UserEntity userEntity = userSignUpDomain.toEntity();
        return userRepository.save(userEntity).getUserId();
    }
}
