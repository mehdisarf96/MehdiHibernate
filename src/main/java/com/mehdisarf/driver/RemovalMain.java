package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemovalMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public void remove() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        System.out.println("--1--");
        entityManager.persist(p1);
        System.out.println("--2--");

        entityManager.remove(p1); // inja HB will not make a query to DB. this removal is gonna be held in the Persistence Context (Cache).
        // and only when we commit, it's gonna make a DELETE.

        System.out.println("--3--");
        entityManager.getTransaction().commit();
        System.out.println("--4--");
        entityManager.close();
    }

    public void remove2() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        System.out.println("--1--");
        entityManager.persist(p1);
        System.out.println("--2--");

        p1.setFirstName("Emad");

        System.out.println("--3--");
        entityManager.remove(p1);
        System.out.println("--4--");
        entityManager.getTransaction().commit();
        System.out.println("--5--");

        entityManager.close();
    }

    public void remove3() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        System.out.println("--1--");
        entityManager.persist(p1);
        System.out.println("--2--");
        p1.setFirstName("Emad");
        System.out.println("--3--");
        entityManager.remove(p1);
        System.out.println("--4--");

        boolean existing = entityManager.contains(p1);

        System.out.println("--5-- " + existing);
        entityManager.getTransaction().commit();
        System.out.println("--6--");
        entityManager.close();
    }

    public static void main(String[] args) {
        RemovalMain r = new RemovalMain();
        r.remove3();
    }
}
