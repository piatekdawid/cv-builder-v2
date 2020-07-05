package com.piatekd.cvbuilder_v2.repository;

import com.piatekd.cvbuilder_v2.entity.ForeignLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<ForeignLanguage, Long> {
}
