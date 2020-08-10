package com.santoshkc.leaser.application;

import com.santoshkc.leaser.application.DTOs.CustomerDto;
import com.santoshkc.leaser.domain.aggregates.CustomerAggregate;
import com.santoshkc.leaser.domain.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class CustomerApplication {

    private final CustomerService service;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerApplication(CustomerService service) {
        this.service = service;
    }

    public CustomerDto save(CustomerDto dto) {
        if (dto.getId() != null) {
            throw new ValidationException();
        }
        CustomerAggregate aggregate = new CustomerAggregate.CustomerAggregateBuilder(null)
                .name(dto.getFull_name())
                .build();
        CustomerAggregate savedAggregate = service.save(aggregate);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(savedAggregate.getId());
        customerDto.setFull_name(savedAggregate.getCustomerName());
        return customerDto;
    }

    public boolean deleteCustomerById(Long id) {
        if (id != null) {
            return service.deleteCustomerById(id);
        } else {
            logger.info("id can not be null");
            return false;
        }
    }
}
