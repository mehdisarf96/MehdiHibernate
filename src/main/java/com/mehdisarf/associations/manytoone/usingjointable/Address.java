package com.mehdisarf.associations.manytoone.usingjointable;

import javax.persistence.*;

@Entity
public class Address { // Many side.

    @Id
    private Long id;
    private String street;
    private String zipCode;

    @ManyToOne
    @JoinTable(name = "person_address")
    private Person person;

    /*
        mituni asami column haye Join Table ro customize koni:
            @JoinTable(name = "person_address", joinColumns = {@JoinColumn(name = "address_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
     */

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