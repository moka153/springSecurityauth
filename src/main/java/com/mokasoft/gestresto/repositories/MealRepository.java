package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long> {
}
