package com.mehdisarf.associations.onetoone.bidirectional.usingjoincolumn.twounidirectionalonetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SecondRetrieveWhenSavedFromPersonSideMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRow();
        // I saved on Person side and Person has @OneToOne,
        // SO it's gonna store the relationship on the "Join Column" of Person table.
        // (*** va na on the "Join Column" of Address table. ***)

        Person person = entityManager.find(Person.class, 1L);
        Address theAddress = person.getAddress();
        //be zibayii az Person be Address raftam.
        // mire az 'join column' esh(manzur: join column e Person table),
        // negah mikone va address esh ro migire.

        // vaqti mire address esh ro begire, nemishe ke ye tike az address ro biare
        // ye qesmat dgash ro nayare. so donbale association e address ham mire.
        // vase meqdardehi kardane association e address, mire Join Column(fk) e table e address ro
        // negah mikone. (unjam ke maqadire oon column NULL hastan).
        // midunim ke be vaseteye save kardan on Person side, maqadire sotune FK e table e address, NULL e.
        Address address = entityManager.find(Address.class, 1L);
        System.out.println(address.getPerson()); // null
        address.getPerson().getFirstName(); // be dorosti NPE vasamun throw mishe.

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        p1.setAddress(address1);

        entityManager.persist(p1);
        entityManager.persist(address1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
