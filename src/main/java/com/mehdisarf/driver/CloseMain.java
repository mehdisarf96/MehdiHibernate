package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CloseMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public void close() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");
        System.out.println("--1--");
        entityManager.persist(b1);

        System.out.println("--2--");

        entityManager.close();

        System.out.println("--3--");
    }

    public void closeAndException() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");
        System.out.println("--1--");
        entityManager.persist(b1);
        System.out.println("--2--");
        entityManager.getTransaction().commit();
        System.out.println("--3--");
        entityManager.close(); // the entityManager is closed now.
        System.out.println("--4--");

        entityManager.contains(b1); // using the closed entityManager.

        System.out.println("--5--");
    }

    public static void main(String[] args) {
        CloseMain c = new CloseMain();
        c.closeAndException();
    }
}
