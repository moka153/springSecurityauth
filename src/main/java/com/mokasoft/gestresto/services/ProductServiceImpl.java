package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.dtos.OptionDto;
import com.mokasoft.gestresto.dtos.ProductDto;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.entities.Option;
import com.mokasoft.gestresto.entities.Product;
import com.mokasoft.gestresto.mappers.ProductMapper;
import com.mokasoft.gestresto.repositories.CategoryRepository;
import com.mokasoft.gestresto.repositories.OptionRepository;
import com.mokasoft.gestresto.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    private ProductRepository productRepository;

    private OptionRepository optionRepository;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        log.info("saving new Category");
        Category category = productMapper.fromCategoryDto(categoryDto);
        Category saveCategory = categoryRepository.save(category);
        return productMapper.fromCategory(saveCategory);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().
                map(cat -> productMapper.fromCategory(cat)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        log.info("saving new Product");
        Product product = productMapper.fromProductDto(productDto);
        Product saveProduct = productRepository.save(product);
        return productMapper.fromProduct(saveProduct);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> productMapper.fromProduct(product)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public OptionDto saveOption(OptionDto optionDto) {
        log.info("saving option");
        Option option = productMapper.fromOptionDto(optionDto);
        Option saveOption = optionRepository.save(option);
        return productMapper.fromOption(saveOption);
    }

    @Override
    public void deleteOption(Long idOption) {
        optionRepository.deleteById(idOption);
    }

    @Override
    public List<OptionDto> options() {
        List<Option> options = optionRepository.findAll();
        List<OptionDto> optionDtos = options.stream().
                map(option -> productMapper.fromOption(option)).collect(Collectors.toList());
        return optionDtos;
    }

    @Override
    public List<ProductDto> getProductByCategory(Category category) {
        List<Product> products = productRepository.findProductByCategory(category);
        List<ProductDto> productDtos = products.stream()
                .map(product -> productMapper.fromProduct(product)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<OptionDto> getProductOptions(ProductDto productDto) {
        Product product = productMapper.fromProductDto(productDto);
        List<Option> options = optionRepository.findOptionByProduct(product);
        List<OptionDto> optionDtos = options.stream()
                .map(option -> productMapper.fromOption(option)).collect(Collectors.toList());
        return optionDtos;
    }


}
