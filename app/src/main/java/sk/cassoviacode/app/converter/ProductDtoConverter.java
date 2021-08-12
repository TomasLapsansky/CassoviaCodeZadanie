package sk.cassoviacode.app.converter;

import org.springframework.stereotype.Component;
import sk.cassoviacode.app.dto.ProductDto;
import sk.cassoviacode.app.dto.ProductPreviewDto;
import sk.cassoviacode.app.entity.Product;

@Component
public class ProductDtoConverter {

    public Product convertToProduct(ProductDto productDto) {
        Product product = null;

        if (productDto != null) {
            product = new Product();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
        }

        return product;
    }

    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = null;

        if (product != null) {
            productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
        }

        return productDto;
    }

    public ProductPreviewDto convertToProductPreviewDto(Product product) {
        ProductPreviewDto productPreviewDto = null;

        if (product != null) {
            productPreviewDto = new ProductPreviewDto();
            productPreviewDto.setId(product.getId());
            productPreviewDto.setName(product.getName());
            productPreviewDto.setDescription(product.getDescription());
        }

        return productPreviewDto;
    }
}
