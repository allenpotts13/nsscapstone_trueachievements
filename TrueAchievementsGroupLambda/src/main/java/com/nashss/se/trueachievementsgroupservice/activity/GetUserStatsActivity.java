package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetUserStatsRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetUserStatsResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.utils.MetricsUtil;

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
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new GetUserStatsActivity object.
     *
     * @param gameDao GameDao to access the games table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public GetUserStatsActivity(GameDao gameDao, MetricsPublisher metricsPublisher) {
        this.gameDao = gameDao;
        this.metricsPublisher = metricsPublisher;
    }
    /**
     * This method handles the incoming request by retrieving the user's stats.
     * <p>
     * It then returns the user's stats.
     *
     * @param getUserStatsRequest request object containing the user ID
     * @return getUserStatsResult result object containing the API defined {@link GetUserStatsResult}
     */

    public GetUserStatsResult handleRequest(final GetUserStatsRequest getUserStatsRequest) {
        log.info("handleRequest");
        long startTime = System.currentTimeMillis();

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
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // Publish metrics
            metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_USER_STATS_ACTIVITY,
                "Duration"), duration);

            metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_USER_STATS_ACTIVITY,
                "StartTime"), startTime);

            metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_USER_STATS_ACTIVITY,
                "EndTime"), endTime);

            return result;
        } catch (Exception e) {
            log.error("Error processing GetUserStatsRequest", e);
            // Handle exceptions and return an appropriate result or throw a custom exception if needed
            return GetUserStatsResult.builder().withErrorMessage("Error processing request").build();
        }

    }
}
