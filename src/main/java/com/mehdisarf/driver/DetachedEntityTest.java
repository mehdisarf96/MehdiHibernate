package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DetachedEntityTest {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public void makeDetachedManually(){

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi","Sarf");

        entityManager.persist(p1); // bordamesh be state e Managed/Persisted. va hamchenin ovordamesh tu Persistence Context.

        entityManager.detach(p1); // alan p1 az Persistence Context miad birun. va alan dar state e Detachd e.

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void makeDetachedByClosingEntityManager(){
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person("Mehdi","Sarf");

        entityManager.persist(p1); // bordamesh be state e Managed/Persisted. va hamchenin ovordamesh tu Persistence Context.

        entityManager.close(); // midunam vaqti ye Entity Manager closed mishe, every objects in its Persistence Context will be Detached.
        // so alan dar inja dar in khat, p1 dar state e Detached gharar dare.
        // alan dg p1 Detached shode. bedune estefade az detach().

        // alan bekhay balaii sare p1 (be onvane yek Detached entity) biary, chon un b`ala bayad tuye tx bashe, bayad ye tx ii
        // begin shode bashe. va in be vasileye entity manager bayad anjam she. so:

        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        //entityManager.XXX(p1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) {
        DetachedEntityTest det = new DetachedEntityTest();
        det.makeDetachedByClosingEntityManager();
    }
}
