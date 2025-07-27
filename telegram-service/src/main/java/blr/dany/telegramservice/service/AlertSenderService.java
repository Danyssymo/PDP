package blr.dany.telegramservice.service;

import blr.dany.telegramservice.feign.response.KafkaAlert;

public interface AlertSenderService {

    void sendMessage(KafkaAlert alert);

}
