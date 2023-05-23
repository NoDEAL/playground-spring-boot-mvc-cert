package studio.hcmc.egov.spring.boot.mvc.vo;

import studio.hcmc.egov.spring.boot.mvc.ErrorResponse;

/**
 * 예외를 View에 표시
 */
public class ErrorVO {
    private Throwable throwable;

    private String redirect;

    /**
     * 예외
     * @return 예외
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * 예외
     * @param throwable 예외
     */
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
        if (throwable instanceof ErrorResponse.Redirect) {
            setRedirect(((ErrorResponse.Redirect) throwable).getRedirect());
        }
    }

    /**
     * 오류 페이지에서 이동할 경로
     * @return 대상 페이지
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * 오류 페이지에서 이동할 경로
     * @param redirect 대상 페이지
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
