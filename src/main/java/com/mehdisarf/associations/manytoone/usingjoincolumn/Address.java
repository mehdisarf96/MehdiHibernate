package com.mehdisarf.associations.manytoone.usingjoincolumn;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//@Entity
public class Address { // Many side.

    @Id
    private Long id;
    private String street;
    private String zipCode;

    @ManyToOne
    // @JoinColumn(name = "person_id")
    private Person person;

    public Address() {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
