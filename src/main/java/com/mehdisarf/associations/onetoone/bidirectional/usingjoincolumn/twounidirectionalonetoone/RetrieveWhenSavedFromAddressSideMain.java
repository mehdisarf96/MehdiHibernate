package com.mehdisarf.associations.onetoone.bidirectional.usingjoincolumn.twounidirectionalonetoone;

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
        // I saved on Address side and Address has @OneToOne,
        // SO it's gonna store the relationship on the "Join Column" of Address table.
        // (*** va na on the "Join Column" of Person table. ***)

        Address address = entityManager.find(Address.class, 1L);
        Person thePerson = address.getPerson();
        //be zibayii az Address be Person raftam.
        // mire az 'join column' esh(manzur: join column e Address table),
        // negah mikone va person esh ro migire.

        // vaqti mire person esh ro begire, nemishe ke ye tike az person ro biare
        // ye qesmat dgash ro nayare. masalan intor nist bere firstName o lastName e
        // person ro biare vali address esh ro nayare! so donbale association e person ham mire.
        // vase meqdardehi kardane association e person, mire Join Column(fk) e table e person ro
        // negah mikone. (unjam ke maqadire oon column NULL hastan).
        // midunim ke be vaseteye save kardan on Address side, maqadire sotune
        // Join Column(FK) e table e person, NULL e.
        System.out.println(thePerson.getAddress()); // null
        thePerson.getAddress().getStreet(); // // be dorosti NPE vasamun throw mishe.

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");

        address1.setPerson(p1);

        entityManager.persist(p1);
        entityManager.persist(address1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
