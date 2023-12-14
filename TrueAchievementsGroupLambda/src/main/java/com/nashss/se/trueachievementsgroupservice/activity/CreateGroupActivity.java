package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.CreateGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.CreateGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.exceptions.InvalidAttributeException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;
import com.nashss.se.trueachievementsgroupservice.utils.MetricsUtil;
import com.nashss.se.trueachievementsgroupservice.utils.TrueAchievementGroupServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

/**
 * Implementation of the CreateGroupActivity for the TrueAchievements Group Service CreateGroup API.
 * <p>
 * This API allows the customer to create a new group.
 */
public class CreateGroupActivity {
    private final Logger logger = LogManager.getLogger();
    private final GroupDao groupDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new CreateGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public CreateGroupActivity(GroupDao groupDao, MetricsPublisher metricsPublisher) {
        this.groupDao = groupDao;

        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by persisting a new group
     * with the provided group name and user ID from the request.
     * <p>
     * It then returns the newly created group.
     * <p>
     * If the provided group name or user ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createGroupRequest request object containing the group name and user ID
     *                              associated with it
     * @return createGroupResult result object containing the API defined {@link GroupModel}
     */

    public CreateGroupResult handleRequest(final CreateGroupRequest createGroupRequest) {
        logger.info("Received CreateGroupRequest {}", createGroupRequest);
        long startTime = System.currentTimeMillis();

        if (!TrueAchievementGroupServiceUtils.isValidString(createGroupRequest.getGroupName())) {
            throw new InvalidAttributeException("Group name [" + createGroupRequest.getGroupName() +
                "] contains illegal characters");
        }

        if (!TrueAchievementGroupServiceUtils.isValidString(createGroupRequest.getUserId())) {
            throw new InvalidAttributeException("User ID [" + createGroupRequest.getUserId() +
                "] contains illegal characters");
        }

        Set<Game> gamesList = null;
        if (createGroupRequest.getGamesList() != null) {
            gamesList = new HashSet<>(createGroupRequest.getGamesList());
        }

        final Group group = new Group();
        group.setGroupName(createGroupRequest.getGroupName());
        group.setUserId(createGroupRequest.getUserId());
        group.setGamesList(gamesList);

        groupDao.saveGroup(group);

        GroupModel groupModel = new ModelConverter().toGroupModel(group);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Publish metrics
        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.CREATE_GROUP_ACTIVITY,
            "Duration"), duration);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.CREATE_GROUP_ACTIVITY,
            "StartTime"), startTime);

        metricsPublisher.addTime(MetricsUtil.getMetricName(MetricsConstants.CREATE_GROUP_ACTIVITY,
            "EndTime"), endTime);

        return CreateGroupResult.builder()
            .withGroup(groupModel)
            .build();
    }
}
