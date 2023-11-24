package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetAllGamesRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetAllGamesResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class GetAllGamesActivityTest {
    @Mock
    private GameDao gameDao;
    private GetAllGamesActivity getAllGamesActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getAllGamesActivity = new GetAllGamesActivity(gameDao);
    }

    @Test
    public void handleRequestShouldReturnAllGames() {
        // Arrange
        String userId = "userId123";

        // Create some sample games for testing
        Set<Game> gameList = new HashSet<>();

        Game game1 = new Game();
        game1.setUniqueId("123-456-789");
        Game game2 = new Game();
        game2.setUniqueId("987-654-321");

        gameList.add(game1);
        gameList.add(game2);

        when(gameDao.getAllGames(userId)).thenReturn(gameList);

        // Act
        GetAllGamesRequest getAllGamesRequest = GetAllGamesRequest.builder()
            .withUserId(userId)
            .build();

        GetAllGamesResult result = getAllGamesActivity.handleRequest(getAllGamesRequest);

        // Verify that gameDao.getAllGames was called with the correct userId
        verify(gameDao).getAllGames("userId123");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getGame().size());

    }
}
