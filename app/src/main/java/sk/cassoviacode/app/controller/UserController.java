package sk.cassoviacode.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.cassoviacode.app.converter.UserDtoConverter;
import sk.cassoviacode.app.dto.UserDto;
import sk.cassoviacode.app.entity.User;
import sk.cassoviacode.app.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoConverter userDtoConverter;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userService.findAll();
        List<UserDto> allUsersDto = new ArrayList<>();

        for (User user: allUsers) {
            allUsersDto.add(userDtoConverter.convertToUserDto(user));
        }

        return allUsersDto;
    }

    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable("userId") Long userId) {

        User user = userService.findById(userId);

        return userDtoConverter.convertToUserDto(user);
    }

    @PostMapping("/users/create")
    public UserDto createUser(@RequestBody UserDto userDto) {

        User user;

        if ((user = userService.findByLogin(userDto.getLogin())) != null) {
            return userDtoConverter.convertToUserDto(user);
        }

        if ((user = userService.findByEmail(userDto.getEmail())) != null) {
            return userDtoConverter.convertToUserDto(user);
        }

        user = userDtoConverter.convertToUser(userDto);

        user = userService.save(user);

        return userDtoConverter.convertToUserDto(user);
    }

    @DeleteMapping("/users/delete/{userId}")
    public boolean deleteUser(@PathVariable("userId") Long userId) {

        User user = userService.findById(userId);

        if (user != null) {
            userService.delete(user);
            return true;
        } else {
            return false;
        }
    }
}
