package com.mehdisarf.associations.onetoone.bidirectional.sharedpk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RetrieveMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        //firstRetrieve();
        // secondRetrieve();
        thirdRetrieve();
    }

    static void firstRetrieve() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRowForFirstRetrieve();

        Person person = entityManager.find(Person.class, 1L);
        System.out.println(person.getAddress());
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void insertSomeRowForFirstRetrieve() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address(1L, "Pa", "258741369");
        p1.setAddress(address1);

        entityManager.persist(p1);
        entityManager.persist(address1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    static void secondRetrieve() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRowForSecondRetrieve();

        Person person = entityManager.find(Person.class, 1L);
        System.out.println(person.getAddress());

        entityManager.clear();

        Address address = entityManager.find(Address.class, 1L);
        System.out.println(address.getPerson());
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void insertSomeRowForSecondRetrieve() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address(1L, "Pa", "258741369");
        address1.setPerson(p1);

        entityManager.persist(p1);
        entityManager.persist(address1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    static void thirdRetrieve() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRowForThirdRetrieve();

        Person person = entityManager.find(Person.class, 1L);
        System.out.println(person.getAddress());

        entityManager.clear();

        Address address = entityManager.find(Address.class, 1L);
        System.out.println(address.getPerson());
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void insertSomeRowForThirdRetrieve() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address(1L, "Pa", "258741369");
        // address1.setPerson(p1);
        // or
        // p1.setAddress(address1);
        // No Establishing any relation and connection. bbinim kar mikone hamchonan?
        // BALE! Of course ke kar mikone. result:
        // Address{id=1, street='Pa', zipCode='258741369'}
        // Person{id=1, firstName='mehdi', lastName='sarf'}

        entityManager.persist(p1);
        entityManager.persist(address1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
