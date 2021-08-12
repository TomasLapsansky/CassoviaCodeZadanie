package sk.cassoviacode.app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sk.cassoviacode.app.dto.UserDto;
import sk.cassoviacode.app.entity.User;

@Component
public class UserDtoConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User convertToUser(UserDto userDto) {
        User user = null;

        if (userDto != null) {
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setLogin(userDto.getLogin());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return user;
    }

    public UserDto convertToUserDto(User user) {
        UserDto userDto = null;

        if (user != null) {
            userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setLogin(user.getLogin());
        }

        return userDto;
    }
}
