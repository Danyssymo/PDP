package blr.dany.userservice.mapper;

import blr.dany.userservice.controller.request.UserRequestDto;
import blr.dany.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "telegramName", source = "telegramName")
    User toEntity (UserRequestDto userRequestDto);

}
