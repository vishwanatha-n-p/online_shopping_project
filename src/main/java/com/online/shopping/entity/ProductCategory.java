package com.online.shopping.entity;

import com.online.shopping.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductCategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @Column(name = "status")
    private ProductStatus status;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime updatedAt;

    public ProductCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
