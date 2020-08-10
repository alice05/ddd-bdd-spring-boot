package com.santoshkc.leaser.application;

import com.santoshkc.leaser.application.DTOs.ProjectDto;
import com.santoshkc.leaser.domain.aggregates.ProjectAggregate;
import com.santoshkc.leaser.domain.services.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectManagementApplication {

    private final ProjectService service;

    public ProjectManagementApplication(ProjectService service) {
        this.service = service;
    }

    public ProjectDto save(ProjectDto dto) {
        ProjectAggregate aggregate = new ProjectAggregate.ProjectAggregateBuilder(null)
                .projectName(dto.getProject_name())
                .userId(dto.getCustomer_id())
                .build();
        ProjectAggregate savedAggregate = service.save(aggregate);
        ProjectDto responseBody = new ProjectDto();
        responseBody.setProject_id(savedAggregate.getProjectId());
        responseBody.setCustomer_id(savedAggregate.getUserId());
        responseBody.setProject_name(savedAggregate.getProjectName());
        return responseBody;
    }

    public boolean projectExistsFor(Long customerId) {
        return service.projectExistForCustomer(customerId);
    }
}
