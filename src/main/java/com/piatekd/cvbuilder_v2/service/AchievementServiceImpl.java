package com.piatekd.cvbuilder_v2.service;

import com.piatekd.cvbuilder_v2.entity.Achievement;
import com.piatekd.cvbuilder_v2.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AchievementServiceImpl {

    private final AchievementRepository repository;

    public AchievementServiceImpl(AchievementRepository repository) {
        this.repository = repository;
    }

    public Achievement save(Achievement achievement) {
        return repository.save(achievement);
    }
}
