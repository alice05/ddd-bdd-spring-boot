package com.santoshkc.leaser.domain.aggregates;

import com.santoshkc.leaser.CustomerId;

public class CustomerAggregate {
    private final CustomerId id;
    private final String customerName;

    public CustomerAggregate(CustomerAggregateBuilder builder) {
        id = new CustomerId(builder.customerId);
        customerName = builder.name;
    }

    //region getters
    public Long getId() {
        return id.getId();
    }

    public String getCustomerName() {
        return customerName;
    }
    //endregion

    public static class CustomerAggregateBuilder {
        private final Long customerId;
        private String name;


        public CustomerAggregateBuilder(Long customerId) {
            this.customerId = customerId;
        }

        public CustomerAggregateBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerAggregate build() {
            return new CustomerAggregate(this);
        }
    }
}
