package com.nashss.se.trueachievementsgroupservice.activity;

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
 * Implementation of the DeleteGroupActivity for the TrueAchievements Group Service DeleteGroup API.
 * <p>
 * This API allows the customer to delete a group.
 */
public class DeleteGroupActivity {
    private final Logger logger = LogManager.getLogger();
    private final GroupDao groupDao;

    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new DeleteGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public DeleteGroupActivity(GroupDao groupDao, MetricsPublisher metricsPublisher) {
        this.groupDao = groupDao;

        this.metricsPublisher = metricsPublisher;
    }


    /**
     * This method handles the incoming request by deleting a group
     * with the provided group name and user ID from the request.
     * <p>
     * It then returns the updated All Groups list.
     * <p>
     * If the provided group name or user ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param deleteGroupRequest request object containing the group name and user ID
     *                              associated with it
     * @return deleteGroupResult result object containing the API defined {@link GroupModel}
     */

    public DeleteGroupResult deleteGroup(DeleteGroupRequest deleteGroupRequest) {
        logger.info("DeleteGroupActivity::deleteGroup - Start");

        String groupName = deleteGroupRequest.getGroupName();
        String userId = deleteGroupRequest.getUserId();

        if (TrueAchievementGroupServiceUtils.isInvalidAttribute(groupName) || TrueAchievementGroupServiceUtils.isInvalidAttribute(userId)) {
            logger.error("DeleteGroupActivity::deleteGroup - Invalid group name or user ID");
            throw new InvalidAttributeException("Invalid group name or user ID");
        }

        Group group = groupDao.getGroup(groupName);

        if (group == null) {
            logger.error("DeleteGroupActivity::deleteGroup - Group not found");
            throw new InvalidAttributeException("Group not found");
        }

        if (!group.getOwnerId().equals(userId)) {
            logger.error("DeleteGroupActivity::deleteGroup - User is not the owner of the group");
            throw new InvalidAttributeException("User is not the owner of the group");
        }

        groupDao.deleteGroup(groupName);

        Set<Group> groups = groupDao.getAllGroups();

        metricsPublisher.publishMetric(MetricsUtil.createMetric(MetricsConstants.DELETE_GROUP, 1));

        logger.info("DeleteGroupActivity::deleteGroup - End");

        return ModelConverter.convertToGroupModel(groups);
    }
}
