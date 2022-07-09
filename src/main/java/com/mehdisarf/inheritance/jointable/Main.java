package com.mehdisarf.inheritance.jointable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");

    public static void main(String[] args) {
        insertMain();
        retrieveMain();
    }

    static void insertMain() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Checking checkingAccount = new Checking(100.0, 20.0);
        Savings savingAccount = new Savings(200.0, 50.0);

        entityManager.persist(checkingAccount);
        entityManager.persist(savingAccount);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    static void retrieveMain() {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Checking checkingAccount = entityManager.find(Checking.class, 1L);
        // ino ke mire tu table e 'checking' (which is child)
        // va bad az tariqe fk mire be table e 'account' (which is parent)
        // va tamame etelaate marbut be in instance ro migire.

        Account account1 = entityManager.find(Account.class, 1L);
        // in mire tu table e 'account' (which is parent). unja faqat
        // column haye account hast. alan az koja hibernate mifahme ke
        // in account ba id e 1, az subtype e 'checking' e ya az
        // subtype e 'savings'? az supertype_table be subtype_table ha
        // ham ke fk ii nist ke ba az tariqe un berim be table e subtype.
        // HB miad va az ye mechanism ii mese 'Shared Primary Key' e tuye
        // @OneToOne estefade mkikone. be in surat ke masalan mibine tuye
        // supertype_table record ii ba id 1 hast. baraye inke bbine az
        // che no' subtype iiye va etelaate specific va subtypeii e un
        // instance ro begire, mire tu table haye subtype ha migarde va
        // negah mikone bbine che satri che recordi, id esh 1 e.

        System.out.println(checkingAccount);
        System.out.println(account1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}