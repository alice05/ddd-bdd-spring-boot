package com.santoshkc.leaser.unit.infratstructure.database.freelancer;

import com.santoshkc.leaser.LeaserApplication;
import com.santoshkc.leaser.UserId;
import com.santoshkc.leaser.domain.aggregates.freelancer.FreelancerAggregate;
import com.santoshkc.leaser.infrastructure.database.freelancer.FreelancerDAO;
import com.santoshkc.leaser.infrastructure.database.freelancer.FreelancerJPARepository;
import com.santoshkc.leaser.infrastructure.database.freelancer.FreelancerProjection;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LeaserApplication.class})
public class FreelancerJPARepositoryTest {

    @MockBean
    private FreelancerDAO dao;

    private FreelancerProjection projection;
    private FreelancerAggregate aggregate;

    @Autowired
    private FreelancerJPARepository sut;

    @Before
    public void setUp() {
        initializeData();
        initializeAggregate();
    }

    private void initializeData() {
        projection = new FreelancerProjection();
        projection.setFirstName("first name");
        projection.setLastName("last name");
        projection.setCity("test city");
        projection.setZipCode("test zip");
    }

    private void initializeAggregate() {
        aggregate = new FreelancerAggregate.FreelancerAggregateBuilder(new UserId(null))
                .firstName("first name")
                .lastName("last name")
                .city("test city")
                .zipCode("test zip")
                .build();
    }

    @Test
    public void whenGivenAggregateData_thenShouldSaveToDb() {
        sut.save(aggregate);
        Mockito.verify(dao, Mockito.times(1)).save(projection);
    }

    @Test
    public void whenRequesting_shouldBeAbleToGetAggregateDataFromDB() {
        List<FreelancerProjection> mockedData = Stream.of(projection).collect(Collectors.toList());
        Mockito.when(dao.findAll()).thenReturn(mockedData);

        List<FreelancerAggregate> all = sut.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}