package studio.hcmc.egov.spring.boot.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseStatus;
import studio.hcmc.egov.spring.boot.mvc.vo.user.UserVO;

public class ErrorResponse {
    private ErrorResponse() {}

    public interface Redirect {
        String getRedirect();
    }

    /**
     * 공동인증서 처리 관련 오류
     */
    public static class Cert {
        private Cert() {}

        /**
         * 공동인증서 오류 공통 형식
         */
        public static class CrosscertException extends RuntimeException {
            public CrosscertException(String errorMessage, String errorDetailMessage) {
                super("errorMessage=" + errorMessage + ", errorDetailMessage=" + errorDetailMessage);
            }
        }

        /**
         * Base64 부호화 관련 오류
         */
        public static class Base64 {
            private Base64() {}

            /**
             * 서명 결과가 올바르지 않은 Base64 encoded 문자열
             * @see crosscert.Base64#errcode
             */
            @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "BASE64_DECODE_ERROR")
            public static class DecodeError extends CrosscertException {
                public DecodeError(String errorMessage, String errorDetailMessage) {
                    super(errorMessage, errorDetailMessage);
                }
            }
        }

        /**
         * 공동인증서 자체와 관련된 오류
         */
        public static class Certificate {
            private Certificate() {}

            /**
             * 서명 결과로부터 공동인증서 정보 추출을 실패
             */
            @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "CERTIFICATE_EXTRACT_ERROR")
            public static class ExtractError extends CrosscertException {
                public ExtractError(String errorMessage, String errorDetailMessage) {
                    super(errorMessage, errorDetailMessage);
                }
            }
        }

        /**
         * 서명 결과 관련 오류
         */
        public static class Verifier {
            private Verifier() {}

            /**
             * 서명 결과 검증을 실패
             */
            @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "VERIFIER_VERIFY_ERROR")
            public static class VerifyError extends CrosscertException {
                public VerifyError(String errorMessage, String errorDetailMessage) {
                    super(errorMessage, errorDetailMessage);
                }
            }
        }
    }

    /**
     * 사용자 관련 오류
     */
    public static class User {
        private User() {}

        /**
         * 요청 정보로 탐색된 사용자가 없음
         */
        @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "USER_NOT_FOUND")
        public static class NotFound extends RuntimeException implements Redirect {
            public NotFound() {
                super("사용자를 찾을 수 없습니다. [확인]을 클릭하면 회원가입으로 이동합니다.");
            }

            public NotFound(String message) {
                super("사용자를 찾을 수 없습니다. : " + message + "\n[확인]을 클릭하면 회원가입으로 이동합니다");
            }

            @Override
            public String getRedirect() {
                return "/user/signUp.do";
            }
        }

        /**
         * 이미 등록된 이메일, 비밀번호, 공동인증서 중 하나 이상에 해당하는 정보로 회원가입 요청
         * @see studio.hcmc.egov.spring.boot.mvc.controller.user.UserController#signUp(Model, UserVO, HttpSession)
         */
        @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "USER_DUPLICATED")
        public static class Duplicated extends RuntimeException {
            public Duplicated() {
                super("이미 등록된 사용자입니다.");
            }

            public Duplicated(String message) {
                super("이미 등록된 사용자입니다: " + message);
            }
        }
    }
}
