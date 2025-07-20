package blr.dany.userservice.controller;

import blr.dany.userservice.controller.request.UserRequestDto;
import blr.dany.userservice.entity.User;
import blr.dany.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
