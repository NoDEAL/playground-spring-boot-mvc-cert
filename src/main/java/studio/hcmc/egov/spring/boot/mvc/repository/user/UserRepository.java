package studio.hcmc.egov.spring.boot.mvc.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.hcmc.egov.spring.boot.mvc.entity.user.UserEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * 이메일과 비밀번호가 모두 일치하는 사용자 탐색
     * @param email 이메일
     * @param password 비밀번호
     * @return 탐색된 사용자, 존재하지 않을 때 {@link Optional#empty()}
     */
    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    /**
     * 이메일, 비밀번호, 공동인증서 subject가 모두 일치하는 사용자 탐색
     * @param email 이메일
     * @param subject 공동인증서 식별자
     * @return 탐색된 사용자, 존재하지 않을 때 {@link Optional#empty()}
     */
    Optional<UserEntity> findByEmailAndSubject(String email, String subject);

    /**
     * 이메일, 비밀번호, 공동인증서 subject가 중 하나가 일치하는 사용자 탐색
     * @param email 이메일
     * @param password 비밀번호
     * @param subject 공동인증서 식별자
     * @return 탐색된 사용자, 존재하지 않을 때 {@link Optional#empty()}
     */
    Optional<UserEntity> findByEmailOrPasswordOrSubject(String email, String password, String subject);
}
