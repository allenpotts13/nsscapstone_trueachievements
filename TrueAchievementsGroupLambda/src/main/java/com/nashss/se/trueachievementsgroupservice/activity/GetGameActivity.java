package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;

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
}
