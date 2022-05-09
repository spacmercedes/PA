package com.example;

import Entity.Continent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("Lab9PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Continent continent = new Continent("Europe");
        em.persist(continent);
        Continent c = (Continent)em.createQuery("select e from Continent e where e.name='Europe'").getSingleResult();
        c.setName("Africa");
        em.getTransaction().commit();
        em.close();
        emf.close();

        CityRepositoryJPA city = new CityRepositoryJPA();
//        city.createFakeCities();
        System.out.println(city.findByName("Norvos"));
    }
}

