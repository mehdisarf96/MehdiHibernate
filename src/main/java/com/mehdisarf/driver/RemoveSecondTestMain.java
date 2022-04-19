package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoveSecondTestMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    private void removeDetachedObject(){

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi","Sarf");

        entityManager.persist(p1); // bordamesh be state e Managed/Persisted. va hamchenin ovordamesh tu Persistence Context.

        entityManager.detach(p1); // alan p1 az Persistence Context miad birun. va alan dar state e Detachd e.

        // now:
        entityManager.remove(p1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) {
        RemoveSecondTestMain re = new RemoveSecondTestMain();
        re.removeDetachedObject();
    }
}
