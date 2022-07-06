package com.mehdisarf.associations.manytooneandonetomany.onebidirectional;

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
        Address address2 = new Address("Fa", "258741369");

        // we are saving on Address side:
        address1.setPerson(person);
        address2.setPerson(person);
        // alan injur dg fayde dare va relationship tuye db barqarar mishe
        // va maqadir e sotun e person_id dar table e 'address' null nakhahand mund.
        // chon alan on Address side (owning side) umadi karet ro anjam dadi
        // va ertebat ro establish kardi.

        entityManager.persist(address1);
        entityManager.persist(person);
        entityManager.persist(address2);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
