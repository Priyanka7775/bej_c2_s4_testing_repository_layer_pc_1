package com.example.customer.service;

import com.example.customer.domain.Customer;
import com.example.customer.exception.CustomerNotFound;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServicesImpl implements CustomerService{

    private CustomerRepository customerRepository;
    @Autowired
    public CustomerServicesImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFound {
        boolean result=false;
        if(customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFound();
        }else {
            customerRepository.deleteById(customerId);
            return true;
        }
    }

    @Override
    public List<Customer> getAllCustomerByProductName(String productName) throws CustomerNotFound {
        if(customerRepository.findAllCustomerFromProductName(productName).isEmpty()){
            throw new CustomerNotFound();
        }
        return customerRepository.findAllCustomerFromProductName(productName);
    }
}
