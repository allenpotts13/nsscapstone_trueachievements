package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetGamesInGroupActivity for the TrueAchievements Group Service GetGamesInGroup API.
 * <p>
 * This API allows the user to get the list of games of a saved group.
 */
public class GetGamesInGroupActivity {
    private final Logger log = LogManager.getLogger();
    private final GroupDao groupDao;

    /**
     * Instantiates a new GetGamesInGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     */
    @Inject
    public GetGamesInGroupActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
}
