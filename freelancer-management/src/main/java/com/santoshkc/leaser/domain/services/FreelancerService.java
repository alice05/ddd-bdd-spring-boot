package com.santoshkc.leaser.domain.services;

import com.santoshkc.leaser.domain.aggregates.freelancer.FreelancerAggregate;
import com.santoshkc.leaser.domain.repositories.freelancer.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class FreelancerService {

    private final FreelancerRepository repository;

    @Autowired
    public FreelancerService(FreelancerRepository repository) {
        this.repository = repository;
    }

    public void save(FreelancerAggregate aggregate) {
        repository.save(aggregate);
    }

    public Collection<FreelancerAggregate> getAll() {
        return repository.findAll();
    }
}
