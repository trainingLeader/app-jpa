package com.appjpa.app_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.appjpa.app_jpa.entities.Person;
import com.appjpa.app_jpa.entities.PersonDto;

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
    //------- QUIERIES USIN BETWEEN ---------------
    List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDescLastnameDesc(String name1, String name2);

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc, p.lastname desc")
    List<Person> findAllBetweenName(String c1, String c2);

    List<Person> findAllByOrderByNameAscLastnameDesc();

    //----- USANDO CONCATENACION, UPPER Y LOWER CASE ---------
    @Query("select upper(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();
    
    @Query("select lower(concat(p.name, ' ', p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    // @Query("select CONCAT(p.name, ' ', p.lastname) from Person p")
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();
    @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListCase();
    //******** USING DISTINC -------------------
    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();
    
    @Query("select new com.andres.curso.springboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();
    
    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();
    
    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();
    

}
