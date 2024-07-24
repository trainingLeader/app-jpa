package com.appjpa.app_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.appjpa.app_jpa.entities.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person,Long> {
    List<Person> findByProgrammingLanguage(String programmingLanguage);
    
    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);
    
    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    @Query("select p from Person p where p.id in ?1")
    public List<Person> getPersonsByIds(List<Long> ids);

    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterName();
    
    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();
    //------------------- FUNCIONES AGREGADAS -----------------------------------
    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();
    
    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();
    
    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select min(p.id) from Person p")
    Long getMinId();
    
    @Query("select max(p.id) from Person p")
    Long getMaxId();

}
