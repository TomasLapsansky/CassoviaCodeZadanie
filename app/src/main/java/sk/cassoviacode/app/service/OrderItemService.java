package sk.cassoviacode.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.cassoviacode.app.entity.Order;
import sk.cassoviacode.app.entity.OrderItem;
import sk.cassoviacode.app.persistance.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAllByOrder(Order order) {
        return orderItemRepository.findAllByOrder(order);
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
