package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
