package com.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static PersistenceUtil persistenceUtil;
    private static EntityManagerFactory entityManagerFactory;

    private PersistenceUtil() {
        createEntity();
    }

    public void createEntity() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Lab9PU");
    }

    public static EntityManagerFactory returnEntity() {
        return entityManagerFactory;
    }

    public static PersistenceUtil getInstance() {
        if (persistenceUtil == null) {
            persistenceUtil = new PersistenceUtil();
        }
        return persistenceUtil;
    }
}
