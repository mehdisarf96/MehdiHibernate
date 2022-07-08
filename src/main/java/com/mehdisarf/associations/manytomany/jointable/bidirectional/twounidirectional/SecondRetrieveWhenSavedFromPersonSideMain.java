package com.mehdisarf.associations.manytomany.jointable.bidirectional.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SecondRetrieveWhenSavedFromPersonSideMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        insertSomeRow();
        // I saved on Person side and Person has @ManyToMany,
        // SO it's gonna store the relationship on the "Person's Join Table".
        // (*** va na on the "Car's Join Table ***)
        Person thePerson = entityManager.find(Person.class, 1L);
        List<Car> carsOfThePerson = thePerson.getCars();
        //be zibayii az Person be Car raftam.
        // mire az 'join table' esh, yani "Person's Join Table"
        // yani az Join Table ii ke dar asare @ManyToMany e darun e class e Person
        // ijad shode, negah mikone va car hash ro migire.

        // vaqti mire car ro begire, nemishe ke ye tike az car ro biare
        // ye qesmat dgash ro nayare. so mire donbale association haye Car.
        // class e Car ye association dare be esme 'persons'. balash ba @ManyToMany
        // annotate shode. so vase meqdardehi kardane in association e Car, mire be
        // Join Table ijad shobe tavasote hamin @ManyToMany e tuye class Car
        // negah mikone. (unjam ke recordi store va zakhire nashode.).
        // midunim ke dar ezaye in insert hayii ke anjam
        // dadim(be vaseteye save kardan on Person side), record ii tuye
        // join table e ijad shode tavasote @ManyToMany e class e Car, store nashode.
        Car car1 = entityManager.find(Car.class, 1L);
        System.out.println(car1.getPersons()); // []
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person person1 = new Person("mehdi", "sarf");
        Car car1 = new Car("pride", "white");
        Car car2 = new Car("peugeot", "silver");
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
    }
}
