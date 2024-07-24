package com.appjpa.app_jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.appjpa.app_jpa.entities.Person;
import com.appjpa.app_jpa.repositories.PersonRepository;

@SpringBootApplication
public class AppJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;
	public static void main(String[] args) {
		SpringApplication.run(AppJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//List<Person> persons = (List<Person>) personRepository.findAll();
		//List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage("Java");
		List<Person> persons = (List<Person>) personRepository.buscarByProgrammingLanguage("Java","Johlver");
		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personValues = personRepository.obtenerPersonData();
		personValues.stream().forEach(person -> System.out.println(person[0]));
	}

}
