package com.nashss.se.trueachievementsgroupservice.dynamodb;

import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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
}
