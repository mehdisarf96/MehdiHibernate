package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Book;
import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoveTestMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 1. remove Transient object - assigned primary key.
        Book b1 = new Book("111-222", "The Fall");
        em.remove(b1); // remove e Transient entity ii ke developer dasti primary key un ro assign karde --> generate SELECT statement

        // 2. remove Transient object - auto-generated primary key.
        Person p1 = new Person("Mehdi","Sarf");
        em.remove(p1); // remove e Transient entity ii ke primary key esh, auto-generate e -->  SELECT statementii generate nemishe.

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
