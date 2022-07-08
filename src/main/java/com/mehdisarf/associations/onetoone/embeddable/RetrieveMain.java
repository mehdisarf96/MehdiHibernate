package com.mehdisarf.associations.onetoone.embeddable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RetrieveMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRow();

        Person person = entityManager.find(Person.class, 1L);
        System.out.println(person);

        Person person2 = entityManager.find(Person.class, 2L);
        System.out.println(person2);
        // entityManager.find(Address.class,?????); emishe.
        // entityManager.find(TheEmbeddable.class,?????); so kolan nemishe.
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");

        Person p2 = new Person("ahmad", "mahmood");
        Address address2 = new Address("Ha", "12345678");
        p2.setAddress(address2);

        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
