package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetUserStatsRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetUserStatsResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetUserStatsActivityTest {

    private GetUserStatsActivity getUserStatsActivity;
    @Mock
    private GameDao mockGameDao;
    @Mock
    private MetricsPublisher metricsPublisher;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getUserStatsActivity = new GetUserStatsActivity(mockGameDao, metricsPublisher);
    }

    @Test
    public void testHandleRequestSuccess() {
        // Arrange
        GetUserStatsRequest request = GetUserStatsRequest.builder()
            .withUserId("testUserId")
            .build();

        Map<String, Integer> userStats = new HashMap<>();
        userStats.put("gamerScoreWonIncludeDlc", 100);
        userStats.put("trueAchievementWonIncludeDlc", 50);
        userStats.put("myCompletionPercentage", 75);

        when(mockGameDao.getUserStats("testUserId")).thenReturn(userStats);

        // Act
        GetUserStatsResult result = getUserStatsActivity.handleRequest(request);

        // Assert
        assertEquals(Integer.valueOf(100), result.getGamerScoreWonIncludeDlc());
        assertEquals(Integer.valueOf(50), result.getTrueAchievementWonIncludeDlc());
        assertEquals(Integer.valueOf(75), result.getMyCompletionPercentage());
        assertNull(result.getErrorMessage());
    }

    @Test
    public void testHandleRequestError() {
        // Arrange
        GetUserStatsRequest request = GetUserStatsRequest.builder()
            .withUserId("testUserId")
            .build();

        when(mockGameDao.getUserStats("testUserId")).thenThrow(new RuntimeException("Test error"));

        // Act
        GetUserStatsResult result = getUserStatsActivity.handleRequest(request);

        // Assert
        assertNull(result.getGamerScoreWonIncludeDlc());
        assertNull(result.getTrueAchievementWonIncludeDlc());
        assertNull(result.getMyCompletionPercentage());
        assertEquals("Error processing request", result.getErrorMessage());
    }
}
