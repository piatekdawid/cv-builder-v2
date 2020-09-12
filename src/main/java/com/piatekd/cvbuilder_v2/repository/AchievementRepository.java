package com.piatekd.cvbuilder_v2.repository;

import com.piatekd.cvbuilder_v2.entity.Achievement;
import com.piatekd.cvbuilder_v2.entity.ForeignLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    List<Achievement> deleteAchievementById(Long id);
}
