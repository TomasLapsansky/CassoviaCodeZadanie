package sk.cassoviacode.app.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.cassoviacode.app.entity.Order;
import sk.cassoviacode.app.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder(Order order);
}
