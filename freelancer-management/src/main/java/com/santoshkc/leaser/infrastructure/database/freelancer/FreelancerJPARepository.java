package com.santoshkc.leaser.infrastructure.database.freelancer;

import com.santoshkc.leaser.UserId;
import com.santoshkc.leaser.domain.aggregates.freelancer.FreelancerAggregate;
import com.santoshkc.leaser.domain.repositories.freelancer.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FreelancerJPARepository implements FreelancerRepository {
    private final FreelancerDAO dao;

    @Autowired
    public FreelancerJPARepository(FreelancerDAO dao) {
        this.dao = dao;
    }


    @Override
    public FreelancerAggregate findById(UserId userId) {
        return null;
    }

    @Override
    public void save(FreelancerAggregate freelancerAggregate) {
        FreelancerProjection projection = new FreelancerProjection();
        projection.setFirstName(freelancerAggregate.getFirstName());
        projection.setLastName(freelancerAggregate.getLastName());
        projection.setCity(freelancerAggregate.getCity());
        projection.setZipCode(freelancerAggregate.getZipCode());
        if (freelancerAggregate.getUserId().getId() != null) {
            projection.setId(freelancerAggregate.getUserId().getId());
        }
        dao.save(projection);
    }

    @Override
    public void remove(FreelancerAggregate freelancerAggregate) {

    }

    @Override
    public Collection<FreelancerAggregate> findByName(String name) {
        return null;
    }

    @Override
    public List<FreelancerAggregate> findAll() {
        return dao.
                findAll()
                .stream()
                .map(this::mapToFreelancerAggregate)
                .collect(Collectors.toList());
    }

    private FreelancerAggregate mapToFreelancerAggregate(FreelancerProjection projection) {
        return new FreelancerAggregate
                .FreelancerAggregateBuilder(new UserId(projection.getId()))
                .firstName(projection.getFirstName())
                .lastName(projection.getLastName())
                .city(projection.getCity())
                .zipCode(projection.getZipCode())
                .build()
                ;

    }
}
