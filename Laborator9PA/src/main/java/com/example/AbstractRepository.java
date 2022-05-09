package com.example;

import java.util.List;

public interface AbstractRepository <T>{
    void create(T obj);
    T findById(int id);
    List<T> findByName(String name);
}