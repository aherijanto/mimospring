package com.example.mimospring.dao;

import com.example.mimospring.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class FakePersonDataAccessService implements PersonDAO{
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> idMayBe = selectPersonByID(id);
        if(idMayBe.isEmpty()){
            return 0;
        }
        DB.remove(idMayBe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person update) {

        return selectPersonByID(id)
                .map(person->{
                    int indexOfPersonToDelete = DB.indexOf(person);
                    if(indexOfPersonToDelete >=0){
                        DB.set(indexOfPersonToDelete,new Person(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

}
