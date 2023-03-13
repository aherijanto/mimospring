package com.example.mimospring.dao;

import com.example.mimospring.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {
    int insertPerson(UUID id, Person person);
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }

    List<Person> selectAllPeople();
    int deletePerson(UUID id);
    int updatePerson(UUID id, Person person);
    Optional<Person> selectPersonByID(UUID id);
}
