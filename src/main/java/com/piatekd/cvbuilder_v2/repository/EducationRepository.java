package com.piatekd.cvbuilder_v2.repository;

import com.piatekd.cvbuilder_v2.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> deleteEducationById(Long id);
}
