package blr.dany.telegramservice.controller;

import blr.dany.telegramservice.feign.response.KafkaAlert;
import blr.dany.telegramservice.service.AlertSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/telegram")
@RequiredArgsConstructor
public class TelegramController {

    private final AlertSenderService alertSenderService;

    @PostMapping
    public void sendAlert(@RequestBody KafkaAlert alert){
        alertSenderService.sendMessage(alert);
    }

}
