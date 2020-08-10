package com.santoshkc.leaser.domain.aggregates;

import com.santoshkc.leaser.ProjectId;
import com.santoshkc.leaser.UserId;

public class ProjectAggregate {
    private final ProjectId projectId;
    private final String projectName;
    private final UserId userId;

    private ProjectAggregate(ProjectAggregateBuilder builder) {
        projectId = new ProjectId(builder.projectId);
        projectName = builder.projectName;
        userId = new UserId(builder.userId);
    }

    public Long getProjectId() {
        return projectId.getId();
    }

    public String getProjectName() {
        return projectName;
    }

    public Long getUserId() {
        return userId.getId();
    }

    public static class ProjectAggregateBuilder {
        private final Long projectId;
        private String projectName;
        private Long userId;

        public ProjectAggregateBuilder(Long projectId) {
            this.projectId = projectId;
        }

        public ProjectAggregateBuilder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public ProjectAggregateBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ProjectAggregate build() {
            return new ProjectAggregate(this);
        }
    }
}
