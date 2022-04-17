package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();

        Person p1 = new Person("Mehdi", "Sarf");

        entityManager.getTransaction().begin(); // Begin Transaction

        System.out.println("-----1------");
        entityManager.persist(p1);
        System.out.println(p1);
        System.out.println("-----2------");
        p1.setFirstName("Meyti");
        System.out.println("-----3------");
        entityManager.getTransaction().commit(); // Commit Transaction
        System.out.println("-----4------");
        entityManager.close();
        emf.close();
        System.out.println("-----5------");
    }
}
