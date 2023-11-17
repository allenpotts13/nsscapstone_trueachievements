package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;

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
}
