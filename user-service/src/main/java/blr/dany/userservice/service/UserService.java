package blr.dany.userservice.service;

import blr.dany.userservice.controller.request.UserRequestDto;

public interface UserService {

    void save(UserRequestDto userRequestDto);

}
