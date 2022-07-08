package com.mehdisarf.associations.manytomany.jointable.bidirectional.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertFromCarSideMain {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Car car1 = new Car("pride","white");
        Person person1 = new Person("mehdi","sarf");
        Person person2 = new Person("reza","zareii");
        // we are saving on Car side and Car has @ManyToMany,
        // SO it's gonna store the relationship on the "Car's Join Table!".
        // Look:
        car1.addOwner(person1);
        car1.addOwner(person2);

        entityManager.persist(car1);
        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
