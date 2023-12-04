package com.nashss.se.trueachievementsgroupservice.dynamodb;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.exceptions.GameNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the games table
     * @param metricsPublisher the {@link MetricsPublisher} used to interact with the cloudwatch data set
     */
    @Inject
    public GameDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link Game} corresponding to the specified id.
     *
     * @param userId the user ID
     * @param uniqueId the unique ID
     *
     * @return the stored Game, or null if none was found.
     */
    public Game getGame(String userId, String uniqueId) {
        Game game = this.dynamoDbMapper.load(Game.class, userId, uniqueId);

        if (game == null) {
            metricsPublisher.addCount(MetricsConstants.GETGAME_GAMENOTFOUND_COUNT, 1);
            throw new GameNotFoundException("Could not find game with id " + uniqueId);
        }
        metricsPublisher.addCount(MetricsConstants.GETGAME_GAMENOTFOUND_COUNT, 0);
        return game;
    }

    /**
     * Returns the Game List corresponding to the querying unique.
     *
     * @param userId the user ID
     * @return the stored games, or none if none were found.
     */
    public Set<Game> getAllGames(String userId) {
        Game game = new Game();
        game.setUserId(userId);

        DynamoDBQueryExpression<Game> queryExpression = new DynamoDBQueryExpression<Game>()
                .withHashKeyValues(game);

        PaginatedQueryList<Game> gamesList = dynamoDbMapper.query(Game.class, queryExpression);

        return new HashSet<>(gamesList);
    }

    /**
     * Returns the User Stats corresponding to the querying unique.
     *
     * @param userId the user ID
     * @return the user stats, or none if none were found.
     */
    public Map<String, Integer> getUserStats(String userId) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":userId", new AttributeValue().withS(userId));

        Map<String, Integer> userStats = new HashMap<>();
        userStats.put("gamerScoreWonIncludeDlc",
            sumIndex("GamerScoreWonIncludeDlc-UserId-Index",
                "gamerScoreWonIncludeDlc",
                ":userId", expressionAttributeValues));
        userStats.put("trueAchievementWonIncludeDlc",
            sumIndex("TrueAchievementWonIncludeDlc-UserId-Index",
                "trueAchievementWonIncludeDlc",
                ":userId", expressionAttributeValues));
        userStats.put("myCompletionPercentage",
            sumIndex("MyCompletionPercentage-UserId-Index",
                "myCompletionPercentage",
                ":userId", expressionAttributeValues));

        return userStats;
    }

    private int sumIndex(String indexName, String attributeName, String conditionExpression ,
                         Map<String, AttributeValue> expressionAttributeValues) {
        // Use a StringBuilder for constructing the query expression
        StringBuilder queryExpressionBuilder = new StringBuilder("userId = :userId");

        // Add additional conditions if provided
        if (conditionExpression != null && !conditionExpression.isEmpty()) {
            queryExpressionBuilder.append(" AND ").append(conditionExpression);
        }

        // Perform a scan using DynamoDBMapper
        PaginatedQueryList<Game> games = dynamoDbMapper.query(Game.class, new DynamoDBQueryExpression<Game>()
                .withIndexName(indexName)
                .withConsistentRead(false)
                .withKeyConditionExpression(queryExpressionBuilder.toString())
                .withExpressionAttributeValues(expressionAttributeValues));

        Set<Game> gameSet = new HashSet<>(games);

        // Sum the values
        return gameSet.stream().mapToInt(game -> getValueByAttributeName(game, attributeName)).sum();
    }

    private int getValueByAttributeName(Game game, String attributeName) {
        switch (attributeName) {
            case "gamerScoreWonIncludeDlc":
                return game.getGamerScoreWonIncludeDlc();
            case "trueAchievementWonIncludeDlc":
                return game.getTrueAchievementWonIncludeDlc();
            case "myCompletionPercentage":
                return game.getMyCompletionPercentage();
            // Add other attributes as needed
            default:
                throw new IllegalArgumentException("Invalid attribute name: " + attributeName);
        }
    }
}
