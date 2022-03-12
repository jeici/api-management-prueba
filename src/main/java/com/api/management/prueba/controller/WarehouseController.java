package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.Warehouse;
import com.api.management.prueba.repository.WarehouseRepository;
import com.api.management.prueba.request.WarehouseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase dedicada al manejo de bodegas o puertos.
 *
 * @author JuanSerrano
 * @version 0.0.1
 * @since 05-03-2022
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    /**
     * Metodo tipo GetMapping que permite obtener todas las bodegas y/o puertos
     *
     * @param name es el nombre de una bodega y/o puerto.
     * @return el listado de las bodegas y/o puertos
     */
    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWarehouse(@RequestParam(required = false) String name) {
        List<Warehouse> ware = new ArrayList<Warehouse>();
        HttpStatus msg = HttpStatus.NO_CONTENT;
        if (name == null)
            warehouseRepository.findAll().forEach(ware::add);
        else
            warehouseRepository.findByNameContainingAndStatusIsTrue(name).forEach(ware::add);

        if (!ware.isEmpty()) {
            msg = HttpStatus.OK;
        }
        return new ResponseEntity<>(ware, msg);
    }

    /**
     * Metodo tipo PostMapping que permite crear una entidad del tipo warehouse.
     *
     * @param request son los datos a procesar.
     * @return devuelve el mensaje de la creacion de la entidad.
     */
    @PostMapping("/")
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody WarehouseRequest request) {
        Warehouse s = warehouseRepository.save(new Warehouse(request.getName(), request.getType(), true, request.getDescription()));
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    /**
     * Metodo tipo GetMapping que permite buscar una entidad del tipo bodega.
     *
     * @param id el codigo a procesar.
     * @return devuelve los datos de la entidad.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable("id") Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    /**
     * Metodo tipo PutMapping que permite actualizar una entidad del tipo bodega.
     *
     * @param id el codigo a procesar.
     * @return devuelve los datos de la entidad actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") Long id, @RequestBody WarehouseRequest request) {
        Warehouse ware = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));
        ware.setName(request.getName());
        ware.setStatus(request.getStatus());
        ware.setType(request.getType());

        return new ResponseEntity<>(warehouseRepository.save(ware), HttpStatus.OK);
    }

    /**
     * Metodo tipo DeleteMapping que permite eliminar una entidad del tipo bodega.
     *
     * @param id el codigo a procesar.
     * @return devuelve el estado de la operacion de eliminacion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWareHouse(@PathVariable("id") Long id) {
        warehouseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

