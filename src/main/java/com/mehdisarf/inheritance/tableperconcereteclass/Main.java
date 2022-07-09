package com.mehdisarf.inheritance.tableperconcereteclass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        insertMain();
        retrieveMain();
    }

    static void insertMain() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Checking checkingAccount = new Checking(100.0, 20.0);
        Savings savingAccount = new Savings(200.0, 50.0);
        Account savingAccount2 = new Savings(450.0, 150.0);
        // Account account = new Account(600.0); // test e vaqti ke parent, concrete ast.

        entityManager.persist(checkingAccount);
        entityManager.persist(savingAccount);
        entityManager.persist(savingAccount2);
        // entityManager.persist(account); //test e vaqti ke parent, concrete ast.
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    static void retrieveMain() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Checking checkingAccount = entityManager.find(Checking.class, 1L);
        Account account = entityManager.find(Account.class, 1L);
        //Account pureAccount = entityManager.find(Account.class, 4L); //test e vaqti ke parent, concrete ast.

        System.out.println(checkingAccount);
        System.out.println(account);
        //System.out.println(pureAccount); test e vaqti ke parent, concrete ast.
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}