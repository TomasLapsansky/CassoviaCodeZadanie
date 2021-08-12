package sk.cassoviacode.app.dto;

import java.util.List;

public class OrderDto {

    private Long id;

    private String name;

    private Double price;

    private String userName;

    private List<OrderItemDto> orderItemsDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {

        double price = 0.0;

        for (OrderItemDto orderItemDto: this.getOrderItemsDto()) {
            price += orderItemDto.getPrice() * orderItemDto.getAmount();
        }

        return price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderItemDto> getOrderItemsDto() {
        return orderItemsDto;
    }

    public void setOrderItemsDto(List<OrderItemDto> orderItemsDto) {
        this.orderItemsDto = orderItemsDto;
    }
}
