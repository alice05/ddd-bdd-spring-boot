package com.santoshkc.leaser.domain.aggregates.freelancer;

import com.santoshkc.leaser.UserId;

import java.util.Objects;

public class FreelancerAggregate {
    private final UserId userId;
    private final String firstName;
    private final String lastName;
    private final Address address;

    private FreelancerAggregate(FreelancerAggregateBuilder builder) {
        Address address = new Address(builder.zipcode, builder.city);
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = address;
    }

    //region getters
    public UserId getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return address.getCity();
    }

    public String getZipCode() {
        return address.getZipcode();
    }
    //endregion

    // region AggregateBuilder
    public static class FreelancerAggregateBuilder {
        private final UserId userId;
        private String firstName;
        private String lastName;
        private String zipcode;
        private String city;


        public FreelancerAggregateBuilder(UserId userId) {
            this.userId = userId;
        }

        public FreelancerAggregateBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public FreelancerAggregateBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public FreelancerAggregateBuilder zipCode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public FreelancerAggregateBuilder city(String city) {
            this.city = city;
            return this;
        }

        public FreelancerAggregate build() {
            return new FreelancerAggregate(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreelancerAggregate that = (FreelancerAggregate) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    //endregion
}
