package org.example.spring_demo_stockmanagement.pl.models.dto;

import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;

import java.util.UUID;

public record ArticleDetailsDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        double addedValue,
        VAT vat,
        String picture,
        String category,
        int quantity
){
    public static ArticleDetailsDTO detailsArticle(Stock stock){
        Article article = stock.getArticle();
        return new ArticleDetailsDTO(
                article.getId(),
                article.getDesignation(),
                article.getUnitPriceIncludingTax()/100D,
                article.getUnitPriceIncludingTax()/100D,
                article.getAddedValue(),
                article.getVat(),
                article.getPicture(),
                article.getCategory().getDesignation(),
                stock.getCurrentQuantity()
        );
    }
}
