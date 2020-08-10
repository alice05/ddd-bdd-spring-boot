package com.santoshkc.leaser.infrastructure.database;

import com.santoshkc.leaser.CustomerId;
import com.santoshkc.leaser.domain.aggregates.ProjectAggregate;
import com.santoshkc.leaser.domain.repositories.ProjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectJPARepository implements ProjectRepository {

    private final ProjectDAO dao;

    public ProjectJPARepository(ProjectDAO dao) {
        this.dao = dao;
    }

    @Override
    public ProjectAggregate save(ProjectAggregate aggregate) {
        ProjectProjection projection = new ProjectProjection();
        projection.setProjectName(aggregate.getProjectName());
        projection.setUserId(aggregate.getUserId());

        ProjectProjection savedProjection = dao.save(projection);
        return getBuild(savedProjection);
    }

    @Override
    public ProjectAggregate findById(CustomerId customerId) {
        ProjectProjection byUserId = dao.findByUserId(customerId.getId());
        if (byUserId == null) {
          return null;
        }
        return getBuild(byUserId);
    }

    private ProjectAggregate getBuild(ProjectProjection savedProjection) {
        return new ProjectAggregate
                .ProjectAggregateBuilder(savedProjection.getId())
                .projectName(savedProjection.getProjectName())
                .userId(savedProjection.getUserId())
                .build();
    }
}
