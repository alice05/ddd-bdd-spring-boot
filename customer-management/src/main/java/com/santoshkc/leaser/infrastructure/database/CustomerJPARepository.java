package com.santoshkc.leaser.infrastructure.database;

import com.santoshkc.leaser.CustomerId;
import com.santoshkc.leaser.domain.aggregates.CustomerAggregate;
import com.santoshkc.leaser.domain.repositories.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerJPARepository implements CustomerRepository {

    private final CustomerDAO dao;

    public CustomerJPARepository(CustomerDAO dao) {
        this.dao = dao;
    }

    @Override
    public CustomerAggregate save(CustomerAggregate customerAggregate) {
        CustomerProjection projection = new CustomerProjection();
        projection.setFullName(customerAggregate.getCustomerName());

        CustomerProjection entity = dao.save(projection);
        return new CustomerAggregate
                .CustomerAggregateBuilder(entity.getId())
                .name(entity.getFullName())
                .build();
    }

    @Override
    public boolean deleteCustomerById(CustomerId customerId) {
        if (dao.existsById(customerId.getId())) {
            dao.deleteById(customerId.getId());
            return true;
        }
        return false;
    }
}
