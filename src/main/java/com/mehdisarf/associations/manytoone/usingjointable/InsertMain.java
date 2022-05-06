package com.mehdisarf.associations.manytoone.usingjointable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369", p1);
        Address address2 = new Address("Fa", "258741369", p1);
        Address address3 = new Address("Ta", "258741369", p1);

        Person p2 = new Person("ahmad", "mahmood");
        Address address4 = new Address("Ve", "128745623", p2);

        entityManager.persist(p1);
        entityManager.persist(address1);
        entityManager.persist(address2);
        entityManager.persist(address3);
        entityManager.persist(p2);
        entityManager.persist(address4);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
