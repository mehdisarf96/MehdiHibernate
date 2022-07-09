package com.mehdisarf.inheritance.singletable;

import javax.persistence.Entity;

@Entity
public class Savings extends Account {

    private double interest;

    public Savings() {
    }

    public Savings(double balance, double interest){
        super(balance);
        this.interest = interest;
    }


}
