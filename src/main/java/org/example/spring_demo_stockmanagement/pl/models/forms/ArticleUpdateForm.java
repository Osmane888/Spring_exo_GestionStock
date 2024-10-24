package org.example.spring_demo_stockmanagement.pl.models.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.pl.validators.ImageFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class ArticleUpdateForm extends ArticleForm{

    private UUID id;
    private String imageUrl;

    public ArticleUpdateForm(UUID id,String designation, double unitPriceExcludingTax, VAT vat, UUID categoryId, String imageUrl) {
        super(designation, unitPriceExcludingTax, vat, categoryId);
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public static ArticleUpdateForm fromArticle(Article article){
        return new ArticleUpdateForm(
                article.getId(),
                article.getDesignation(),
                article.getUnitPriceExcludingTax(),
                article.getVat(),
                article.getCategory().getId(),
                article.getPicture()
        );
    }

    @Override
    public Article toArticle() {
        return new Article(
                this.id,
                this.getDesignation(),
                (long) this.getUnitPriceExcludingTax(),
                this.getVat()
        );
    }
}
