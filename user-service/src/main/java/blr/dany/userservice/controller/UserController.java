package blr.dany.userservice.controller;

import blr.dany.userservice.controller.request.UserRequestDto;
import blr.dany.userservice.entity.User;
import blr.dany.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userService.save(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<User> getUserByChatId(@PathVariable String chatId) {
        return ResponseEntity.ok().body(userService.getUserByChatId(chatId));
    }

    @GetMapping
    ResponseEntity<List<User>> getUsersBySubAndCountry(
            @RequestParam("isSub") Boolean isSub,
            @RequestParam("country") String country) {
        return ResponseEntity.ok().body(userService.getUsersByChatIdAndCountry(isSub, country));
    }

    @PatchMapping("/{chatId}/region")
    public ResponseEntity<Void> changeUserRegion(@PathVariable String chatId, @RequestParam String country){
        userService.changeRegion(chatId, country);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{chatId}/subscription")
    public ResponseEntity<Void> changeUserSubscription(@PathVariable String chatId, @RequestParam boolean isSub){
        userService.changeSubscription(chatId, isSub);
        return ResponseEntity.ok().build();
    }

}
