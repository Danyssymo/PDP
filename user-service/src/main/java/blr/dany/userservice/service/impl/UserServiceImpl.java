package blr.dany.userservice.service.impl;

import blr.dany.userservice.controller.request.UserRequestDto;
import blr.dany.userservice.entity.User;
import blr.dany.userservice.mapper.UserMapper;
import blr.dany.userservice.repository.UserRepository;
import blr.dany.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void save(UserRequestDto userRequestDto) {
        userRepository.save(userMapper.toEntity(userRequestDto));
    }

    @Override
    public User getUserByChatId(String chatId) {
        return userRepository.findByChatId(chatId).orElse(null);
    }


}
