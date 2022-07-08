package com.mehdisarf.associations.onetoone.embeddable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");

        Person p2 = new Person("ahmad", "mahmood");
        Address address2 = new Address("Ha", "12345678");
        p2.setAddress(address2);

        entityManager.persist(p1);
        // entityManager.persist(address1); // nemituni @Embeddable ro persist koni.
        entityManager.persist(p2);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}