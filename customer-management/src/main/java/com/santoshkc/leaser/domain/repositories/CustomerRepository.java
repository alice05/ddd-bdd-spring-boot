package com.santoshkc.leaser.domain.repositories;

import com.santoshkc.leaser.CustomerId;
import com.santoshkc.leaser.domain.aggregates.CustomerAggregate;

public interface CustomerRepository {
    CustomerAggregate save(CustomerAggregate customerAggregate);
    boolean deleteCustomerById(CustomerId customerId);
}
