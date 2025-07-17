package blr.dany.userservice.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "Имя не должно быть пустым")
    private String telegramName;
    @NotNull(message = "Поле подписки обязательно")
    private Boolean isSub;
    @NotNull(message = "Страна обязательна")
    private String country;

}
