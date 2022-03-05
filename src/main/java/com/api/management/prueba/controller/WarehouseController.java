package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Warehouse;
import com.api.management.prueba.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWareHouse(@RequestParam(required = false) String name) {
        List<Warehouse> ware = new ArrayList<Warehouse>();
        if (name == null)
            warehouseRepository.findAll().forEach(ware::add);
        else
            warehouseRepository.findByNameContainingAndStatusIsTrue(name).forEach(ware::add);

        if (ware.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ware, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Warehouse> createWareHouse(@Valid @RequestBody Warehouse request){
        Warehouse s = warehouseRepository.save(new Warehouse(request.getName(), request.getType(),true, request.getDescription()));
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable("id") Long id){
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWareHouse(@PathVariable("id") Long id, @RequestBody Warehouse request) {
        Warehouse ware  = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));
        ware.setName(request.getName());
        ware.setStatus(request.getStatus());
        ware.setType(request.getType());

        return new ResponseEntity<>(warehouseRepository.save(ware), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWareHouse(@PathVariable("id") Long id) {
        warehouseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

