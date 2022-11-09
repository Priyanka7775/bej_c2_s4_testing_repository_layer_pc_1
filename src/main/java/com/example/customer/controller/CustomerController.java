package com.example.customer.controller;

import com.example.customer.domain.Customer;
import com.example.customer.exception.CustomerNotFound;
import com.example.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerDetail/api")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<?> insertCustomer(@RequestBody Customer customer){
        Customer customer1=customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);

    }

    @GetMapping("/customer1")
    public ResponseEntity<?> fetchAllCustomer() throws Exception {
        return new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.OK);
    }

    @DeleteMapping("customer2/{customerId}")
    public ResponseEntity<?> deleteCustomerDetail(@PathVariable("customerId") int customerId) throws CustomerNotFound{
        ResponseEntity responseEntity=null;
        try{
            customerService.deleteCustomer(customerId);
            responseEntity=new ResponseEntity("Succesfully Deleted",HttpStatus.OK);
        }catch (CustomerNotFound customerNotFound){
            throw new CustomerNotFound();
        }catch (Exception exception){
            responseEntity=new ResponseEntity(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/customer1/{productName}")
    public ResponseEntity<?> fetchByProductName(@PathVariable String productName) throws CustomerNotFound {
        ResponseEntity responseEntity=null;
        try{
            responseEntity =new ResponseEntity(customerService.getAllCustomerByProductName(productName),HttpStatus.FOUND);
        } catch (CustomerNotFound customerNotFound) {
           throw new CustomerNotFound();
            // responseEntity=new ResponseEntity(customerNotFound.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
