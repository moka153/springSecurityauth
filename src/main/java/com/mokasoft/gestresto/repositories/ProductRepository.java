package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
