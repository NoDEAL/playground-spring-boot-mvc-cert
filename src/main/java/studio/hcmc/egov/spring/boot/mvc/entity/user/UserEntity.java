package studio.hcmc.egov.spring.boot.mvc.entity.user;

import jakarta.persistence.*;
import studio.hcmc.egov.spring.boot.mvc.domain.user.UserDomain;

@Entity(name = "user")
@Table(name = "user")
public class UserEntity implements UserDomain {
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", unique = true, nullable = false, updatable = false)
    private String email;

    @Column(name = "password", unique = true, nullable = false, updatable = false)
    private String password;

    @Column(name = "subject", unique = true, nullable = false, updatable = false)
    private String subject;

    /**
     * JPA로부터 인스턴스 생성을 위한 빈 생성자
     */
    public UserEntity() {}

    /**
     * 복사 생성자
     */
    public UserEntity(long userId, String email, String password, String subject) {
        this.id = userId;
        this.email = email;
        this.password = password;
        this.subject = subject;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
