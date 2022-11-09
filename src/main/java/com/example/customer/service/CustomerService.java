package com.example.customer.service;

import com.example.customer.domain.Customer;
import com.example.customer.exception.CustomerNotFound;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomer() throws Exception;
    boolean deleteCustomer(int customerId) throws CustomerNotFound;
    List<Customer> getAllCustomerByProductName(String productName) throws CustomerNotFound;
}
