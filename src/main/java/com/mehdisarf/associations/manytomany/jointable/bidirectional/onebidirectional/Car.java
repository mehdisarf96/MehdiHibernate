package com.mehdisarf.associations.manytomany.jointable.bidirectional.onebidirectional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String color;

    @ManyToMany(mappedBy = "cars")
    private List<Person> persons = new ArrayList<>();

    public Car() {
    }

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // a convenient method
    public void addOwner(Person person) {
        this.persons.add(person);
    }

    @Override
    public String toString() {
        return "(Car" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", persons='" + persons + '\'' +
                ")";
    }
}