package blr.dany.weatheranalyzer.feign;

import blr.dany.weatheranalyzer.dto.request.KafkaAlert;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram-service", url = "${tg.service.url}")
public interface TelegramFeignClient {

    @PostMapping("/api/v1/telegram")
    void sendAlert(@RequestBody KafkaAlert alert);

}
