package com.santoshkc.leaser.domain.services;

import com.santoshkc.leaser.CustomerId;
import com.santoshkc.leaser.domain.aggregates.ProjectAggregate;
import com.santoshkc.leaser.domain.repositories.ProjectRepository;
import org.springframework.stereotype.Component;

@Component
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public ProjectAggregate save(ProjectAggregate aggregate) {
        return repository.save(aggregate);
    }

    public boolean projectExistForCustomer(Long customerId) {
        return repository.findById(new CustomerId(customerId)) != null;
    }
}
