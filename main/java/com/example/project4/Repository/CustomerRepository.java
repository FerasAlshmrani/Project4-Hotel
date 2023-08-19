package com.example.project4.Repository;

import com.example.project4.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer getCustomerById(Integer id);

    Customer getCustomerByUsernameAndPassword(String username,String password);

    Customer searchCustomerByUsername(String username);

    @Query("select c from Customer c where c.isBooked = true")
    List<Customer> getCustomerByIsBooked();

}
