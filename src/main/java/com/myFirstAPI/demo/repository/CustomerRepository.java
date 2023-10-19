package com.myFirstAPI.demo.repository;

import com.myFirstAPI.demo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//to fire queries on my customer table.
// jpa repository interface provides a set of methods to interact with a database for CRUD operations
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
