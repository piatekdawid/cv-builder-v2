package com.piatekd.cvbuilder_v2.repository;

import com.piatekd.cvbuilder_v2.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {


    List<Experience> deleteExperienceById(Long id);
}
