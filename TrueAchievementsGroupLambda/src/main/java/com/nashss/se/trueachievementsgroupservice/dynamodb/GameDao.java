package com.nashss.se.trueachievementsgroupservice.dynamodb;

import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a Game to represent the model in DynamoDB.
 */
@Singleton
public class GameDao {
    private DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a GameDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the games table
     * @param metricsPublisher the {@link MetricsPublisher} used to interact with the cloudwatch data set
     */
    @Inject
    public GameDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }
}
