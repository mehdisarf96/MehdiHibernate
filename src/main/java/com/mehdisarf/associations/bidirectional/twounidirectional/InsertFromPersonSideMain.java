package com.mehdisarf.associations.bidirectional.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertFromPersonSideMain {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");

        // we are saving on Person side and Person has @OneToMany,
        // SO it's gonna store the relationship on the "Join Table".
        // Look:
        person.addAddress(address1);
        person.addAddress(address2);

        entityManager.persist(address1);
        entityManager.persist(person);
        entityManager.persist(address2);

        /*
        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");
        person.addAddress(address1);
        person.addAddress(address2);

        entityManager.persist(address1);
        entityManager.persist(address2);
        entityManager.persist(person);
        // aslan tartib e persist kardan ahamiati nadare.
        // hamchonan We'have saved on Person side; bekhatere inke az samte person
        // umadim ertebat ha ro bargharar kardim o beham associate eshun kardim:
        // person.addAddress(address1);
        // person.addAddress(address2);
        // inke begim aval person ro persist kardim, pas I saved on Person side,
        // in kamelan qalate. aval inke ham Person va ham Address ha ro darim persist mikonim.
        // pas hardo entity daran persist mishan. zemnan zoodtar persist shodan ham
        // baes nemishe ke begim ruye side oon entity ii ke zudtar persist kardim, save anjam dadim.
         */

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
