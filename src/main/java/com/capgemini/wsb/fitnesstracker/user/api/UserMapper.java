package com.capgemini.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    public static User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }
    public static User toEntityUpdate(UserDto userDto, User entity){
        return new User(
                userDto.Id(),
                userDto.firstName() == null ? entity.getFirstName() : userDto.firstName(),
                userDto.lastName() == null ? entity.getLastName() : userDto.lastName(),
                userDto.birthdate() == null ? entity.getBirthdate() : userDto.birthdate(),
                userDto.email() == null ? entity.getEmail() : userDto.email());

    }
    public static UserBasicDto toBasicDto(User user){
        return  new UserBasicDto(user.getId(), user.getEmail());
    }
    public static UserSimpleDto toSimpleDto(User user) {return new UserSimpleDto(user.getFirstName(), user.getLastName());}

}
