package com.customer.movie.booking.repository;

import com.customer.movie.booking.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//to fire queries on my customer table.
// jpa repository interface provides a set of methods to interact with a database for CRUD operations
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value="Select age from Customer where customerID=:id", nativeQuery=true)
    int retrieveCustomerInfo(@Param("id") int id);

}
