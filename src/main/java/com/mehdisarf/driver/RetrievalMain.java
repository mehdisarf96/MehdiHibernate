package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Book;
import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RetrievalMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public void retrieval() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");

        System.out.println("--1--");
        entityManager.persist(b1);
        System.out.println("--2--");

        Book theB1 = entityManager.find(Book.class, "111");
        System.out.println("--3--" + theB1.getTitle());

        entityManager.getTransaction().commit();
        System.out.println("--4--");

        entityManager.close();

        /*
        --1--
        --2--
        --3--The Fall
        Hibernate: insert into Book (title, isbn) values (?, ?)
        --4--
         */
    }

    public void retrieval2() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        System.out.println("--1--");
        entityManager.persist(p1);
        System.out.println("--2--");

        Person theP1 = entityManager.find(Person.class, 1L);
        System.out.println("--3--" + theP1.getFirstName());

        entityManager.getTransaction().commit();
        System.out.println("--4--");

        entityManager.close();

        /*
            --1--
            Hibernate: insert into Person (firstName, lastName) values (?, ?)
            --2--
            --3--Mehdi
            --4--
         */
    }

    public static void main(String[] args) {
        RetrievalMain r = new RetrievalMain();
        r.retrieval2();
    }
}
