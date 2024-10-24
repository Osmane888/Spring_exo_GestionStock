package org.example.spring_demo_stockmanagement.pl.models.forms;

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

@Data
@NoArgsConstructor
public class ArticleForm {

    @NotBlank(message = "Champ vide")
    private String designation;
    @Min(0)
    private double unitPriceExcludingTax;
    private VAT vat;
    @ImageFormat
    private MultipartFile image;
    private UUID categoryId;


    public ArticleForm(String designation, double unitPriceExcludingTax, VAT vat, UUID categoryId) {
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
        this.categoryId = categoryId;
    }

    public Article toArticle(){
        return new Article(
                this.designation,
                (long) unitPriceExcludingTax,
                vat
        );
    }

}
