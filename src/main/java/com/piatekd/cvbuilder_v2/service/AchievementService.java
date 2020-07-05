package com.piatekd.cvbuilder_v2.service;

import com.piatekd.cvbuilder_v2.entity.Achievement;
import com.piatekd.cvbuilder_v2.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AchievementService {

    @Autowired
    AchievementRepository repository;

    public Achievement save(Achievement achievement) {
        return repository.save(achievement);
    }
}
