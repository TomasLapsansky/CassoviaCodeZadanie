package sk.cassoviacode.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sk.cassoviacode.app.converter.OrderDtoConverter;
import sk.cassoviacode.app.dto.OrderCreateDto;
import sk.cassoviacode.app.dto.OrderDto;
import sk.cassoviacode.app.entity.Order;
import sk.cassoviacode.app.entity.OrderItem;
import sk.cassoviacode.app.entity.Product;
import sk.cassoviacode.app.entity.User;
import sk.cassoviacode.app.service.OrderItemService;
import sk.cassoviacode.app.service.OrderService;
import sk.cassoviacode.app.service.ProductService;
import sk.cassoviacode.app.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDtoConverter orderDtoConverter;

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        List<Order> allOrders = orderService.findAll();
        List<OrderDto> allOrdersDto = new ArrayList<>();

        for (Order order: allOrders) {
            allOrdersDto.add(orderDtoConverter.convertToOrderDto(order));
        }

        return allOrdersDto;
    }

    @GetMapping("/orders/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {

        Order order = orderService.findById(orderId);

        return orderDtoConverter.convertToOrderDto(order);
    }

    @GetMapping("/orders/user/{userId}")
    public List<OrderDto> getAllUserOrders(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        List<OrderDto> allOrdersDto = new ArrayList<>();

        if (user != null) {
            List<Order> allOrders = orderService.findAllByUser(user);

            for (Order order: allOrders) {
                allOrdersDto.add(orderDtoConverter.convertToOrderDto(order));
            }
        }

        return allOrdersDto;
    }

    @PostMapping("/orders/create")
    public boolean createOrder(@RequestBody OrderCreateDto orderCreateDto) {

        /* Get logged user id */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = "";

        if (authentication != null) {
            login = authentication.getName();
        }

        User user = userService.findByLogin(login);

        if (user == null ) {
            return false;
        }

        orderCreateDto.setUserId(user.getId());

        /* Check productIds first */
        List<Product> products = new ArrayList<>();
        for (Long productId: orderCreateDto.getProductIds()) {

            Product product = productService.findById(productId);
            if (product != null) {
                /* Save to product list */
                products.add(product);
            } else {
                /* Return null without saving anything */
                return false;
            }
        }

        Order order = orderDtoConverter.convertToOrder(orderCreateDto);

        order = orderService.save(order);

        for (Product product: products) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setAmount(1); // maybe in the future
            orderItem.setPrice(product.getPrice());
            orderItem.setProduct(product);

            orderItemService.save(orderItem);
        }

        /* Items were not loaded */
        return true;
    }
}
