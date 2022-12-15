package com.online.shopping.entity;

import com.online.shopping.enums.ProductStatus;
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
@Table(name = "product_types")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_subcategory_id")
    private ProductSubcategory productSubcategory;

    @Column(name = "status", unique = true)
    private ProductStatus status;

    public ProductType(String productType) {
        this.productType = productType;
    }

}
