package com.online.shopping.entity;

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

@Table(name = "currency")
@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Currency {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country", unique = true)
    private String country;

    @Column(name = "currency_symbol")
    private String symbol;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime localDatetime;

    public Currency(String country, String symbol) {
        this.country = country;
        this.symbol = symbol;
    }

}
