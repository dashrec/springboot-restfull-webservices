package net.javaguides.springboot.mapper;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    // @Mapping(source="email", target = "emailAdress") // to handle specific usecase like different email addresses in User and userDto

    UserDto mapToUserDto(User user); // convert to UserDto

    User mapToUser(UserDto userDto); // map to User

}
