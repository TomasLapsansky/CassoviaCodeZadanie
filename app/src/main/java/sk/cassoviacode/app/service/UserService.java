package sk.cassoviacode.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.cassoviacode.app.entity.User;
import sk.cassoviacode.app.persistance.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    public User findByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    public User findByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
