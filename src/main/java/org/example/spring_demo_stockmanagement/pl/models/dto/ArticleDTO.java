package org.example.spring_demo_stockmanagement.pl.models.dto;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;

import java.util.UUID;

public record ArticleDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        String category,
        int quantity
) {

    public static ArticleDTO fromStock(Stock stock){
        Article article = stock.getArticle();
        return new ArticleDTO(
                article.getId(),
                article.getDesignation(),
                article.getUnitPriceIncludingTax()/10D,
                article.getUnitPriceIncludingTax()/10D,
                article.getCategory().getDesignation(),
                stock.getCurrentQuantity()
        );
    }
}
