package sk.cassoviacode.app.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.cassoviacode.app.entity.Order;
import sk.cassoviacode.app.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);
}
