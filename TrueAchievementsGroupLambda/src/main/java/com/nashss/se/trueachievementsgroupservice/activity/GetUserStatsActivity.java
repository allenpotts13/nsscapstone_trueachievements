package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetUserStatsActivity for the TrueAchievements Group Service GetUserStats API.
 * <p>
 * This API allows the user to retrieve all their stats.
 */
public class GetUserStatsActivity {
    private final Logger log = LogManager.getLogger();
    private final GameDao gameDao;

    /**
     * Instantiates a new GetUserStatsActivity object.
     *
     * @param gameDao GameDao to access the games table.
     */
    @Inject
    public GetUserStatsActivity(GameDao gameDao) {
        this.gameDao = gameDao;
    }
}
