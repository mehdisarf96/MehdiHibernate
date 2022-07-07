package com.mehdisarf.associations.onetoone.bidirectional.usingjoincolumn.onebidirectionalonetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SecondInsertMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");

        // we are saving on Person side and Person has @OneToOne,
        person.setAddress(address1);
        // alan injur dg fayde dare va relationship tuye db barqarar mishe
        // va maqadir e sotun e address_id dar table e 'person' null nakhahand mund.
        // chon alan on Person side (owning side) umadi karet ro anjam dadi
        // va ertebat ro establish kardi.

        entityManager.persist(address1);
        entityManager.persist(person);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
