package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import javax.inject.Inject;

/**
 * Implementation of the GetAllGroupsActivity for the TrueAchievements Group Service GetAllGroups API.
 * <p>
 * This API allows the user to retrieve all groups.
 */
public class GetAllGroupsActivity {
    private final GroupDao groupDao;

    /**
     * Instantiates a new GetAllGroupsActivity object.
     *
     * @param groupDao GroupDao to access the Groups table.
     */
    @Inject
    public GetAllGroupsActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
}
