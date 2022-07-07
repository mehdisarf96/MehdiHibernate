package com.mehdisarf.associations.onetoone.unidirectional.sharedpk;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address { // Many side.

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // comment mikonam ta issue bartaraf she.
    private Long id;
    private String street;
    private String zipCode;

    public Address() {
    }

    public Address(Long id, String street, String zipCode) {
        this.id = id;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
