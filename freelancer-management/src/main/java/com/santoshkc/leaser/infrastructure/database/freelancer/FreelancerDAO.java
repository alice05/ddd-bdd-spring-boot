package com.santoshkc.leaser.infrastructure.database.freelancer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerDAO extends JpaRepository<FreelancerProjection, Long> {

}
