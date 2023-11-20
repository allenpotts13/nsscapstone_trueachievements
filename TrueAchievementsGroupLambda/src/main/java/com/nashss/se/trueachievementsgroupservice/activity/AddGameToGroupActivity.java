package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.AddGameToGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.AddGameToGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
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

    /**
     * Instantiates a new AddContactToGroupActivity object.
     *
     * @param gameDao GameDao to access the contact table.
     * @param groupDao GroupDao to access the group table.
     */
    @Inject
    public AddGameToGroupActivity(GameDao gameDao, GroupDao groupDao) {
        this.gameDao = gameDao;
        this.groupDao = groupDao;
    }

    /**
     * This method handles the incoming request by adding an additional game
     * to a group and persisting the updated group.
     *
     * @param addGameToGroupRequest request object containing the game userId and uniqueId and a name
     *                                 to retrieve the group and contact.
     * @return addGameToGroupResult result object containing the groups updated list
     *                                 of API defined {@link GameModel}s
     */

    public AddGameToGroupResult handleRequest(final AddGameToGroupRequest addGameToGroupRequest) {
        logger.info("Received AddGameToGroupRequest {} ", addGameToGroupRequest);

        String userId = addGameToGroupRequest.getUserId();
        String uniqueId = addGameToGroupRequest.getUniqueId();
        String groupName = addGameToGroupRequest.getGroupName();

        Game game = gameDao.getGame(userId, uniqueId);
        Group group = groupDao.getGroup(userId, groupName);

        Set<Game> gamesSet = group.getGamesList();
        if (gamesSet == null) {
            gamesSet = new HashSet<>();
        }

        group.setGamesList(gamesSet);

        groupDao.saveGroup(group);

        List<GameModel> gameModelList = new ModelConverter().toGameModelList(gamesSet);
        Set<GameModel> updatedContactSet = new HashSet<>(gameModelList);

        return AddGameToGroupResult.builder()
            .withGameSet(updatedContactSet)
            .build();
    }
}
