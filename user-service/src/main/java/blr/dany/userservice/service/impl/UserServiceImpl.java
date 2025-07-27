package blr.dany.userservice.service.impl;

import blr.dany.userservice.controller.request.UserRequestDto;
import blr.dany.userservice.entity.User;
import blr.dany.userservice.mapper.UserMapper;
import blr.dany.userservice.repository.UserRepository;
import blr.dany.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Transactional
    @Override
    public void save(UserRequestDto userRequestDto) {
        userRepository.save(userMapper.toEntity(userRequestDto));
    }

    @Transactional
    @Override
    public User getUserByChatId(String chatId) {
        return userRepository.findByChatId(chatId).orElse(null);
    }

    @Transactional
    @Override
    public void changeRegion(String chatId, String country) {
        userRepository.findByChatId(chatId).ifPresent(user -> user.setCountry(country));
    }

    @Transactional
    @Override
    public void changeSubscription(String chatId, boolean isSub) {
        userRepository.findByChatId(chatId).ifPresent(user -> user.setIsSub(isSub));
    }

    @Transactional
    @Override
    public List<User> getUsersByChatIdAndCountry(Boolean isSub, String country) {
        return userRepository.findByCountryAndIsSub(country, isSub);
    }


}
