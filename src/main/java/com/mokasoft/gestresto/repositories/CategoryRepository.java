package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
