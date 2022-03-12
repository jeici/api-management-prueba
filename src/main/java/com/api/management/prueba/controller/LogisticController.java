package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Logistic;
import com.api.management.prueba.repository.LogisticRepository;
import com.api.management.prueba.request.LogisticRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase dedicada al manejo de tipos de logisticas.
 *
 * @author JuanSerrano
 * @version 0.0.1
 * @since 05-03-2022
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/logistic")
public class LogisticController {
    @Autowired
    LogisticRepository logisticRepository;

    /**
     * Metodo tipo GetMapping que permite obtener todos los tipos de logistica.
     *
     * @param name es el nombre de un tipo de logistica.
     * @return el listado de los tipos de logisticas que coinciden.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Logistic>> getAll(@RequestParam(required = false) String name) {
        List<Logistic> logistics = new ArrayList<>();
        HttpStatus msg = HttpStatus.NO_CONTENT;
        if (name == null)
            logisticRepository.findAll().forEach(logistics::add);
        else
            logisticRepository.findByNameContainingAndStatusIsTrue(name).forEach(logistics::add);

        if (!logistics.isEmpty()) {
            msg = HttpStatus.OK;
        }
        return new ResponseEntity<>(logistics, msg);
    }

    /**
     * Metodo tipo PostMapping que permite crear una entidad del tipo logistica.
     *
     * @param request son los datos a procesar.
     * @return devuelve el mensaje de la creacion de la entidad.
     */
    @PostMapping("/")
    public ResponseEntity<Logistic> create(@Valid @RequestBody LogisticRequest request) {
        Logistic l = logisticRepository.save(new Logistic(request.getName(), true, request.getDescription(), request.getDiscountRate()));
        return new ResponseEntity<>(l, HttpStatus.CREATED);
    }

    /**
     * Metodo tipo GetMapping que permite buscar una entidad del tipo logistica.
     *
     * @param id le codigo a procesar.
     * @return devuelve los datos de la entidad.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Logistic> findById(@PathVariable("id") Long id) {
        Logistic logistic = logisticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(logistic, HttpStatus.OK);
    }

    /**
     * Metodo tipo PutMapping que permite actualizar una entidad del tipo logistica.
     *
     * @param id el codigo a procesar.
     * @return devuelve los datos de la entidad actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Logistic> update(@PathVariable("id") Long id, @RequestBody LogisticRequest request) {
        Logistic logistic = logisticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        logistic.setName(request.getName());
        logistic.setDescription(request.getDescription());
        logistic.setDiscountRate(request.getDiscountRate());
        logistic.setStatus(request.getStatus());

        return new ResponseEntity<>(logisticRepository.save(logistic), HttpStatus.OK);
    }

    /**
     * Metodo tipo DeleteMapping que permite eliminar una entidad del tipo logistica.
     *
     * @param id el codigo a procesar.
     * @return devuelve el estado de la operacion de eliminacion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        logisticRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
