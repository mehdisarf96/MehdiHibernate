package com.mehdisarf.inheritance.tableperconcereteclass;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Checking extends Account {

    @Column(name = "[limit]") // chon limit ye keyword e tu MySQL.
    private double limit;

    public Checking() {
    }

    public Checking(double balance, double limit){
        super(balance);
        this.limit = limit;
    }
}
