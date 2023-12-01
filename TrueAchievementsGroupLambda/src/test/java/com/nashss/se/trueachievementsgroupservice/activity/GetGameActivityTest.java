package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGameRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGameResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetGameActivityTest {

    @Mock
    private GameDao gameDao;

    private GetGameActivity activity;
    @Mock
    private MetricsPublisher metricsPublisher;

    @BeforeEach
    public void setup() {
        openMocks(this);
        activity = new GetGameActivity(gameDao, metricsPublisher);
    }

    @Test
    public void handleRequest_returnsWithExpectedValues() {
        // GIVEN
        String expectedUserId = "expectedUserId";
        String expectedUniqueId = "expectedUniqueId";
        Game game = new Game();
        game.setUserId(expectedUserId);
        game.setUniqueId(expectedUniqueId);
        when(gameDao.getGame(expectedUserId, expectedUniqueId)).thenReturn(game);

        GetGameRequest request = GetGameRequest.builder()
            .withUserId(expectedUserId)
            .withUniqueId(expectedUniqueId)
            .build();

        // WHEN
        GetGameResult result = activity.handleRequest(request);
        // THEN
        assertEquals(expectedUserId, result.getGame().getUserId(), "Expected userId to be equal");
        assertEquals(expectedUniqueId, result.getGame().getUniqueId(), "Expected uniqueId to be equal");
    }
}
