package com.santoshkc.leaser.domain.services;

import com.santoshkc.leaser.CustomerId;
import com.santoshkc.leaser.domain.aggregates.CustomerAggregate;
import com.santoshkc.leaser.domain.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    private final CustomerRepository repository;
    private final ProjectManagementAdapter adapter;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository repository, ProjectManagementAdapter adapter) {
        this.repository = repository;
        this.adapter = adapter;
    }

    public CustomerAggregate save(CustomerAggregate aggregate) {
        return repository.save(aggregate);
    }

    public boolean deleteCustomerById(Long customerId) {
        if (!adapter.projectExistsFor(customerId)) {
            return repository.deleteCustomerById(new CustomerId(customerId));
        } else {
            logger.info("Projects exists for " + customerId);
            return false;
        }
    }

}
