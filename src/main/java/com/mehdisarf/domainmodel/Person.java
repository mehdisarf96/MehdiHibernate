package com.mehdisarf.domainmodel;

import javax.persistence.*;

@Table(name = "mycustomizedperson")
@Entity(name = "people")
// (class name: Person) ---- (entity name: people) ---- (table name: mycustomizedperson)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    private String lastName;

    // unique in @Column is used only if you let your JPA provider create the database for you - it will create
    // the unique constraint on the specified column.
    // But if you already have the database, or you alter it once created, then unique doesn't have any effect.
    @Column(unique = true, length = 250)
    private String email;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
