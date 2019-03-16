package com.gokhan.veteriner.persistence;

import com.gokhan.veteriner.domain.Animal;

import java.util.List;
import java.util.Set;

public interface AnimalMapper {

    List<Animal> findAll();
    void insert(Animal animal);
    void delete(Animal animal);
    void update(Animal animal);

}
