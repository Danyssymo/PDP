package blr.dany.weatheranalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "blr.dany.weatheranalyzer.feign")
public class WeatherAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAnalyzerApplication.class, args);
    }

}
