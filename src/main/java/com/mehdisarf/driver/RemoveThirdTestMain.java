package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoveThirdTestMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        RemoveThirdTestMain remThird = new RemoveThirdTestMain();
        remThird.removeManagedObjectTrack2();
    }

    /*
        **  yadavari:
        getReference() wont make a query or hit to the DB
        unless u really use this object. what does use mean?
        maslan man az book, title esh ro mikham.
        mikham title ro retrieve konam. va tuye proxy, title nadarim.
        it doesn't have title. so it must to make a call to database.
     */

    private void removeManagedObject1() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin(); // begin tx.

        Person p1 = new Person("Mehdi", "Sarf");

        entityManager.persist(p1); // will cause a INSERT.

        entityManager.getTransaction().commit(); // commit tx.
        entityManager.close();

        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin(); // begin tx.

        // first u have to get the object. 2 rah dari: find() ya getReference()
        // vaqti az find() estefade mikoni, didim ke 2ta hit be db mizane
        // va vaqti az getReference() estefade mikoni, 1hit be db mizane.
        Person theP1 = entityManager.find(Person.class, 1L); // will cause a SELECT.
        // now theP1 is in Managed/Persisted state.

        entityManager.remove(theP1); // will cause a DELETE.

        entityManager.getTransaction().commit();
        entityManager.close();

        // output:

        /*
        Hibernate: insert into Person (firstName, lastName) values (?, ?)
        Hibernate: select person0_.id as id1_2_0_, person0_.firstName as firstnam2_2_0_, person0_.lastName as lastname3_2_0_ from Person person0_ where person0_.id=?
        Hibernate: delete from Person where id=?
         */
    }

    private void removeManagedObjectTrack1() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        entityManager.persist(p1);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("----1----");
        Person theP1 = entityManager.find(Person.class, 1L);
        System.out.println("----2----");
        entityManager.remove(theP1);
        System.out.println("----3----");
        entityManager.getTransaction().commit();
        System.out.println("----4----");
        entityManager.close();

        /*         Output:

        Hibernate: insert into Person (firstName, lastName) values (?, ?)
        ----1----
        Hibernate: select person0_.id as id1_2_0_, person0_.firstName as firstnam2_2_0_, person0_.lastName as lastname3_2_0_ from Person person0_ where person0_.id=?
        ----2----
        ----3----
        Hibernate: delete from Person where id=?
        ----4----

        beyne line 1 o 2, find() causes a INSERT statement.
        beyne line 2 o 3, etefaqi nemiofte.
        beyne line 3 o 4, vaqti ke be commit miresim, DELETE statement generate va etefaq miofte.
         */
    }

    private void removeManagedObject2() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin(); // begin tx.

        Person p1 = new Person("Mehdi", "Sarf");

        entityManager.persist(p1); // will cause a INSERT.

        entityManager.getTransaction().commit(); // commit tx.
        entityManager.close();

        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin(); // begin tx.

        // inbar az getReference() estefade.
        Person theP1 = entityManager.getReference(Person.class, 1L); // entezar darim ke will not cause a SELECT anymore. chon this is getReference()

        entityManager.remove(theP1); // will cause a DELETE.

        entityManager.getTransaction().commit();
        entityManager.close();

        // output:
        /*

         */
    }

    private void removeManagedObjectTrack2() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi", "Sarf");

        entityManager.persist(p1);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("----1----");
        Person theP1 = entityManager.getReference(Person.class, 1L);
        System.out.println("----2----");
        entityManager.remove(theP1);
        System.out.println("----3----");
        entityManager.getTransaction().commit();
        System.out.println("----4----");
        entityManager.close();

        /*         Output:

        Hibernate: insert into Person (firstName, lastName) values (?, ?)
        ----1----
        ----2----
        Hibernate: select person0_.id as id1_2_0_, person0_.firstName as firstnam2_2_0_, person0_.lastName as lastname3_2_0_ from Person person0_ where person0_.id=?
        ----3----
        Hibernate: delete from Person where id=?
        ----4----

        beyne line 1 o 2:
        hichi nist. pas getReference() didn't make any call or hit to db. so hamuntor ke entezar dashtim,
        getReference() didn't issue any call to database.
        addressi ke 'theP1' tu khodesh alan negah dashte, address e un proxy e ast. hamun proxy ii ke hb baramun provide karde.

        beyne line 2 o 3:
        inja vaqti remove() ejra mishe, ye SELECT statement generate mishe. pas in SELECT ii
        ke ma mibinim, male getReference() nist va male ejraye remove() bude.

        beyne line 3 o 4:
        inja DELETE statement darim.

        pas vaqti az getReference() va remove() ba ham estefade mikonim,
        hamchonan 2ta hit va call be database darim; ama injur:
        0 call for getReference() and 2 call for remove() (SELECT and DELETE)
         */
    }
}
