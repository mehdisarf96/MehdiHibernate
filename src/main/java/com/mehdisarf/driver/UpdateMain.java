package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Book;
import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdateMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public void update() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        System.out.println("--1--");
        entityManager.persist(p1);
        System.out.println("--2--");

        p1.setFirstName("Raul"); // changes the Managed/Persisted object.
        System.out.println("--3--");
        entityManager.getTransaction().commit();
        System.out.println("--4--");

        entityManager.close();

        /*
            --1--
            Hibernate: insert into Person (firstName, lastName) values (?, ?)
            --2--
            --3--
            Hibernate: update Person set firstName=?, lastName=? where id=?
            --4--
         */
    }

    public void serveralUpdates() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        System.out.println("--1--");
        entityManager.persist(p1);
        System.out.println("--2--");

        p1.setFirstName("Raul"); // changes the Managed/Persisted object. oop state of the object is only changed in Persistence Context (Cache).
        System.out.println("--3--");
        p1.setLastName("Gonzalez"); // changes the Managed/Persisted object. oop state of the object is only changed in Persistence Context (Cache).
        System.out.println("--4--");
        entityManager.getTransaction().commit();
        System.out.println("--5--");

        entityManager.close();

        /*
            --1--
            Hibernate: insert into Person (firstName, lastName) values (?, ?)
            --2--
            --3--
            --4--
            Hibernate: update Person set firstName=?, lastName=? where id=?
            --5--
         */
    }

    public void updateEntityWithAssignedPKByDeveloper() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");

        System.out.println("--1--");
        entityManager.persist(b1);
        System.out.println("--2--");

        b1.setTitle("the Plague"); // changes the Managed/Persisted object.
        System.out.println("--3--");
        entityManager.getTransaction().commit();
        System.out.println("--4--");

        entityManager.close();
        /*  output
            --1--
            --2--
            --3--
            Hibernate: insert into Book (title, isbn) values (?, ?)
            Hibernate: update Book set title=? where isbn=?
            --4--
         */
    }

    public static void main(String[] args) {
        UpdateMain um = new UpdateMain();
        um.updateEntityWithAssignedPKByDeveloper();
    }
}
