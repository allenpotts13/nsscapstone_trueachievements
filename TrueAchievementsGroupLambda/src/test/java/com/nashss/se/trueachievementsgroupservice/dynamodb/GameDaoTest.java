package com.nashss.se.trueachievementsgroupservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GameDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private GameDao gameDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        gameDao = new GameDao(dynamoDBMapper,metricsPublisher);
    }

    @Test
    public void getGame_callsMapperForGame() {
        // GIVEN
        String expectedUserId = "expectedUserId";
        String expectedUniqueId = "expectedUniqueId";
        Game game = new Game();
        game.setUserId(expectedUserId);
        game.setUniqueId(expectedUniqueId);
        when(dynamoDBMapper.load(Game.class,expectedUserId, expectedUniqueId)).thenReturn(game);

        // WHEN
        Game result = gameDao.getGame(expectedUserId, expectedUniqueId);

        // THEN
        assertEquals(game, result, "Expected game and result to be equal");
    }

}
