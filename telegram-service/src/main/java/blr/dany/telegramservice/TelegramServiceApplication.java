package blr.dany.telegramservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "blr.dany.telegramservice.feign")
public class TelegramServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramServiceApplication.class, args);
    }

}
