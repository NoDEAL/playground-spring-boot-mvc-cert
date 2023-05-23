package studio.hcmc.egov.spring.boot.mvc.vo.user;

import studio.hcmc.egov.spring.boot.mvc.domain.user.UserDomain;

public class UserVO implements UserDomain {
    private long id;

    private String email;

    private String password;

    private String subject;

    private String signedData;

    private String serialNumberLeading;

    private String serialNumberTrailing;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSignedData() {
        return signedData;
    }

    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }

    public String getSerialNumberLeading() {
        return serialNumberLeading;
    }

    public void setSerialNumberLeading(String serialNumberLeading) {
        this.serialNumberLeading = serialNumberLeading;
    }

    public String getSerialNumberTrailing() {
        return serialNumberTrailing;
    }

    public void setSerialNumberTrailing(String serialNumberTrailing) {
        this.serialNumberTrailing = serialNumberTrailing;
    }
}
