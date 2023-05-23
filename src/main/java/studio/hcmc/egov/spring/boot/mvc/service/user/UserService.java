package studio.hcmc.egov.spring.boot.mvc.service.user;

import org.springframework.stereotype.Service;
import studio.hcmc.egov.spring.boot.mvc.entity.user.UserEntity;
import studio.hcmc.egov.spring.boot.mvc.repository.user.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Bean 객체 의존성 주입을 위한 생성자
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@link UserEntity}으로 사용자 정보 저장
     * @param entity 생성 또는 수정된 내용이 포함된 {@link UserEntity}
     * @return 생성 또는 수정된 내용이 반영된 {@link UserEntity}
     */
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    /**
     * 이메일과 비밀번호가 모두 일치하는 사용자 탐색
     * @param email 이메일
     * @param password 비밀번호
     * @return 탐색된 사용자, 존재하지 않을 때 {@link Optional#empty()}
     */
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    /**
     * 이메일, 비밀번호, 공동인증서 subject가 모두 일치하는 사용자 탐색
     * @param email 이메일
     * @param subject 공동인증서 식별자
     * @return 탐색된 사용자, 존재하지 않을 때 {@link Optional#empty()}
     */
    public Optional<UserEntity> findByEmailAndSubject(String email, String subject) {
        return userRepository.findByEmailAndSubject(email, subject);
    }

    /**
     * 이메일, 비밀번호, 공동인증서 subject가 중 하나가 일치하는 사용자 탐색
     * @param email 이메일
     * @param password 비밀번호
     * @param subject 공동인증서 식별자
     * @return 탐색된 사용자, 존재하지 않을 때 {@link Optional#empty()}
     */
    public Optional<UserEntity> findByEmailOrPasswordOrSubject(String email, String password, String subject) {
        return userRepository.findByEmailOrPasswordOrSubject(email, password, subject);
    }
}
