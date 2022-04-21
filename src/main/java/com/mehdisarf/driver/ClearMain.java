package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClearMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public void clear() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");
        System.out.println("--1--");
        entityManager.persist(b1); // ruye b1, persist() ro call kardim so ba ejraye in, b1 mire tu Persistence Context (First-level Cache).
        System.out.println("--2--");
        System.out.println("--3--" + entityManager.contains(b1));

        entityManager.clear(); //All the Managed object inside the Persistence Context (First-level Cache) will be Detached.

        System.out.println("--4--" + entityManager.contains(b1));
        entityManager.getTransaction().commit();
        System.out.println("--5--");

        entityManager.close();

        /* Output:
        --1--
        --2--
        --3--true
        --4--false
        --5--
         */
    }

    public void clear2() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");
        System.out.println("--1--");
        entityManager.persist(b1); // ruye b1, persist() ro call kardim so ba ejraye in, b1 mire tu Persistence Context (First-level Cache).
        System.out.println("--2--");
        System.out.println("--3--" + entityManager.contains(b1));

        entityManager.flush(); // to make sure all the changes are pushed into DB.
        System.out.println("--4--");
        entityManager.clear(); //All the Managed object inside the Persistence Context (First-level Cache) will be Detached.

        System.out.println("--5--" + entityManager.contains(b1));
        entityManager.getTransaction().commit();
        System.out.println("--6--");

        entityManager.close();
    }

    public static void main(String[] args) {
        ClearMain c = new ClearMain();
        c.clear2();
    }
}
