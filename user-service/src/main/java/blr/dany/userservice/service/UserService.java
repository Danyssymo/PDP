package blr.dany.userservice.service;

import blr.dany.userservice.controller.request.UserRequestDto;
import blr.dany.userservice.entity.User;

public interface UserService {

    void save(UserRequestDto userRequestDto);
    User getUserByChatId(String chatId);

}
