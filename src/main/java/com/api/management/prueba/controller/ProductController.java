package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Product;
import com.api.management.prueba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String name) {
        List<Product> products = new ArrayList<>();
        if (name == null)
            productRepository.findAll().forEach(products::add);
        else
            productRepository.findByNameContainingAndStatusIsTrue(name).forEach(products::add);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createService(@Valid @RequestBody Product p){
        Product prod = productRepository.save(new Product(p.getName(), true, p.getDescription(), p.getPrice()));
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
        Product products = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product request) {
        Product products  = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());

        return new ResponseEntity<>(productRepository.save(products), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

