package blr.dany.telegramservice.feign;

import blr.dany.telegramservice.feign.request.CreateUserRequestDto;
import blr.dany.telegramservice.feign.response.TelegramUser;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @PostMapping("/api/v1/users")
    ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequestDto user);

    @GetMapping("/api/v1/users/{chatId}")
    ResponseEntity<TelegramUser> getUserByChatId(@PathVariable String chatId);

    @PatchMapping("/api/v1/users/{chatId}/region")
    ResponseEntity<Void> changeUserRegion(
            @PathVariable("chatId") String chatId,
            @RequestParam("country") String country
    );

    @PatchMapping("/api/v1/users/{chatId}/subscription")
    ResponseEntity<Void> changeUserSubscription(
            @PathVariable("chatId") String chatId,
            @RequestParam("isSub") boolean isSub
    );
}
