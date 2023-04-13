package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.ProductRequest;
import com.mokasoft.gestresto.dtos.ProductResponse;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.entities.Option;
import com.mokasoft.gestresto.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product productRequestToProduct(ProductRequest productRequest){
        Category category = new Category();
        category.setCategoryId(productRequest.getCategoryId());
        Product product = Product.builder()
                .designation(productRequest.getDesignation())
                .description(productRequest.getDescription())
                .type(productRequest.getType())
                .costPrice(productRequest.getCostPrice())
                .price(productRequest.getPrice())
                .picture(productRequest.getPicture())
                .quantity(productRequest.getQuantity())
                .category(category)
                .available(productRequest.isAvailable())
                .isTodaySpecial(productRequest.isTodaySpecial())
                .build();
        return product;
    }
    public ProductResponse productToProductResponse(Product product){
        ProductResponse productResponse = ProductResponse.builder()
                .productId(product.getProductId())
                .designation(product.getDesignation())
                .description(product.getDescription())
                .type(product.getType())
                .costPrice(product.getCostPrice())
                .price(product.getPrice())
                .picture(product.getPicture())
                .quantity(product.getQuantity())
                .category(new CategoryMapper().categoryToCategoryResponse(product.getCategory()))
                .available(product.isAvailable())
                .isTodaySpecial(product.isTodaySpecial())
                .build();
        return productResponse;
    }

}
