package sk.cassoviacode.app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.cassoviacode.app.dto.OrderCreateDto;
import sk.cassoviacode.app.dto.OrderDto;
import sk.cassoviacode.app.dto.OrderItemDto;
import sk.cassoviacode.app.entity.Order;
import sk.cassoviacode.app.entity.OrderItem;
import sk.cassoviacode.app.entity.User;
import sk.cassoviacode.app.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDtoConverter {

    @Autowired
    private ProductDtoConverter productDtoConverter;

    @Autowired
    private UserService userService;

    public OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = null;

        if (order != null) {
            orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setName(order.getName());
            orderDto.setUserName(order.getUser().getLogin());

            List<OrderItemDto> orderItemsDto = new ArrayList<>();

            if (order.getOrderItems() != null) {
                /* For order creation */
                for (OrderItem orderItem : order.getOrderItems()) {
                    orderItemsDto.add(convertToOrderItemDto(orderItem));
                }
            }

            orderDto.setOrderItemsDto(orderItemsDto);
        }

        return orderDto;
    }

    private OrderItemDto convertToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = null;

        if (orderItem != null) {
            orderItemDto = new OrderItemDto();
            orderItemDto.setAmount(orderItem.getAmount());
            orderItemDto.setPrice(orderItem.getPrice());
            orderItemDto.setProductPreviewDto(productDtoConverter.convertToProductPreviewDto(orderItem.getProduct()));
        }

        return orderItemDto;
    }

    public Order convertToOrder(OrderCreateDto orderCreateDto) {
        Order order = null;

        if (orderCreateDto != null) {
            order = new Order();
            order.setName(orderCreateDto.getName());
            order.setDescription(orderCreateDto.getDescription());
            order.setChange(orderCreateDto.getChange());

            User user = userService.findById(orderCreateDto.getUserId());
            if (user == null) {
                return null;
            }

            order.setUser(user);
        }

        return order;
    }
}
