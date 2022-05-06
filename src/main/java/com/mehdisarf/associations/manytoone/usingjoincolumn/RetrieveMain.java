package com.mehdisarf.associations.manytoone.usingjoincolumn;

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

        Address theAddress = entityManager.find(Address.class, 1L);
        Person theAddressPerson = theAddress.getPerson(); // deqat kon ke mitunam az Address be Person beram vali
        // barAx na.

        System.out.println();
        System.out.println(theAddressPerson);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369", p1);
        entityManager.persist(p1);
        entityManager.persist(address1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
