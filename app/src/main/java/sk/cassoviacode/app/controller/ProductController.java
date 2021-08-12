package sk.cassoviacode.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.cassoviacode.app.converter.ProductDtoConverter;
import sk.cassoviacode.app.dto.ProductDto;
import sk.cassoviacode.app.entity.Product;
import sk.cassoviacode.app.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDtoConverter productDtoConverter;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productService.findAll();
        List<ProductDto> allProductsDto = new ArrayList<>();

        for (Product product: allProducts) {
            allProductsDto.add(productDtoConverter.convertToProductDto(product));
        }

        return allProductsDto;
    }

    @GetMapping("/products/{productId}")
    public ProductDto getProduct(@PathVariable("productId") Long productId) {

        Product product = productService.findById(productId);

        return productDtoConverter.convertToProductDto(product);
    }

    @PostMapping("/products/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {

        Product product = productDtoConverter.convertToProduct(productDto);

        product = productService.save(product);

        return productDtoConverter.convertToProductDto(product);
    }

    @PatchMapping("/products/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {

        Product product = productService.findById(productDto.getId());

        if (product == null) {
            return null;
        }

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        product = productService.save(product);

        return productDtoConverter.convertToProductDto(product);
    }

    @DeleteMapping("/products/delete/{productId}")
    public boolean deleteProduct(@PathVariable("productId") Long productId) {

        Product product = productService.findById(productId);

        if (product != null) {
            productService.delete(product);
            return true;
        } else {
            return false;
        }
    }
}
