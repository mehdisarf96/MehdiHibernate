package com.mehdisarf.associations.onetoone.unidirectional.usingjoincolumn;

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
        System.out.println(person.getAddress());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        p1.setAddress(address1);

        entityManager.persist(p1);
        entityManager.persist(address1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
