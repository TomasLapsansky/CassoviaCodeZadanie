package sk.cassoviacode.app.dto;

public class OrderItemDto {

    private Integer amount;

    private Double price;

    private ProductPreviewDto productPreviewDto;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductPreviewDto getProductPreviewDto() {
        return productPreviewDto;
    }

    public void setProductPreviewDto(ProductPreviewDto productPreviewDto) {
        this.productPreviewDto = productPreviewDto;
    }
}
