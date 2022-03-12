package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Customer;
import com.api.management.prueba.repository.CustomerRepository;
import com.api.management.prueba.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase dedicada al manejo de clientes.
 *
 * @author JuanSerrano
 * @version 0.0.1
 * @since 05-03-2022
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    /**
     * Metodo tipo GetMapping que permite obtener todos los clientes.
     *
     * @param name es el nombre de un cliente.
     * @return el listado de los clientes que coinciden.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll(@RequestParam(required = false) String name) {
        List<Customer> customers = new ArrayList<>();
        HttpStatus msg = HttpStatus.NO_CONTENT;//por defecto no hay contenido.
        if (name == null)
            customerRepository.findAll().forEach(customers::add);
        else
            customerRepository.findByFullNameContainingAndStatusIsTrue(name).forEach(customers::add);

        if (!customers.isEmpty()) {
            msg = HttpStatus.OK;
        }
        return new ResponseEntity<>(customers, msg);
    }

    /**
     * Metodo tipo PostMapping que permite crear una entidad del tipo cliente.
     *
     * @param request son los datos a procesar.
     * @return devuelve el mensaje de la creacion de la entidad.
     */
    @PostMapping("/")
    public ResponseEntity<Customer> create(@Valid @RequestBody CustomerRequest request) {
        Customer l = customerRepository.save(new Customer(request.getFullName(), request.getPhone(), request.getEmail(), request.getAddress(), true));
        return new ResponseEntity<>(l, HttpStatus.CREATED);
    }

    /**
     * Metodo tipo GetMapping que permite buscar una entidad del tipo cliente.
     *
     * @param id el codigo a procesar.
     * @return devuelve los datos de la entidad.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * Metodo tipo PutMapping que permite actualizar una entidad del tipo cliente.
     *
     * @param id el codigo a procesar.
     * @return devuelve los datos de la entidad actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id, @RequestBody CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

    /**
     * Metodo tipo DeleteMapping que permite eliminar una entidad del tipo cliente.
     *
     * @param id el codigo a procesar.
     * @return devuelve el estado de la operacion de eliminacion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
