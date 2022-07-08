package com.mehdisarf.associations.manytomany.jointable.unidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RetrieveMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        insertSomeRow();

        Person person1 = entityManager.find(Person.class, 1L);
        System.out.println(person1);
        Person person2 = entityManager.find(Person.class, 2L);
        System.out.println(person2);

        Car car1 = entityManager.find(Car.class, 1L);
        System.out.println(car1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person p1 = new Person("mehdi", "sarf");
        Car car1 = new Car("Pride", "White");
        Car car2 = new Car("Peugeot405", "Silver");
        p1.addCar(car1);
        p1.addCar(car2);

        Person p2 = new Person("darren", "shan");
        p2.addCar(car2); // car2 has two owners! :D

        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
