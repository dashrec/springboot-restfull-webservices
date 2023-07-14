package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Client--> controller layer  --> service layer --> repository layer/jpa --> DB

@Service
@AllArgsConstructor // automatically create constructor for UserRepository
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper; // inject modelMapper

    @Override
    public UserDto createUser(UserDto userDto) { // get userDto from controller

           // User user = UserMapper.mapToUser(userDto);
          //  User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for this User");
        }



        User user = AutoUserMapper.MAPPER.mapToUser(userDto);        // Convert UserDto into User JPA Entity
        User savedUser = userRepository.save(user);                  //  save user database
           // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
           // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);          //  Convert User JPA entity to UserDto

        return savedUserDto;

    }



    @Override
    public UserDto getUserByID(Long userId) {
      User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
      // return modelMapper.map(user, UserDto.class); // convert user jpa entity to user dto
      return AutoUserMapper.MAPPER.mapToUserDto(user); // convert user jpa entity to user dto
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll(); // gets list of jpa entity
       // return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList()); // convert to list of UserDto old
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList()); // convert to list of UserDto
    }

    @Override // updateUser method expects UserDto in response/return hence we convert usr jpa to userDto
    public UserDto updateUser(UserDto user) { // this user is sent by client

        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);

      //  return modelMapper.map(updatedUser, UserDto.class); //old

        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }


}
