package com.mehdisarf.inheritance.singletable.customizeddiscriminator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Savings extends Account {

    private double interest;

    public Savings() {
    }

    public Savings(double balance, double interest){
        super(balance);
        this.interest = interest;
    }
}
