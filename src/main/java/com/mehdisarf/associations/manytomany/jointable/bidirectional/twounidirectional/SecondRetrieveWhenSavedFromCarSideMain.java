package com.mehdisarf.associations.manytomany.jointable.bidirectional.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SecondRetrieveWhenSavedFromCarSideMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRow();
        // I saved on Car side and Car has @ManyToMany,
        // SO it's gonna store the relationship on the "Car's Join Table".
        // (*** va na on the "Person's Join Table ***)
        Car theCar = entityManager.find(Car.class, 1L);
        List<Person> persons = theCar.getPersons();
        //be zibayii az Car be Person raftam.
        // mire az 'join table' esh, yani "Car's Join Table"
        // yani az Join Table ii ke dar asare @ManyToMany e darun e class e Car
        // ijad shode, negah mikone va persons esh ro migire.

        // vaqti mire person ro begire, nemishe ke ye tike az person ro biare
        // ye qesmat dgash ro nayare. so mire donbale association haye Person.
        // class e Person ye association dare be esme 'cars'. balash ba @ManyToMany
        // annotate shode. so vase meqdardehi kardane in association e Person, mire be
        // Join Table e ijad shobe tavasote hamin @ManyToMany e tuye class Person,
        // negah mikone. (unjam ke recordi store va zakhire nashode.).
        // midunim ke dar ezaye in insert hayii ke anjam
        // dadim(be vaseteye save kardan on Car side), record ii tuye
        // join table e ijad shode tavasote @ManyToMany e class e Person, store nashode.
        Person person = entityManager.find(Person.class, 1L);
        System.out.println(person.getCars()); // []
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Car car1 = new Car("pride", "white");
        Person person1 = new Person("mehdi", "sarf");
        Person person2 = new Person("reza", "zareii");
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
