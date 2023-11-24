package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetAllGroupsRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetAllGroupsResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    /**
     * This method handles the incoming request by retrieving all existing groups.
     * <p>
     * It then returns the groups.
     *
     * @param getAllGroupsRequest request object containing the user ID associated with it
     * @return getAllGroupsResult result object containing the API defined GroupModel
     */

    public GetAllGroupsResult handleRequest(final GetAllGroupsRequest getAllGroupsRequest) {
        ModelConverter mc = new ModelConverter();
        List<Group> groupList = groupDao.getAllGroups(getAllGroupsRequest.getUserId());
        List<GroupModel> groupModelList = new ArrayList<>();
        for (Group group : groupList) {
            groupModelList.add(mc.toGroupModel(group));
        }
        Collections.sort(groupModelList);
        return GetAllGroupsResult.builder()
            .withGroupList(groupModelList)
            .build();
    }
}
