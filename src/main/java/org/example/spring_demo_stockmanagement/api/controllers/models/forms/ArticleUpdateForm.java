package org.example.spring_demo_stockmanagement.api.controllers.models.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.pl.validators.ImageFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record ArticleUpdateForm(
        @NotBlank(message = "Champ vide")
        String designation,
        @Min(0)
        double unitPriceExcludingTax,
        VAT vat,
        @ImageFormat
        MultipartFile image,
        UUID categoryId,
        UUID id,
        String imageUrl
){


    public Article toArticle() {
        return new Article(
                this.id,
                this.designation(),
                (long) this.unitPriceExcludingTax(),
                this.vat()
        );
    }
}