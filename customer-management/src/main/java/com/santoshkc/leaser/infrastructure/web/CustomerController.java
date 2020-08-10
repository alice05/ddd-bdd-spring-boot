package com.santoshkc.leaser.infrastructure.web;

import com.santoshkc.leaser.application.CustomerApplication;
import com.santoshkc.leaser.application.DTOs.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerApplication application;

    public CustomerController(CustomerApplication application) {
        this.application = application;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> create(
            @Valid @RequestBody CustomerDto dto) {
        CustomerDto responseDto = application.save(dto);
        return new ResponseEntity<CustomerDto>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isDeleted = application.deleteCustomerById(id);
        if(isDeleted) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
        }
    }
}
