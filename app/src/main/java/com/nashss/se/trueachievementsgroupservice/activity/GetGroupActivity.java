package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetGroupActivity for the TrueAchievements Group Service GetGroup API.
 *
 * This API allows the user to get one of their saved groups.
 */
public class GetGroupActivity {
    private final Logger log = LogManager.getLogger();
    private final GroupDao groupDao;

    /**
     * Instantiates a new GetGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     */
    @Inject
    public GetGroupActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
}
