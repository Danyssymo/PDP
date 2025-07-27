package blr.dany.userservice.service;

import blr.dany.userservice.controller.request.UserRequestDto;
import blr.dany.userservice.entity.User;

import java.util.List;

public interface UserService {

    void save(UserRequestDto userRequestDto);
    User getUserByChatId(String chatId);
    void changeRegion(String chatId, String country);
    void changeSubscription(String chatId, boolean isSub);
    List<User> getUsersByChatIdAndCountry(Boolean isSub, String country);

}
