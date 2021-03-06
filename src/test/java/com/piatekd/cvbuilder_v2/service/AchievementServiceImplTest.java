package com.piatekd.cvbuilder_v2.service;

import com.piatekd.cvbuilder_v2.entity.Achievement;
import com.piatekd.cvbuilder_v2.repository.AchievementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class AchievementServiceImplTest {

    @InjectMocks
    private AchievementServiceImpl achievementService;

    @Mock
    private AchievementRepository achievementRepository;

    @Test
    void saveAchievement() {

        Achievement achievement = new Achievement();
        achievement.setName("someName");
        when(achievementRepository.save(achievement)).thenReturn(achievement);

        Achievement saved = achievementService.save(achievement);

        verify(achievementRepository).save(achievement);
        assertEquals(achievement.getName(), saved.getName());
    }
}