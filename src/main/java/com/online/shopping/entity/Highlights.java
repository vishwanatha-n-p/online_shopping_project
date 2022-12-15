package com.online.shopping.entity;

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
@Table(name = "highlights")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Highlights {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "features")
    private String features;

    @Column(name = "size")
    private String size;


    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime updatedAt;

    public Highlights(String modelNumber, String features, String size) {
        this.modelNumber = modelNumber;
        this.features = features;
        this.size = size;
    }

}