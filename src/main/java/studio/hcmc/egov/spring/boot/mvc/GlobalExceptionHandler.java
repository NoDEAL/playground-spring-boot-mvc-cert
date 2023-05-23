package studio.hcmc.egov.spring.boot.mvc;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import studio.hcmc.egov.spring.boot.mvc.vo.ErrorVO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable throwable) {
        final var vo = new ErrorVO();
        vo.setThrowable(throwable);

        return new ModelAndView("/error", "error", vo);
    }
}
