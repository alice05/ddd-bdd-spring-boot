package com.santoshkc.leaser.application.freelancer;

import com.santoshkc.leaser.UserId;
import com.santoshkc.leaser.application.freelancer.DTOs.FreelancerDto;
import com.santoshkc.leaser.domain.aggregates.freelancer.FreelancerAggregate;
import com.santoshkc.leaser.domain.services.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FreelancerApplication {
    private final FreelancerService freelancerService;

    @Autowired
    public FreelancerApplication(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    public void create(FreelancerDto dto) {

        if (dto.getId() != null) {
            throw new ValidationException();
        }

        FreelancerAggregate aggregate =
                new FreelancerAggregate.FreelancerAggregateBuilder(new UserId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .city(dto.getCity())
                .zipCode(dto.getZipCode())
                .build();

        freelancerService.save(aggregate);
    }

    public Collection<FreelancerDto> getAll() {
        Collection<FreelancerAggregate> aggregates = freelancerService.getAll();
        return aggregates.stream().map(this::mapAggregateToDTO).collect(Collectors.toList());
    }

    // region mapper utilities (May be replaced by mapper)
    private FreelancerDto mapAggregateToDTO(FreelancerAggregate aggregate) {
        FreelancerDto dto = new FreelancerDto();
        dto.setId(aggregate.getUserId().getId());
        dto.setFirstName(aggregate.getFirstName());
        dto.setLastName(aggregate.getLastName());
        dto.setCity(aggregate.getCity());
        dto.setZipCode(aggregate.getZipCode());
        return dto;
    }
    // #endregion
}
