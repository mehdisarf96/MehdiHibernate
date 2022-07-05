package com.mehdisarf.associations.onetomany.usingjointable;

import javax.persistence.*;

@Entity
public class Address { // Many side.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String zipCode;

    /* I'm demonstrating One-To-many Unidirectionl. so field paein ro hazf mikonim
    chon this case is one to many and it is unidirectional;
    which means a Person has collection of Address.
    but from Address, it doesn't know the relationship about Person.

    @ManyToOne
    private Person person;

     */
    public Address() {
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
