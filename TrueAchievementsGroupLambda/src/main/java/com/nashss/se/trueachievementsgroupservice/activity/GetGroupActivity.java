package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import com.nashss.se.trueachievementsgroupservice.utils.MetricsUtil;

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
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new GetGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public GetGroupActivity(GroupDao groupDao, MetricsPublisher metricsPublisher) {
        this.groupDao = groupDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the group from the database.
     * <p>
     * It then returns the group.
     * <p>
     * If the group does not exist, this should throw a GroupNotFoundException.
     *
     * @param getGroupRequest request object containing the group name
     * @return getGroupResult result object containing the API defined {@link GroupModel}
     */

    public GetGroupResult handleRequest(final GetGroupRequest getGroupRequest) {
        log.info("Received GetGroupRequest {}", getGroupRequest);
        long startTime = System.currentTimeMillis();

        String requestedUserId = getGroupRequest.getUserId();
        String requestedName = getGroupRequest.getGroupName();
        Group group = groupDao.getGroup(requestedUserId, requestedName);
        GroupModel groupModel = new ModelConverter().toGroupModel(group);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Publish metrics
        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_GROUP_ACTIVITY,
            "Duration"), duration);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_GROUP_ACTIVITY,
            "StartTime"), startTime);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.GET_GROUP_ACTIVITY,
            "EndTime"), endTime);

        return GetGroupResult.builder()
            .withGroup(groupModel)
            .build();
    }
}
