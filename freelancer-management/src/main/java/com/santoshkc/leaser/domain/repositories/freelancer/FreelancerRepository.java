package com.santoshkc.leaser.domain.repositories.freelancer;

import com.santoshkc.leaser.UserId;
import com.santoshkc.leaser.domain.aggregates.freelancer.FreelancerAggregate;

import java.util.Collection;

public interface FreelancerRepository {

    FreelancerAggregate findById(UserId userId);
    void save(FreelancerAggregate freelancerAggregate);
    void remove(FreelancerAggregate freelancerAggregate);
    Collection<FreelancerAggregate> findByName(String name);
    Collection<FreelancerAggregate> findAll();

}
