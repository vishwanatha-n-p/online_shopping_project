package com.online.shopping.entity;

import com.online.shopping.enums.OrderStatus;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "my_orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MyOrders {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "color")
    private String color;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdate;

    public MyOrders(String productName, String color, int quantity, long totalPrice, LocalDateTime orderDate) {
        this.productName = productName;
        this.color = color;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

}
