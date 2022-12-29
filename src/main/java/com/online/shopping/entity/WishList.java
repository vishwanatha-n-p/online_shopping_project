package com.online.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "wishlist")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class WishList {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "wishlist_product", joinColumns = {@JoinColumn(name = "wishlist_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    @ToString.Exclude
    private List<Product> products = new LinkedList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(List<Product> productsRequest) {
        Iterator<Product> productIterator = productsRequest.iterator();
        while(productIterator.hasNext()){
            Product product = productIterator.next();
            this.products.remove(product);
        }
    }

}
