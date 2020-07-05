package com.piatekd.cvbuilder_v2.repository;

import com.piatekd.cvbuilder_v2.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
