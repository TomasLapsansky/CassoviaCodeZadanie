package sk.cassoviacode.app.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.cassoviacode.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByLogin(String login);
    User getByEmail(String email);
    User findByLogin(String login);
}
