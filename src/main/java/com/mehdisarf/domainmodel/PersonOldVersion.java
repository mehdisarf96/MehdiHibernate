package com.mehdisarf.domainmodel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "mycustomizedperson")
//@Entity(name = "people")
// (class name: Person) ---- (entity name: people) ---- (table name: mycustomizedperson)
public class PersonOldVersion { // esme qabli in class Person bud.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    private String lastName;

    @Column(unique = true, length = 250)
    private String email;

    @Temporal(value = TemporalType.DATE) // before java 7
    private Date birthDate; // before java 7

    // there is no need to use @Temporal. LocalDate will automatically map to DATE (in DB).
    private LocalDate bd;

    @Transient
    private int temp;

    public PersonOldVersion() {
    }

    public PersonOldVersion(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonOldVersion(String firstName, String lastName, String email, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public PersonOldVersion(String firstName, String lastName, String email, Date birthDate, LocalDate bd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.bd = bd;
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
