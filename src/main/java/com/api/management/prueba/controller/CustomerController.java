package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Customer;
import com.api.management.prueba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll(@RequestParam(required = false) String name) {
        List<Customer> customers = new ArrayList<>();
        if (name == null)
            customerRepository.findAll().forEach(customers::add);
        else
            customerRepository.findByFullNameContainingAndStatusIsTrue(name).forEach(customers::add);

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }



    @PostMapping("/")
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer){
        Customer l = customerRepository.save(new Customer(customer.getFullName(), customer.getPhone(), customer.getEmail(), customer.getAddress(), true));
        return new ResponseEntity<>(l, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id, @RequestBody Customer request) {
        Customer customer  = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
