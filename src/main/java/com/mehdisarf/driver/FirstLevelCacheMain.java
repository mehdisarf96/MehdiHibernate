package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Book;
import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FirstLevelCacheMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    private void persist() { // hamin code ro ba generation strategy haye mokhtalef bbin. va farqeshun ro negah kon.

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        System.out.println("--1--");
        entityManager.persist(p1);
        System.out.println("--2--");
        entityManager.getTransaction().commit();
        System.out.println("--3--");

        entityManager.close();

        /*      output (vaqti IDENTIT e)
        --1--
            Hibernate: insert into Person (firstName, lastName) values (?, ?)
        --2--
        --3--
         */


        /*         output (vaqti SEQUENCE e)
        --1--
            Hibernate: select next_val as id_val from hibernate_sequence for update
            Hibernate: update hibernate_sequence set next_val= ? where next_val=?
        --2--
            Hibernate: insert into Person (firstName, lastName, id) values (?, ?, ?)
        --3--
         */
    }

    private void persistEntityWithAssignedPKByDeveloper() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111-222","The Fall");

        System.out.println("--1--");
        entityManager.persist(b1);
        System.out.println("--2--");
        entityManager.getTransaction().commit();
        System.out.println("--3--");

        entityManager.close();

        /*
        Output:
        --1--
        --2--
        Hibernate: insert into Book (title, isbn) values (?, ?)
        --3--
         */
    }

    public static void main(String[] args) {
        FirstLevelCacheMain f = new FirstLevelCacheMain();
        f.persistEntityWithAssignedPKByDeveloper();
    }
}