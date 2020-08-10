package com.santoshkc.leaser.unit.application.freelancer;

import com.santoshkc.leaser.LeaserApplication;
import com.santoshkc.leaser.UserId;
import com.santoshkc.leaser.application.freelancer.DTOs.FreelancerDto;
import com.santoshkc.leaser.application.freelancer.FreelancerApplication;
import com.santoshkc.leaser.domain.aggregates.freelancer.FreelancerAggregate;
import com.santoshkc.leaser.domain.services.FreelancerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LeaserApplication.class})
public class FreelancerApplicationTest {

    @MockBean
    private FreelancerService service;

    @Autowired
    private FreelancerApplication sut;

    private FreelancerAggregate aggregate;
    private FreelancerDto dto;

    @Before
    public void setUp() {
        initializeAggregate();
        initializeDTO();
    }

    private void initializeAggregate() {
        aggregate = new FreelancerAggregate.FreelancerAggregateBuilder(new UserId())
                .firstName("first name")
                .lastName("last name")
                .city("test city")
                .zipCode("test zip")
                .build();
    }

    private void initializeDTO() {
        dto = new FreelancerDto();
        dto.setFirstName("first name");
        dto.setLastName("last name");
        dto.setCity("test city");
        dto.setZipCode("test zip");
    }

    @Test
    public void whenCreateRequestedWithDTO_saveAggregate() {
        sut.create(dto);
        Mockito.verify(service, Mockito.times(1)).save(aggregate);
    }

    @Test(expected = ValidationException.class)
    public void whenCreatedWithId_shouldThrowExceptions() {
        dto.setId(1L);
        sut.create(dto);
    }

    @Test
    public void whenRequestedForDto_shouldProvideDataFromAggregate() {
        List<FreelancerAggregate> collect = Stream.of(aggregate).collect(Collectors.toList());
        Mockito.when(service.getAll()).thenReturn(collect);

        Collection<FreelancerDto> all = sut.getAll();
        assertThat(all.size()).isEqualTo(1);
    }
}