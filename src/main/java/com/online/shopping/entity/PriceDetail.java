package com.online.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_detail")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @Column(name = "seller_name")
    private String sellerName;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "price")
    private long price;

    @Column(name = "product_count")
    private int productCount;

    @Column(name = "discount")
    private String discount;

    @Column(name = "special_offer_discount")
    private int specialOfferDiscount;

    @Column(name = "delivery_charge")
    private int deliveryCharge;

    @Column(name = "final_price")
    private long finalPrice;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PriceDetail(String sellerName, long price, int productCount, String discount, int specialOfferDiscount, int deliveryCharge, long finalPrice) {
        this.sellerName = sellerName;
        this.price = price;
        this.productCount = productCount;
        this.discount = discount;
        this.specialOfferDiscount = specialOfferDiscount;
        this.deliveryCharge = deliveryCharge;
        this.finalPrice = finalPrice;
    }

}
