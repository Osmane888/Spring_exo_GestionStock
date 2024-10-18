package org.example.spring_demo_stockmanagement.pl.models.forms;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ArticleForm {

    private String designation;
    private double unitPriceExcludingTax;
    private VAT vat;
    private MultipartFile image;
    private UUID categoryId;

    public Article toArticle(){
        String price = String.valueOf(this.unitPriceExcludingTax);
        return new Article(
                this.designation,
                (long) unitPriceExcludingTax,
                vat
        );
    }

}
