package com.mehdisarf.associations.manytooneandonetomany.onebidirectional.usingjointable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RetrieveMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        // yadavari: ruye mode drop-and-create gozashtamesh. so majburam ye seri row insert konam.
        insertSomeRow();

        Address address = entityManager.find(Address.class, 1L);
        System.out.println(address.getPerson());

        entityManager.getTransaction().commit();
        entityManager.clear();

        Person person = entityManager.find(Person.class, 1L);
        // mire person ro biare, mirese be association hash. mirese be association e addresses.
        // baraye inke uno biare. nega mikone mibine ke balash neveshte @OneToMany. ama qabl az
        // inke bere soraqe ye Join Table ii, mibine ke E! neveshte 'mappedBy = person'.
        // mifahme ke in entity, manage kardan e relationship ro give up karde va in association e
        // addresses ham touye owning side (Address) va tavasote field e person dar unja manage shode.
        // pa mishe mire tu owning side (Address) va mibine ke balaye field e person neveshte @ManyToOne
        // so mire soraqe Join (FK) column e tuye table e address. unja ro negah mikone va mibine ke
        // che address hayii motealeq be in person e ma hastand.
        System.out.println(person.getAddresses().get(0));

        entityManager.close();
    }

    public static void insertSomeRow() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Person person = new Person("mehdi", "sarf");
        Address address1 = new Address("Pa", "258741369");
        Address address2 = new Address("Fa", "258741369");

        // we are saving on Address side:
        address1.setPerson(person);
        address2.setPerson(person);

        entityManager.persist(address1);
        entityManager.persist(person);
        entityManager.persist(address2);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
