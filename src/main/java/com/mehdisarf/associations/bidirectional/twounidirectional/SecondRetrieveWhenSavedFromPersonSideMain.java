package com.mehdisarf.associations.bidirectional.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SecondRetrieveWhenSavedFromPersonSideMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRow();

        // I saved on Person side and Person has @OneToMany,
        // SO it's gonna store the relationship on the "Join Table".
        // (*** va na bevasileye sotune Foreign Key e table e address ***)
        Person thePerson = entityManager.find(Person.class, 1L);

        List<Address> addressesOfThePerson = thePerson.getAddresses();
        //be zibayii az Person be Address raftam.
        // mire az 'join table' esh negah mikone va address hash ro migire.

        // vaqti mire address hash ro begire, nemishe ke ye tike az address ro biare
        // ye qesmat dgash ro nayare. so mire donbale association haye address.
        // vase meqdardehi kardane association haye address, mire Join Column(fk) e table e address ro
        // negah mikone. (unjam ke maqadire oon column NULL hastan).
        // midunim ke be vaseteye save kardan on Person side, maqadire sotune FK e table e address, NULL e.
        Address anAddress = entityManager.find(Address.class, 1L);
        System.out.println(anAddress.getPerson()); // null
        anAddress.getPerson().getFirstName(); // be dorosti NPE vasamun throw mishe.

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");
        person.addAddress(address1);
        person.addAddress(address2);

        entityManager.persist(person);
        entityManager.persist(address1);
        entityManager.persist(address2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
