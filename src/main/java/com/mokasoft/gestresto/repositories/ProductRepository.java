package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByCategory(Category category);
}
