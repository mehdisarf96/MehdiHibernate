package com.mehdisarf.associations.manytomany.jointable.bidirectional.onebidirectional;

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
        System.out.println(person1.getCars());

        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();

        Car car1 = entityManager.find(Car.class, 1L);
        // mire car ro biare, mirese be association hash. mirese be association e persons.
        // baraye inke uno biare. nega mikone mibine ke balash neveshte @ManyToMany. ama qabl az
        // inke bere soraqe ye Join Table ii, mibine ke E! neveshte 'mappedBy = cars'.
        // mifahme ke in entity (Car), manage kardan e relationship ro give up karde va in association e
        // persons ham touye owning side (Person) va tavasote field e cars dar unja, manage shode.
        // pa mishe mire tu owning side (Person) va mibine ke balaye field e cars neveshte @ManyToMany
        // so mire soraqe Join Table ii ke in @ManyToMany sakhte. unja ro negah mikone va mibine ke
        // che person hayii motealeq be in car e ma hastand.
        System.out.println(car1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Car car1 = new Car("Pride","White");
        Person p1 = new Person("mehdi", "sarf");
        Person p2 = new Person("darren", "shan");

        // we are establishing relations on Person side. look:
        p1.addCar(car1);
        p2.addCar(car1);

        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(car1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
