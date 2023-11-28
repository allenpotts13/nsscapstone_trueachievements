package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGameRequest;
import com.nashss.se.trueachievementsgroupservice.activity.requests.GetUserStatsRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetUserStatsResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
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


    public GetUserStatsResult handleRequest(final GetUserStatsRequest getUserStatsRequest) {
        log.info("handleRequest");

        try {
            // Retrieve the game from the database
            Map<String, Integer> userStats = gameDao.getUserStats(getUserStatsRequest.getUserId());

            // Construct a GetUserStatsResult based on the retrieved data
            GetUserStatsResult result = GetUserStatsResult.builder()
                .withGamerScoreWonIncludeDlc(userStats.get("gamerScoreWonIncludeDlc"))
                .withTrueAchievementWonIncludeDlc(userStats.get("trueAchievementWonIncludeDlc"))
                .withMyCompletionPercentage(userStats.get("myCompletionPercentage"))
                .build();

            log.info("Successfully retrieved user stats: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error processing GetUserStatsRequest", e);
            // Handle exceptions and return an appropriate result or throw a custom exception if needed
            return GetUserStatsResult.builder().withErrorMessage("Error processing request").build();
        }

    }
}
