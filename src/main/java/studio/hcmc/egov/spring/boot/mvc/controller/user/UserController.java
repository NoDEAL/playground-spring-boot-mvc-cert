package studio.hcmc.egov.spring.boot.mvc.controller.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import studio.hcmc.egov.spring.boot.mvc.ErrorResponse;
import studio.hcmc.egov.spring.boot.mvc.domain.user.UserDomain;
import studio.hcmc.egov.spring.boot.mvc.entity.user.UserEntity;
import studio.hcmc.egov.spring.boot.mvc.service.user.UserService;
import studio.hcmc.egov.spring.boot.mvc.util.CertificateHelper;
import studio.hcmc.egov.spring.boot.mvc.vo.user.UserVO;

@Controller
public class UserController {
    private final UserService userService;

    /**
     * Bean 객체 의존성 주입을 위한 생성자
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 로그인 View
     */
    @RequestMapping(
            value = "/user/signIn.do",
            method = RequestMethod.GET
    )
    public String signInView(
            Model model,
            HttpSession session
    ) {
        final var user = (UserDomain) session.getAttribute("user");
        if (user != null) {
            // 사용자 정보가 세션에 존재: 이미 로그인함
            return "redirect:/home.do";
        }

        model.addAttribute("userVO", new UserVO());

        return "/user/signIn";
    }

    /**
     * 로그인
     */
    @RequestMapping(
            value = "/user/signIn.do",
            method = RequestMethod.POST
    )
    public String signIn(
            Model model,
            UserVO userVO,
            HttpSession session
    ) {
        final var email = userVO.getEmail();
        if (email == null) {
            // TODO validate: email
            throw new AssertionError("email is null");
        }

        final var signedData = userVO.getSignedData();
        if (signedData == null) {
            // TODO validate: signedData
            throw new AssertionError("signedData is null");
        }

        final var certificate = CertificateHelper.decodeCertificate(signedData);
        final var userEntity = userService
                .findByEmailAndSubject(email, certificate.subject)
                .orElseThrow(ErrorResponse.User.NotFound::new);
        // 이메일은 존재하나 등록되지 않은 인증서: 비밀번호가 틀린 것과 같음
        session.setAttribute("user", userEntity);

        return "redirect:/home.do";
    }

    /**
     * 회원가입 View
     */
    @RequestMapping(
            value = "/user/signUp.do",
            method = RequestMethod.GET
    )
    public String signUpView(
            Model model,
            HttpSession session
    ) {
        final var user = (UserDomain) session.getAttribute("user");
        if (user != null) {
            // 사용자 정보가 세션에 존재: 이미 로그인함
            return "redirect:/home.do";
        }

        model.addAttribute("userVO", new UserVO());

        return "/user/signUp";
    }

    /**
     * 회원가입
     */
    @RequestMapping(
            value = "/user/signUp.do",
            method = RequestMethod.POST
    )
    public String signUp(
            Model model,
            UserVO userVO,
            HttpSession session
    ) {
        final var email = userVO.getEmail();
        if (email == null) {
            // TODO validate: email
            throw new AssertionError("email is null");
        }

        final var password = userVO.getPassword();
        if (password == null) {
            // TODO validate: password
            throw new AssertionError("password is null");
        }

        final var signedData = userVO.getSignedData();
        if (signedData == null) {
            // TODO validate: signedData
            throw new AssertionError("signedData is null");
        }

        final var certificate = CertificateHelper.decodeCertificate(signedData);
        userService.findByEmailOrPasswordOrSubject(
            email,
            password,
            certificate.subject
        ).ifPresent(o -> {
            // 이메일, 비밀번호, 공동인증서가 하나라도 중복될 수 없음
            throw new ErrorResponse.User.Duplicated();
        });

        final var savedUserEntity = userService.save(
                new UserEntity(
                        userVO.getId() /* no effect */,
                        userVO.getEmail(),
                        userVO.getPassword(),
                        certificate.subject
                )
        );
        // 회원가입 후 자동 로그인을 적용하려면 세션에 저장

        return "redirect:/user/signIn.do";
    }

    /**
     * 공동인증서 등록 View
     */
    @RequestMapping(
            value = "/user/registerCertificate.do",
            method = RequestMethod.GET
    )
    public String registerCertificateView(
            Model model,
            HttpSession session
    ) {
        final var user = (UserDomain) session.getAttribute("user");
        if (user != null) {
            // 사용자 정보가 세션에 존재: 이미 로그인함
            return "redirect:/home.do";
        }

        model.addAttribute("userVO", new UserVO());

        return "/user/registerCertificate";
    }

    /**
     * 공동인증서 등록
     */
    @RequestMapping(
            value = "/user/registerCertificate.do",
            method = RequestMethod.POST
    )
    public String registerCertificate(
            Model model,
            UserVO userVO,
            HttpSession session
    ) {
        final var email = userVO.getEmail();
        if (email == null) {
            // TODO validate: email
            throw new AssertionError("email is null");
        }

        final var password = userVO.getPassword();
        if (password == null) {
            // TODO validate: password
            throw new AssertionError("password is null");
        }

        final var signedData = userVO.getSignedData();
        if (signedData == null) {
            // TODO validate: signedData
            throw new AssertionError("signedData is null");
        }

        // 이메일, 비밀번호 존재 여부 판단
        final var userEntity = userService
            .findByEmailAndPassword(email, password)
            .orElseThrow(ErrorResponse.User.NotFound::new);
        final var certificate = CertificateHelper.decodeCertificate(signedData);
        userEntity.setSubject(certificate.subject);
        userService.save(userEntity);

        return "redirect:/user/signIn.do";
    }
}