package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin(); // Begin Transaction

        Person person = new Person(452L, "Mehdi", "Sarf");
        entityManager.persist(person);

        transaction.commit(); // Commit Transaction

        entityManager.close();
    }
}
