package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.DeleteGameFromGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.DeleteGameFromGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.exceptions.GroupNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteGameFromGroupActivity {

    private final Logger log = LogManager.getLogger();

    private final GroupDao groupDao;

    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new DeleteGameFromGroupActivity object.
     *
     * @param groupDao GroupDao to access the group table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public DeleteGameFromGroupActivity(GroupDao groupDao, MetricsPublisher metricsPublisher) {
        this.groupDao = groupDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the group, updating it,
     * and persisting the group.
     * <p>
     * It then returns the updated group.
     * <p>
     * @param deleteGameFromGroupRequest request object containing the group ID, group name, and customer ID
     *                              associated with it
     * @return deleteGameFromGroupResult result object containing the API defined {@link GroupModel}
     */
    public DeleteGameFromGroupResult handleRequest(final DeleteGameFromGroupRequest deleteGameFromGroupRequest) {
        log.info("DeleteGameFromGroupActivity::handleRequest - Received request: {}", deleteGameFromGroupRequest);
        try {
            long startTime = System.currentTimeMillis();

            Group group = groupDao.getGroup(
                deleteGameFromGroupRequest.getUserId(),
                deleteGameFromGroupRequest.getGroupName()
            );


            groupDao.deleteGameFromGroup(
                deleteGameFromGroupRequest.getUserId(),
                deleteGameFromGroupRequest.getGroupName(),
                deleteGameFromGroupRequest.getUniqueId()
            );

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            metricsPublisher.addTime("DeleteGameFromGroupActivity::handleRequest", duration);

            return DeleteGameFromGroupResult.builder()
                .withGroup(new ModelConverter().toGroupModel(group))
                .build();
        } catch (GroupNotFoundException e) {
            log.error("DeleteGameFromGroupActivity::handleRequest - GroupNotFoundException: {}", e.getMessage());
            metricsPublisher.addCount("DeleteGameFromGroupActivity::handleRequest::GroupNotFoundException", 1);
            return DeleteGameFromGroupResult.builder()
                .withError("Group not found")
                .build();
        }
    }
}
