package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetAllGamesRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetAllGamesResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;
import com.nashss.se.trueachievementsgroupservice.utils.MetricsUtil;

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
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new GetAllGamesActivity object.
     *
     * @param gameDao GameDao to access the games table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public GetAllGamesActivity(GameDao gameDao, MetricsPublisher metricsPublisher) {
        this.gameDao = gameDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving all games.
     * <p>
     * It then returns the games.
     *
     * @param getAllGamesRequest request object containing the user ID
     *
     * @return getAllGamesResult result object containing the API defined GameModel
     */

    public GetAllGamesResult handleRequest(final GetAllGamesRequest getAllGamesRequest) {
        long startTime = System.currentTimeMillis();
        Set<Game> gameList = gameDao.getAllGames(getAllGamesRequest.getUserId());
        List<GameModel> gameModelList = new ModelConverter().toGameModelList(new HashSet<>(gameList));
        Collections.sort(gameModelList);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Publish metrics
        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_ALL_GAMES_ACTIVITY,
            "Duration"), duration);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_ALL_GAMES_ACTIVITY,
            "StartTime"), startTime);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_ALL_GAMES_ACTIVITY,
            "EndTime"), endTime);

        return GetAllGamesResult.builder()
            .withGameList(gameModelList)
            .build();
    }
}
