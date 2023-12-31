package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGamesInGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGamesInGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import com.nashss.se.trueachievementsgroupservice.utils.MetricsUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetGamesInGroupActivity for the TrueAchievements Group Service GetGamesInGroup API.
 * <p>
 * This API allows the user to get the list of games of a saved group.
 */
public class GetGamesInGroupActivity {
    private final Logger log = LogManager.getLogger();
    private final GroupDao groupDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new GetGamesInGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public GetGamesInGroupActivity(GroupDao groupDao, MetricsPublisher metricsPublisher) {
        this.groupDao = groupDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the group from the database.
     * <p>
     * It then returns the group's game list.
     * <p>
     * If the group does not exist, this should throw a GroupNotFoundException.
     *
     * @param getGroupGamesRequest request object containing the group name
     * @return getGroupGamesResult result object containing the group's list of API defined {@link GameModel}s
     */

    public GetGamesInGroupResult handleRequest(final GetGamesInGroupRequest getGroupGamesRequest) {
        log.info("Received GetGamesInGroupRequest {}", getGroupGamesRequest);
        long startTime = System.currentTimeMillis();

        Group group = groupDao.getGroup(getGroupGamesRequest.getUserId(), getGroupGamesRequest.getGroupName());
        if (group.getGamesList() == null) {
            return GetGamesInGroupResult.builder()
                    .withGameList(new ArrayList<>())
                    .build();
        }
        List<GameModel> gameModels = new ModelConverter().toGameModelList(group.getGamesList());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Publish metrics
        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_GAMES_IN_GROUP_ACTIVITY,
            "Duration"), duration);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_GAMES_IN_GROUP_ACTIVITY,
            "StartTime"), startTime);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_GAMES_IN_GROUP_ACTIVITY,
            "EndTime"), endTime);

        return GetGamesInGroupResult.builder()
                .withGameList(gameModels)
                .build();
    }
}
