package com.mehdisarf.associations.manytomany.jointable.bidirectional.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertFromPersonSideMain {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person person1 = new Person("mehdi","sarf");
        Car car1 = new Car("pride","white");
        Car car2 = new Car("peugeot","silver");
        // we are saving on Person side and Person has @ManyToMany,
        // SO it's gonna store the relationship on the "Person's Join Table!".
        // Look:
        person1.addCar(car1);
        person1.addCar(car2);

        entityManager.persist(person1);
        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.getTransaction().commit();
        entityManager.close();

//        entityManager = emf.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        Car theCar1 = entityManager.find(Car.class, 1L);
//        System.out.println(theCar1);
//        theCar1.getPersons().get(1).getFirstName();
//
//        entityManager.getTransaction().commit();
//        entityManager.close();

        /*
        Car car3 = new Car("peykan","yakhchali");
        Person person2 = new Person("ahmad","mahmood");
        car3.addOwner(person2);
        entityManager.persist(person2);
        entityManager.persist(car3);
         */

    }
}
