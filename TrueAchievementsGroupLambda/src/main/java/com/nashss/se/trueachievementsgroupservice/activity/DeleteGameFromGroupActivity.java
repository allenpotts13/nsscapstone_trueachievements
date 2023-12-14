package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.DeleteGameFromGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.DeleteGameFromGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.exceptions.GroupNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import com.nashss.se.trueachievementsgroupservice.utils.MetricsUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

public class DeleteGameFromGroupActivity {

    private final Logger log = LogManager.getLogger();
    private final GameDao gameDao;

    private final GroupDao groupDao;

    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new DeleteGameFromGroupActivity object.
     *
     * @param gameDao GameDao to access the game table.
     * @param groupDao GroupDao to access the group table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public DeleteGameFromGroupActivity(GameDao gameDao, GroupDao groupDao, MetricsPublisher metricsPublisher) {
        this.gameDao = gameDao;
        this.groupDao = groupDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the group, updating it,
     * and persisting the group.
     * <p>
     * It then returns the updated group.
     * <p>
     * @param deleteGameFromGroupRequest request object containing the group ID, group name, and customer ID
     *                              associated with it
     * @return deleteGameFromGroupResult result object containing the API defined {@link GroupModel}
     */
    public DeleteGameFromGroupResult handleRequest(final DeleteGameFromGroupRequest deleteGameFromGroupRequest) {
        log.info("DeleteGameFromGroupActivity::handleRequest - Received request: {}", deleteGameFromGroupRequest);
        try {
            long startTime = System.currentTimeMillis();

            String userId = deleteGameFromGroupRequest.getUserId();
            String uniqueId = deleteGameFromGroupRequest.getUniqueId();
            String groupName = deleteGameFromGroupRequest.getGroupName();

            Group group = groupDao.getGroup(userId, groupName);
            Game game = gameDao.getGame(userId, uniqueId);

            Set<Game> gameSet = group.getGamesList();
            if (gameSet == null) {
                gameSet = new HashSet<>();
                group.setGamesList(gameSet);
            }

            gameSet.remove(game);

            groupDao.saveGroup(group);

            List<GameModel> gameModelList = new ModelConverter().toGameModelList(gameSet);
            Set<GameModel> updatedGameSet = new HashSet<>(gameModelList);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // Publish metrics
            metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.DELETE_GAME_FROM_GROUP_ACTIVITY,
                "Duration"), duration);

            metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.DELETE_GAME_FROM_GROUP_ACTIVITY,
                "StartTime"), startTime);

            metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.DELETE_GAME_FROM_GROUP_ACTIVITY,
                "EndTime"), endTime);

            return DeleteGameFromGroupResult.builder()
                .withGameSet(updatedGameSet)
                .build();
        } catch (GroupNotFoundException e) {
            log.error("DeleteGameFromGroupActivity::handleRequest - GroupNotFoundException: {}", e.getMessage());
            metricsPublisher.addCount("DeleteGameFromGroupActivity::handleRequest::GroupNotFoundException", 1);
            return DeleteGameFromGroupResult.builder()
                .withError("Group not found")
                .build();
        }
    }
}
