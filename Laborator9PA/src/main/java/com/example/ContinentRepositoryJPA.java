package com.example;

import Entity.Continent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ContinentRepositoryJPA implements AbstractRepository<Continent> {
    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();

    public ContinentRepositoryJPA() {
    }

    @Override
    public void create(Continent continent) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(continent);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Continent findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Continent continent = entityManager.find(Continent.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return continent;
    }

    @Override
    public List<Continent> findByName(String name) {
        List<Continent> continentList;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        continentList = entityManager.createNamedQuery("findContinentByName", Continent.class).setParameter("name", name).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return continentList;
    }
}
