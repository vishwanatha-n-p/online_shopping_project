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
@Table(name = "product_subcategory")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "subcategory_name", unique = true)
    private String subcategoryName;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @Column(name = "status")
    private ProductStatus status;

    public ProductSubcategory(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

}
