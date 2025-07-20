package blr.dany.telegramservice.feign.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequestDto {

    @NotBlank(message = "Имя не должно быть пустым")
    private String telegramName;
    @NotNull(message = "Поле подписки обязательно")
    private Boolean isSub;
    @NotNull(message = "Страна обязательна")
    private String country;
    @NotNull(message = "Чат айди обязателен")
    private String chatId;

}
