package com.example;

import Entity.City;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CityRepositoryJPA implements AbstractRepository<City>  {
    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();
    private static Faker faker = new Faker();

    public CityRepositoryJPA() {
    }

    public static String getFakeName() { //retur un nume fake
        String Name = faker.harryPotter().location();
        return Name;
    }

    @Override
    public void create(City city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public City findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return city;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cityList;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        cityList = entityManager.createNamedQuery("findCityByName", City.class).setParameter("name", name).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return cityList;
    }

    public void createFakeCities(){ //folosesc faker pentru a crea 50 de orase fake
        int count = 0;

        while(count<50)
        {
            City city = new City(getFakeName());

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(city);
            entityManager.getTransaction().commit();
            entityManager.close();

            count++;
        }
    }


}
