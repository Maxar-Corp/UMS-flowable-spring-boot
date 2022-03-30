package com.flowable.flowableboot.repository;

import com.flowable.flowableboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//The Spring repository annotation offers CRUD out of the box
// This is used to find a person by username.
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
  Person findByUsername(String username);
}
