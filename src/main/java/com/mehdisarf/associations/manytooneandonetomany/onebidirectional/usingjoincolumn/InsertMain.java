package com.mehdisarf.associations.manytooneandonetomany.onebidirectional.usingjoincolumn;

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

        // we are saving on Person side and Person has @OneToMany,
        person.addAddress(address1);
        person.addAddress(address2);
        // ama injur fayde nadare va relationship tuye db barqarar nemishe
        // va maqadir e sotun e person_id dar table e 'address' null khahand
        // mund. chera? chon to alan on Person side umadi karet ro anjam dadi
        // va ertebat ro establish kardi. khob. tuye Person, association ba chi
        // // annotate shode? ba @OneToMany. ama @OneToMany ba 'mappedBy'
        // umade give up karde va dg Join Table ii dorost nemikone.
        // alan inja owning side, Address e. va faqat ham mishe ertebatat ro
        // az samte un establish koni. bayad ertebatat ro az samte un establish
        // koni.

        entityManager.persist(address1);
        entityManager.persist(person);
        entityManager.persist(address2);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
