package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PushChangesToDBMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public void pushChangesBeforeCommit() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");
        System.out.println("--1--");

        entityManager.persist(b1); // this change will be held in Persistence Context (Cache). so you won't see any statement here.

        System.out.println("--2--");
        entityManager.getTransaction().commit();
        System.out.println("--3--");

        entityManager.close();
    }

    public void pushChangesBeforeQuery() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");
        System.out.println("--1--");
        entityManager.persist(b1); // this change will be held in Persistence Context (Cache). so you won't see any statement here.
        System.out.println("--2--");

        TypedQuery<Book> query = entityManager.createQuery("from Book", Book.class);
        query.getResultList();

        System.out.println("--3--");
        entityManager.getTransaction().commit();
        System.out.println("--4--");

        entityManager.close();

        /*  Output:
            --1--
            --2--
            Hibernate: insert into Book (title, isbn) values (?, ?)
            Hibernate: select book0_.isbn as isbn1_0_, book0_.title as title2_0_ from Book book0_
            --3--
            --4--

            beyne 1 o 2, insert will be held in Pers Cntxt so no statement.
            ama beyne 2 o 3 che etefaqi dare miofte?

            beyne 2 o 3, entityManager.createQuery() darim and we want to make a query. ok.
            ama age tu output nega koni, before making our query, INSERT e marbut be persist() etefaq oftade: insert into Book (title, isbn) values (?, ?)
            chera?
            ****Because when we make a query, We Have Ho Get The Latest Version of the data from Database.
            and Hibernate will make sure ALL the changes we updated in the Persistence Cntxt (Cache) will be pushed to database.
            so before make a query, it will push all the changes to Database.****

            hala beyne 3 o 4, azunjaii ke hamechi is already in DB, then dg hich statementi generate va run nashode.
         */
    }

    public void pushChangesBeforeQuery2() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");

        System.out.println("--1--");
        entityManager.persist(b1); // this change will be held in Persistence Context (Cache). so you won't see any statement here.
        System.out.println("--2--");
        b1.setTitle("The Plague"); // this change will be held in Persistence Context (Cache). so you won't see any statement here.

        System.out.println("--3--");
        TypedQuery<Book> query = entityManager.createQuery("from Book", Book.class);
        query.getResultList();
        System.out.println("--4--");
        entityManager.getTransaction().commit();
        System.out.println("--5--");

        entityManager.close();

        /*  Output:
        --1--
        --2--
        --3--
        Hibernate: insert into Book (title, isbn) values (?, ?)
        Hibernate: update Book set title=? where isbn=?
        Hibernate: select book0_.isbn as isbn1_0_, book0_.title as title2_0_ from Book book0_
        --4--
        --5--

        beyne 1 o 2, insert darim. will be held in Prst Cntx (Cache). so there is no statement.
        beyne 2 o 3, update darim. will be held in Prst Cntx (Cache). so there is no statement.
        beyne 3 o 4, BEFORE we make a query, ALL the changes need to be pushed to Database. so first
        we have an INSERT and then second we have an UPDATE.
         */
    }

    public void pushChangesBeforeFlush() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Book b1 = new Book("111", "The Fall");

        System.out.println("--1--");
        entityManager.persist(b1); // this change will be held in Persistence Context (Cache). so you won't see any statement here.
        System.out.println("--2--");
        b1.setTitle("The Plague"); // this change will be held in Persistence Context (Cache). so you won't see any statement here.
        System.out.println("--3--");

        entityManager.flush();

        System.out.println("--4--");
        entityManager.getTransaction().commit();
        System.out.println("--5--");

        entityManager.close();

        /*  Output:
            --1--
            --2--
            --3--
            Hibernate: insert into Book (title, isbn) values (?, ?)
            Hibernate: update Book set title=? where isbn=?
            --4--
            --5--
         */
    }

    public static void main(String[] args) {
        PushChangesToDBMain psh = new PushChangesToDBMain();
        psh.pushChangesBeforeFlush();
    }
}
