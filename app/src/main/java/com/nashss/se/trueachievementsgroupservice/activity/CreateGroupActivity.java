package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateGroupActivity for the TrueAchievements Group Service CreateGroup API.
 * <p>
 * This API allows the customer to create a new group.
 */
public class CreateGroupActivity {
    private final Logger logger = LogManager.getLogger();
    private final GroupDao groupDao;

    /**
     * Instantiates a new CreateGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     */
    @Inject
    public CreateGroupActivity(GroupDao groupDao) {
        this.groupDao = groupDao;

    }
}
