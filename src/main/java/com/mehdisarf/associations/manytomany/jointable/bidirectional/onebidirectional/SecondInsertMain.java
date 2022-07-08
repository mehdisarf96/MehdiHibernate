package com.mehdisarf.associations.manytomany.jointable.bidirectional.onebidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SecondInsertMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Car car1 = new Car("Pride","White");
        Person p1 = new Person("mehdi", "sarf");
        Person p2 = new Person("darren", "shan");

        // // we are establishing relations on Car side. look:
        //car1.addOwner(p1);
        //car1.addOwner(p2);

        // we are establishing relations on Person side. look:
        p1.addCar(car1);
        p2.addCar(car1);
        // alan injur dg fayde dare va relationship tuye db barqarar mishe
        // va be ezaye in relationship ha, tuye join table e 'person_car' (ke nash'at gerefte az
        // @ManyToMany e class e Person (as owning side) hast) record e jadid
        // zakhire va store mishe.
        // chon alan on Person side (owning side) umadi karet ro anjam dadi
        // va ertebat ro establish kardi.

        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(car1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
