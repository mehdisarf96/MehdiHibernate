package com.mehdisarf.inheritance.singletable.customizeddiscriminator;

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

        entityManager.persist(checkingAccount);
        entityManager.persist(savingAccount);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    static void retrieveMain() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Account account1 = entityManager.find(Account.class, 1L);
        Account account2 = entityManager.find(Account.class, 2L);

        System.out.println(account1);
        System.out.println(account2);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}