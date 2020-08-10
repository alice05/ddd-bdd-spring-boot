package com.santoshkc.leaser.domain.repositories;

import com.santoshkc.leaser.CustomerId;
import com.santoshkc.leaser.ProjectId;
import com.santoshkc.leaser.domain.aggregates.ProjectAggregate;

public interface ProjectRepository {
    ProjectAggregate save(ProjectAggregate aggregate);
    ProjectAggregate findById(CustomerId customerId);
}
