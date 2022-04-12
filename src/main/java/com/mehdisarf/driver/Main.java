package com.mehdisarf.driver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        // Just this line. Run and see what happens.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");
    }
}
