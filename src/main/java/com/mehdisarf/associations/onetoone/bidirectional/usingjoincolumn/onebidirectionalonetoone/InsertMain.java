package com.mehdisarf.associations.onetoone.bidirectional.usingjoincolumn.onebidirectionalonetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");

        // we are saving on Address side and Address has @OneToOne,
        address1.setPerson(person);
        address2.setPerson(person);
        // ama injur fayde nadare va relationship tuye db barqarar nemishe
        // va maqadir e sotun e address_id dar table e 'person' null khahand
        // mund. chera? chon to alan on Address side umadi karet ro anjam dadi
        // va ertebat ro establish kardi. khob. tuye Address, association ba chi
        // annotate shode? ba @OneToOne. ama @OneToOne ba 'mappedBy'
        // umade give up karde va dg Join Column ii dorost nemikone.
        // alan inja owning side, Person e. va faqat ham mishe ertebatat ro
        // az samte un establish koni. bayad ertebatat ro az samte un establish
        // koni.

        entityManager.persist(address1);
        entityManager.persist(person);
        entityManager.persist(address2);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
