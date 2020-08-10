package com.santoshkc.leaser.infrastructure.database.timesheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetDAO extends JpaRepository<TimesheetProjection, Long> {
}
