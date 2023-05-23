package studio.hcmc.egov.spring.boot.mvc;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Launcher {
    /**
     * 애플리케이션 시작점
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Launcher.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setLogStartupInfo(true);
        springApplication.run(args);
    }
}
