package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserBasicDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserSimpleDto;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }
    User toEntityUpdate(UserDto userDto, User entity){
        return new User(
                userDto.Id(),
                userDto.firstName() == null ? entity.getFirstName() : userDto.firstName(),
                userDto.lastName() == null ? entity.getLastName() : userDto.lastName(),
                userDto.birthdate() == null ? entity.getBirthdate() : userDto.birthdate(),
                userDto.email() == null ? entity.getEmail() : userDto.email());

    }
    UserBasicDto toBasicDto(User user){
        return  new UserBasicDto(user.getId(), user.getEmail());
    }
    UserSimpleDto toSimpleDto(User user) {return new UserSimpleDto(user.getFirstName(), user.getLastName());}

}
