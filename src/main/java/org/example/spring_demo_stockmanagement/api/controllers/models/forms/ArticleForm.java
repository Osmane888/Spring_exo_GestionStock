package org.example.spring_demo_stockmanagement.api.controllers.models.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.pl.validators.ImageFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


public record ArticleForm(

        @NotBlank(message = "Champ vide")
        String designation,
        @Min(0)
        double unitPriceExcludingTax,
        VAT vat,
        @ImageFormat
        MultipartFile image,
        UUID categoryId

) {

    public Article toArticle(){
        return new Article(
                this.designation,
                (long) unitPriceExcludingTax,
                vat
        );
    }

}
