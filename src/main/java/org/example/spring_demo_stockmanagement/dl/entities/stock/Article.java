package org.example.spring_demo_stockmanagement.dl.entities.stock;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.BaseEntity;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"designation", "unitPriceExcludingTax", "vat", "category"})
@ToString(callSuper = true)
public class Article extends BaseEntity {

    @Getter @Setter
    @Column(nullable = false, unique = true, length = 80)
    private String designation;

    @Getter @Setter
    @Column(nullable = false)
    @Min(0L)
    private long unitPriceExcludingTax;

    @Getter @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VAT vat;

    @Getter @Setter
    @Column(unique = true)
    private String picture;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToOne(mappedBy = "article", fetch = FetchType.EAGER)
    private Stock stock;

    @Column(nullable = false)
    private final boolean isDeleted = false;



    public Article(UUID id, String designation, long unitPriceExcludingTaxe, VAT vat, String picture, Category category) {
        super(id);
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTaxe;
        this.vat = vat;
        this.picture = picture;
        this.category = category;
    }

    public Article(String designation, long unitPriceExcludingTaxe, VAT vat) {
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTaxe;
        this.vat = vat;
    }

    public Article(UUID id, String designation, long unitPriceExcludingTax, VAT vat) {
        super(id);
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
    }

    public long getUnitPriceIncludingTax(){
        return getUnitPriceExcludingTax() + getAddedValue();
    }

    public long getAddedValue(){
        BigDecimal vat = BigDecimal.valueOf(this.vat.value,2);
        BigDecimal priceTTE = BigDecimal.valueOf(this.unitPriceExcludingTax);
        BigDecimal addedValue = priceTTE.multiply(vat);
        return addedValue.setScale(2, RoundingMode.HALF_UP).longValue();
    }

    protected void setStock(Stock stock) {
        this.stock = stock;
    }
}