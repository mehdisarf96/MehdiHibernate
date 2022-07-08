package com.mehdisarf.associations.manytomany.jointable.bidirectional.onebidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Car car1 = new Car("Pride","White");
        Person p1 = new Person("mehdi", "sarf");
        Person p2 = new Person("darren", "shan");
        // we are establishing relations on Car side. look:
        car1.addOwner(p1);
        car1.addOwner(p2);
        // ama injur fayde nadare va relationship tuye db barqarar nemishe
        // va be ezaye inha, recordi be join table e 'person_car' (ke nash'at gerefte az
        // @ManyToMany e class e Person (as owning side) hast) ezafe va store nemishe.
        // chera? chon to alan on Car side umadi karet ro anjam dadi
        // va ertebat ro establish kardi. khob. tuye Car, association ba chi
        // annotate shode? ba @ManyToMany. ama @ManyToMany ba 'mappedBy',
        // umade give up karde manage kardan e relationship ro.
        // alan inja owning side, Person e. va faqat ham mishe ertebatat ro
        // az samte un establish koni. bayad ertebatat ro az samte un establish koni.
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(car1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
