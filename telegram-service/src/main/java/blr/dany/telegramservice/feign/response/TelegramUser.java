package blr.dany.telegramservice.feign.response;

import lombok.Data;

import java.util.UUID;

@Data
public class TelegramUser {

    private String telegramName;
    private Boolean isSub;
    private String country;
    private String chatId;

}
