package com.example;

import Entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CountryRepositoryJPA implements AbstractRepository<Country> {
    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();

    public CountryRepositoryJPA() {
    }

    @Override
    public void create(Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Country findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country country = entityManager.find(Country.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return country;
    }

    @Override
    public List findByName(String name) {
        List<Country> countryList;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        countryList = entityManager.createNamedQuery("findCityByName", Country.class).setParameter("name", name).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return countryList;
    }
}
