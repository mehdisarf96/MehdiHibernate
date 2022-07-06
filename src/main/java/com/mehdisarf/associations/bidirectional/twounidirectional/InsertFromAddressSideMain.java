package com.mehdisarf.associations.bidirectional.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertFromAddressSideMain {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");

        // we are saving on Address side and Address has @ManyToOne,
        // SO it's gonna store the relationship on the "Join Column (fk) of address table".
        // Look:
        address1.setPerson(person);
        address2.setPerson(person);

        entityManager.persist(person);
        entityManager.persist(address1);
        entityManager.persist(address2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
