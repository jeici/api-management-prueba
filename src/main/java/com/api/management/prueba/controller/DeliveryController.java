package com.api.management.prueba.controller;

import com.api.management.prueba.exeption.ResourceNotFoundException;
import com.api.management.prueba.model.*;
import com.api.management.prueba.repository.*;
import com.api.management.prueba.request.DeliveryRequest;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LogisticRepository logisticRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping("/allByClient/{id}")
    public ResponseEntity<List<Delivery>> getAll(@PathVariable("id") Long id) {
        List<Delivery> deliveries = new ArrayList<>();
        Customer cus = new Customer();
        if (id <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + id);
        else
            cus = customerRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra cliente con este identificador = " + id));

        deliveries = deliveryRepository.findByCustomerId(cus);
        if (deliveries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Delivery> createDelivery(@Valid @RequestBody DeliveryRequest request){
        Delivery delivery= new Delivery();
        delivery.setCustomerId(new Customer());
        delivery.setProductId(new Product());
        delivery.setLogisticId(new Logistic());
        delivery.setWarehouseId(new Warehouse());

        Pattern pattern1 ;
        Matcher matcher1 ;
        Pattern patternDate ;
        Matcher matcherDate ;
        if(valid(request)){
            delivery.setCustomerId(customerRepository.findById(request.getCustomerId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra cliente con este identificador = " + request.getCustomerId())));
            delivery.setLogisticId(logisticRepository.findById(request.getLogisticId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra tipo de logistica con este codigo = " + request.getLogisticId())));
            delivery.setWarehouseId(warehouseRepository.findById(request.getWarehouseId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra bodega con este codigo = " + request.getWarehouseId())));
            delivery.setProductId(productRepository.findById(request.getProductId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra este producto = " + request.getProductId())));

            if(delivery.getLogisticId().getId()==1){
                pattern1 = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
            }else{
                pattern1 =Pattern.compile("^[A-Z]{3}[0-9]{4}[A-Z]{1}$");
            }

            matcher1 = pattern1.matcher(request.getTransportNumber());
            if(!matcher1.find()){
                throw new ResourceNotFoundException("Numero de transporte no coincide con el formato");
            }

            delivery.setTransportNumber(request.getTransportNumber());
            delivery.setTrakingNumber(generate());
            if(!request.getDeliveryDate().isEmpty()){
                String regex = "(\\d{2,4})-(\\d{2})-(\\d{2})";
                patternDate = Pattern.compile(regex);
                matcherDate = patternDate.matcher(request.getDeliveryDate());
                if(!matcherDate.find()){
                    throw new ResourceNotFoundException("Formato de fecha es incorrecto, YYYY-MM-DD");
                }
                LocalDateTime fecha = LocalDate.parse(request.getDeliveryDate()).atTime(LocalTime.from(LocalDateTime.now()));
                delivery.setDeliveryDate(fecha);
            }

            delivery.setStatus(true);
            delivery.setQuantity(request.getQuantity());
            delivery.setPrice(request.getPrice().setScale(8, RoundingMode.HALF_UP));
            BigDecimal discount = BigDecimal.ZERO.setScale(8, RoundingMode.HALF_UP);
            BigDecimal subTotal = BigDecimal.ZERO.setScale(8, RoundingMode.HALF_UP);
            BigDecimal total = BigDecimal.ZERO.setScale(8, RoundingMode.HALF_UP);
            if(delivery.getQuantity()>=10 ){
                subTotal = delivery.getPrice().multiply(new BigDecimal(delivery.getQuantity())).setScale(8, RoundingMode.HALF_UP);
                discount = subTotal.multiply(delivery.getLogisticId().getDiscountRate().divide(new BigDecimal(100))).setScale(8, RoundingMode.HALF_UP);

                if(discount.compareTo(request.getDiscount().setScale(8, RoundingMode.HALF_UP)) != 0){
                    throw new ResourceNotFoundException("El descuento aplicado es incorrecto");
                }else{
                    delivery.setDiscount(discount);
                }

                total = subTotal.subtract(discount);

                if(total.compareTo(request.getTotal().setScale(8, RoundingMode.HALF_UP))!=0){
                    throw new ResourceNotFoundException("El total es incorrecto");
                }else{
                    delivery.setTotal(total);
                }
            }else{
                delivery.setTotal(delivery.getProductId().getPrice().multiply(new BigDecimal(delivery.getQuantity())));
                delivery.setDiscount(BigDecimal.ZERO);
            }

            deliveryRepository.save(delivery);

            return new ResponseEntity<>(delivery, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public Boolean valid (DeliveryRequest d){
        //cliente
        if (d.getCustomerId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + d.getCustomerId());

        //logistica
        if (d.getLogisticId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un tipo de logistica existente  " + d.getLogisticId());

        //producto
        if (d.getProductId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un producto existente  " + d.getProductId());

        //bodega
        if (d.getWarehouseId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar una bodega existente  " + d.getWarehouseId());

        //cantidad
        if (d.getQuantity() <= 0 )
            throw  new ResourceNotFoundException("la cantidad de producto es requerido " + d.getQuantity());

        //fecha de entrega
        if(d.getDeliveryDate() == null)
            throw  new ResourceNotFoundException("fecha de entrega es requerida" + d.getDeliveryDate());

        // numero de transporte
        if(d.getTransportNumber() == null)
            throw  new ResourceNotFoundException("el numero de transporte es requerido" + d.getTransportNumber());

        return true;
    }

    public String  generate() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

}
