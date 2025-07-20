package blr.dany.telegramservice.feign;

import blr.dany.telegramservice.feign.request.CreateUserRequestDto;
import blr.dany.telegramservice.feign.response.TelegramUser;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @PostMapping("/api/v1/users")
    ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequestDto user);

    @GetMapping("/api/v1/users/{chatId}")
    ResponseEntity<TelegramUser> getUserByChatId(@PathVariable String chatId);

}
