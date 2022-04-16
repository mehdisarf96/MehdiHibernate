package com.mehdisarf.driver;

import com.mehdisarf.domainmodel.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mehdiPersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin(); // Begin Transaction

        Person person = new Person("Mehdi", "Sarf","mehdi@gmail.com",new Date() ,
                LocalDate.of(2022,03,26));

        entityManager.persist(person);

        transaction.commit(); // Commit Transaction

        entityManager.close();
    }
}
