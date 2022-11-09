package com.example.customer.repository;

import com.example.customer.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends MongoRepository<Customer,Integer> {
    @Query("{'customerProduct.productName':{$in:[?0]}}")
    List<Customer> findAllCustomerFromProductName(String productName);
}
