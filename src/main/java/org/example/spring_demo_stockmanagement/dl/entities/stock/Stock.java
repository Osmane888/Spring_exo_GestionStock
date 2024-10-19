package org.example.spring_demo_stockmanagement.dl.entities.stock;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Stock extends BaseEntity {

    @Column(nullable = false)
    private int currentQuantity;

    @OneToOne(fetch = FetchType.EAGER)
    private Article article;

    public Stock(UUID id, int currentQuantity, Article article) {
        super(id);
        this.currentQuantity = currentQuantity;
        this.setArticle(article);
    }

    public void setArticle(Article article) {
        this.article = article;
        article.setStock(this);
    }
}
