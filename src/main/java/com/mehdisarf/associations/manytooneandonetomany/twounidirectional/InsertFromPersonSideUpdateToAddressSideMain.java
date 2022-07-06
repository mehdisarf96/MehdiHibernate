package com.mehdisarf.associations.manytooneandonetomany.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertFromPersonSideUpdateToAddressSideMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        insertSomeRow();

        Address addressOne = entityManager.find(Address.class, 1L);
        Person personTwo = entityManager.find(Person.class, 2L);
        addressOne.setPerson(personTwo);

        entityManager.merge(addressOne);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person person1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");

        person1.addAddress(address1);
        person1.addAddress(address2);

        entityManager.persist(person1);
        entityManager.persist(address1);
        entityManager.persist(address2);

        Person person2 = new Person("ahmad","mahmoud");
        entityManager.persist(person2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
