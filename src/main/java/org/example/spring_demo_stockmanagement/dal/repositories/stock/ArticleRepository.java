package org.example.spring_demo_stockmanagement.dal.repositories.stock;

import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {

    @Query("select count(a) > 0 from Article  a where a.designation ilike :designation")
    boolean existsByDesignation(String designation);

}