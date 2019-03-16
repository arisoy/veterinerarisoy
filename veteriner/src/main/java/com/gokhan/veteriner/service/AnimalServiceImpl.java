package com.gokhan.veteriner.service;

import com.gokhan.veteriner.domain.Animal;
import com.gokhan.veteriner.persistence.AnimalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AnimalServiceImpl implements AnimalService{
    @Autowired
    private AnimalMapper mapper;

    @Override
    public List<Animal> findAll() {
        return mapper.findAll();
    }

    @Override
    public void insert(Animal animal) {
        mapper.insert(animal);
    }

    @Override
    public void delete(Animal animal) {
        mapper.delete(animal);
    }

    @Override
    public void update(Animal animal) {
        mapper.update(animal);
    }
}
