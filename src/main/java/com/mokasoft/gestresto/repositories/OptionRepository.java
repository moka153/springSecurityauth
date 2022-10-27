package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Option;
import com.mokasoft.gestresto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option,Long> {
    List<Option> findOptionByProduct(Product product);
}
