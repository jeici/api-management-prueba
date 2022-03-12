package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Product;
import com.api.management.prueba.repository.ProductRepository;
import com.api.management.prueba.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase dedicada al manejo de productos.
 *
 * @author JuanSerrano
 * @version 0.0.1
 * @since 05-03-2022
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    /**
     * Metodo que permite obtener todos los productos.
     *
     * @param name es el nombre de un producto.
     * @return el listado de los productos.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String name) {
        List<Product> products = new ArrayList<>();
        HttpStatus msg = HttpStatus.NO_CONTENT;
        if (name == null)
            productRepository.findAll().forEach(products::add);
        else
            productRepository.findByNameContainingAndStatusIsTrue(name).forEach(products::add);

        if (products.isEmpty()) {
            msg = HttpStatus.OK;
        }
        return new ResponseEntity<>(products, msg);
    }

    /**
     * Metodo tipo GetMapping que permite crear una entidad del tipo producto.
     *
     * @param request son los datos a procesar.
     * @return devuelve el mensaje de la creacion de la entidad.
     */
    @PostMapping("/")
    public ResponseEntity<?> createService(@Valid @RequestBody ProductRequest request) {
        Product prod = productRepository.save(new Product(request.getName(), true, request.getDescription(), request.getPrice()));
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    /**
     * Metodo tipo GetMapping que permite buscar una entidad del tipo producto.
     * @param id el codigo a procesar.
     * @return devuelve los datos de la entidad.
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Product products = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Metodo tipo PutMapping que permite actualizar una entidad del tipo producto.
     *
     * @param id el codigo a procesar.
     * @return devuelve los datos de la entidad actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        Product products = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setStatus(request.getStatus());

        return new ResponseEntity<>(productRepository.save(products), HttpStatus.OK);
    }

    /**
     * Metodo tipo DeleteMapping que permite eliminar una entidad del tipo producto.
     *
     * @param id el codigo a procesar.
     * @return devuelve el estado de la operacion de eliminacion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

