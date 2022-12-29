package com.online.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "customer_detail")
@NoArgsConstructor
@Setter
@Getter
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "customer_detail_id")
    @JsonIgnore
    private List<Address> addresses;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public void addAddresses(Address addressRequest) {
        addresses.add(addressRequest);
    }

    public List<Address> retrieveAddresses() {
        if (addresses.size() > 2) {
            return this.addresses.subList(addresses.size() - 2, addresses.size());
        } else {
            return this.addresses;
        }
    }

    public void removeAddresses(Address addressRequest) {
        addresses.remove(addressRequest);
    }

}
