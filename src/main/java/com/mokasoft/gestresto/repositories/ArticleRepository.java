package com.mokasoft.gestresto.repositories;

import com.mokasoft.gestresto.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
