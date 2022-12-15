package com.online.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "seller")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Seller {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "service")
    private String service;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "seller_product", joinColumns = {@JoinColumn(name = "seller_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    @JsonIgnore
    @ToString.Exclude
    private List<Product> products = new LinkedList<>();

    public Seller(String sellerName, String service, String contactNumber, String email, String city, String state, String postalCode, String country) {
        this.sellerName = sellerName;
        this.service = service;
        this.contactNumber = contactNumber;
        this.email = email;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public void addProductToSeller(Product productResponse) {
        this.products.add(productResponse);
    }

    public void removeSellerProduct(Product productResponse) {
        this.products.remove(productResponse);
    }

}
