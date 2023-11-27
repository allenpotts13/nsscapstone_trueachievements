package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGameRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGameResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import javax.inject.Inject;

/**
 * Implementation of the GetGameActivity for the TrueAchievements Group Service GetGame API.
 * <p>
 * This API allows the user to retrieve a game.
 */
public class GetGameActivity {
    private final GameDao gameDao;

    /**
     * Instantiates a new GetGameActivity object.
     *
     * @param gameDao GameDao to access the games table.
     */
    @Inject
    public GetGameActivity(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    /**
     * This method handles the incoming request by retrieving an existing game
     * with the provided user ID and unique ID from the request.
     * <p>
     * It then returns the game.
     * <p>
     * If the provided user ID or unique ID cannot be found, it throws a
     * GameNotFoundException
     *
     * @param getGameRequest request object containing the user ID associated with it
     * @return getGameResult result object containing the API defined GameModel
     */

    public GetGameResult handleRequest(final GetGameRequest getGameRequest) {
        Game game = gameDao.getGame(getGameRequest.getUserId(), getGameRequest.getUniqueId());
        GameModel gameModel = new ModelConverter().toGameModel(game);
        return GetGameResult.builder()
                .withGame(gameModel)
                .build();
    }
}
