package com.nashss.se.trueachievementsgroupservice.dynamodb;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.exceptions.GroupNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a Group to represent the model in DynamoDB.
 */
@Singleton
public class GroupDao {
    private DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a GroupDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the groups table
     * @param metricsPublisher the {@link MetricsPublisher} used to interact with the cloudwatch data set
     */

    @Inject
    public GroupDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Saves (creates or updates) the given {@link Group}.
     *
     * @param group The group to save
     * @return The Group object that was saved
     */
    public Group saveGroup(Group group) {
        this.dynamoDbMapper.save(group);
        return group;
    }

    /**
     * Returns the {@link Group} corresponding to the specified userId and groupName.
     *
     * @param userId the Group's userId
     * @param groupName the Group's name
     * @return the stored Group, or null if none was found.
     */
    public Group getGroup(String userId, String groupName) {
        Group group = this.dynamoDbMapper.load(Group.class, userId, groupName);

        if (group == null) {
            metricsPublisher.addCount(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT, 1);
            throw new GroupNotFoundException("Could not find group for user " + userId + " with name " + groupName);
        }
        metricsPublisher.addCount(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT, 0);
        return group;
    }

    /**
     * Returns the Group List corresponding to the querying user's ID.
     *
     * @param userId the user ID
     * @return the stored groups, or none if none were found.
     */
    public List<Group> getAllGroups(String userId) {
        Group group = new Group();
        group.setUserId(userId);

        DynamoDBQueryExpression<Group> queryExpression = new DynamoDBQueryExpression<Group>()
            .withHashKeyValues(group);

        PaginatedQueryList<Group> groupList = dynamoDbMapper.query(Group.class, queryExpression);
        return groupList;
    }

    /**
     * Deletes the {@link Game} corresponding to the specified userId and groupName.
     *
     * @param userId the Group's userId
     * @param groupName the Group's name
     * @param uniqueId the Game's uniqueId
     *
     * @return the stored Group, or null if none was found.
     */
    public Group deleteGameFromGroup(String userId, String groupName, String uniqueId) {
        Group group = this.dynamoDbMapper.load(Group.class, userId, groupName);

        Set<Game> gamesList = group.getGamesList();
        if (gamesList != null) {
            gamesList.removeIf(game -> game.getUniqueId().equals(uniqueId));

            group.setGamesList(gamesList);

            saveGroup(group);
        }
        return group;
    }
}
