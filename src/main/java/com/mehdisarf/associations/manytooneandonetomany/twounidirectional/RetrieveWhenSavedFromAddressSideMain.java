package com.mehdisarf.associations.manytooneandonetomany.twounidirectional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RetrieveWhenSavedFromAddressSideMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRow();

        // I saved on Address side and Address has @ManyToOne,
        // SO it's gonna store the relationship on the "Join Column (fk) of address table".
        // (*** va na bevasileye Join Table nash'at gerefte az @OneToMany e Person ***)
        Address anAddress = entityManager.find(Address.class, 1L);

        Person thePerson = anAddress.getPerson();
        //be zibayii az Address be Person raftam.

        // vaqti mire person esh ro begire, nemishe ke firstname o lastname sh ro biare
        // ama association esh ro nayare. so mire donbale association haye person.
        // vase meqdardehi kardane association haye person, mire tuye join table ro migarde
        // va negah mikone. midunim ke dar ezaye in insert hayii ke anjam
        // dadim(be vaseteye save kardan on Address side), record ii tuye join table store nashode.
        System.out.println(thePerson);

        System.out.println(thePerson.getAddresses()); // []. empty.

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");

        address1.setPerson(person);
        address2.setPerson(person);

        entityManager.persist(person);
        entityManager.persist(address1);
        entityManager.persist(address2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
