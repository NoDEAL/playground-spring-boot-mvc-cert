package studio.hcmc.egov.spring.boot.mvc.domain.user;

public interface UserDomain {
    /**
     * 사용자 ID: Primary
     */
    long getId();

    /**
     * 사용자 이메일: Unique, Immutable
     */
    String getEmail();

    /**
     * 사용자 비밀번호: Unique(주민등록번호 hash), Immutable(?)
     * @see <a href="https://www.mois.go.kr/frt/sub/a06/b06/IDCardChange/screen.do">행정안전부: 주민등록번호 변경제도</a>
     */
    String getPassword();

    /**
     * 공동인증서 식별자: Unique
     */
    String getSubject();
}
