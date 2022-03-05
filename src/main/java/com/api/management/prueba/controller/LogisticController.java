package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Logistic;
import com.api.management.prueba.repository.LogisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/logistic")
public class LogisticController {
    @Autowired
    LogisticRepository logisticRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Logistic>> getAll(@RequestParam(required = false) String name) {
        List<Logistic> logistics = new ArrayList<>();
        if (name == null)
            logisticRepository.findAll().forEach(logistics::add);
        else
            logisticRepository.findByNameContainingAndStatusIsTrue(name).forEach(logistics::add);

        if (logistics.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(logistics, HttpStatus.OK);
    }



    @PostMapping("/")
    public ResponseEntity<Logistic> create(@Valid @RequestBody Logistic logistic){
        Logistic l = logisticRepository.save(new Logistic(logistic.getName(), true, logistic.getDescription(), logistic.getDiscountRate()));
        return new ResponseEntity<>(l, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logistic> findById(@PathVariable("id") Long id){
        Logistic logistic = logisticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(logistic, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Logistic> update(@PathVariable("id") Long id, @RequestBody Logistic request) {
        Logistic logistic  = logisticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        logistic.setName(request.getName());
        logistic.setDescription(request.getDescription());

        return new ResponseEntity<>(logisticRepository.save(logistic), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        logisticRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
