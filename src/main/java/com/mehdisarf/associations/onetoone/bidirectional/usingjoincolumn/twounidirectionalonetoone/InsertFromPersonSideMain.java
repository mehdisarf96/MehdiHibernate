package com.mehdisarf.associations.onetoone.bidirectional.usingjoincolumn.twounidirectionalonetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertFromPersonSideMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");

        // we are saving on Person side and Person has @OneToOne,
        // SO it's gonna store the relationship in the "Join Column" of Person table.
        // in the "Join Column" of its table.
        // Look:
        p1.setAddress(address1);

        entityManager.persist(p1);
        entityManager.persist(address1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}