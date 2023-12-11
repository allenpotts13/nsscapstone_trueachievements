package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.AddGameToGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.AddGameToGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 * Implementation of the AddGameToGroupActivity for the TrueAchievement Group Service AddGameToGroup API.
 *
 * This API allows the user to add a game to an existing group.
 */
public class AddGameToGroupActivity {
    private final Logger logger = LogManager.getLogger();
    private final GameDao gameDao;
    private final GroupDao groupDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new AddGameToGroupActivity object.
     *
     * @param gameDao GameDao to access the games table.
     * @param groupDao GroupDao to access the groups table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public AddGameToGroupActivity(GameDao gameDao, GroupDao groupDao, MetricsPublisher metricsPublisher) {
        this.gameDao = gameDao;
        this.groupDao = groupDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by adding an additional game
     * to a group and persisting the updated group.
     *
     * @param addGameToGroupRequest request object containing the game userId and uniqueId and a name
     *                                 to retrieve the group and game.
     * @return addGameToGroupResult result object containing the groups updated list
     *                                 of API defined {@link GameModel}s
     */

    public AddGameToGroupResult handleRequest(final AddGameToGroupRequest addGameToGroupRequest) {
        logger.info("Received AddGameToGroupRequest {} ", addGameToGroupRequest);
        long startTime = System.currentTimeMillis();

        String userId = addGameToGroupRequest.getUserId();
        String uniqueId = addGameToGroupRequest.getUniqueId();
        String groupName = addGameToGroupRequest.getGroupName();

        Game game = gameDao.getGame(userId, uniqueId);
        Group group = groupDao.getGroup(userId, groupName);

        Set<Game> gamesSet = group.getGamesList();
        if (gamesSet == null) {
            gamesSet = new HashSet<>();
            group.setGamesList(gamesSet);
        }

        gamesSet.add(game);

        groupDao.saveGroup(group);

        List<GameModel> gameModelList = new ModelConverter().toGameModelList(gamesSet);
        Set<GameModel> updatedGameSet = new HashSet<>(gameModelList);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        metricsPublisher.addTime("AddGameToGroupActivity::handleRequest", duration);

        return AddGameToGroupResult.builder()
            .withGameSet(updatedGameSet)
            .build();
    }
}
