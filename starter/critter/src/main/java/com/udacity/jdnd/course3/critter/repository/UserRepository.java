package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c from Customer as c INNER JOIN Pet as p on c.id = p.customer.id WHERE p.id = :petId")
    Customer findCustomerByPetId(@Param("petId") long petId);
}
