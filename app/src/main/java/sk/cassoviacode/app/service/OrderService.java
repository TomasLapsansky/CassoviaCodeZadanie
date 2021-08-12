package sk.cassoviacode.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.cassoviacode.app.entity.Order;
import sk.cassoviacode.app.entity.User;
import sk.cassoviacode.app.persistance.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order findById(Long orderId) {
        return orderRepository.getById(orderId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
