package com.example;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractRepository <T>{
    void create(T obj){

    }

    T findById(int id){

        T name = null;
        return name;
    }
    List<T> findByName(String name){

        List<T> listanume = null;
        return listanume;
    }


//
//public void create(T obj){
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    entityManager.getTransaction().begin();
//    entityManager.persist(obj);
//    entityManager.getTransaction().commit();
//    entityManager.close();
//}
//
}

