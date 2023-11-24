package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetAllGamesRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetAllGamesResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 * Implementation of the GetAllGamesActivity for the TrueAchievements Group Service GetAllGames API.
 * <p>
 * This API allows the user to retrieve all games.
 */
public class GetAllGamesActivity {
    private final GameDao gameDao;

    /**
     * Instantiates a new GetAllGamesActivity object.
     *
     * @param gameDao GameDao to access the games table.
     */
    @Inject
    public GetAllGamesActivity(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    /**
     * This method handles the incoming request by retrieving all games.
     * <p>
     * It then returns the games.
     *
     * @return getAllGamesResult result object containing the API defined GameModel
     */

    public GetAllGamesResult handleRequest(final GetAllGamesRequest getAllGamesRequest) {
        Set<Game> gameList = gameDao.getAllGames(getAllGamesRequest.getUserId());
        List<GameModel> gameModelList = new ModelConverter().toGameModelList(new HashSet<>(gameList));
        Collections.sort(gameModelList);
        return GetAllGamesResult.builder()
            .withGameList(gameModelList)
            .build();
    }
}
