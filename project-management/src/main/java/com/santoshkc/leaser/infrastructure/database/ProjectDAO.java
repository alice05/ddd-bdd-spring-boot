package com.santoshkc.leaser.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDAO extends JpaRepository<ProjectProjection, Long> {
    ProjectProjection findByUserId(Long userId);
}
