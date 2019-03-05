package io.jmlim.commonweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "accountAuditAware") // 얘는 자동설정 되지 않음.
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
