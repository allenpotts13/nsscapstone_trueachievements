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

    // Static variables for index names
    private static final String GAMER_SCORE_INDEX = "userId-gamerScoreWonIncludeDlc-index";
    private static final String TRUE_ACHIEVEMENT_INDEX = "userId-trueAchievementWonIncludeDlc-index";
    private static final String COMPLETION_PERCENTAGE_INDEX = "userId-myCompletionPercentage-index";

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
        Map<String, Integer> userStats = new HashMap<>();

        // Collect the data into the userStats map
        userStats.put("gamerScoreWonIncludeDlc", getAttributeTotal(userId, GAMER_SCORE_INDEX));
        userStats.put("trueAchievementWonIncludeDlc", getAttributeTotal(userId, TRUE_ACHIEVEMENT_INDEX));
        userStats.put("myCompletionPercentage", getAverageMyCompletionPercentage(userId, COMPLETION_PERCENTAGE_INDEX));

        return userStats;
    }

    // Method to query a GSI and sum up the values for a specific attribute
    private int getAttributeTotal(String userId, String indexName) {
        DynamoDBQueryExpression<Game> queryExpression = new DynamoDBQueryExpression<Game>()
            .withIndexName(indexName)
            .withConsistentRead(false)
            .withKeyConditionExpression("userId = :userId")
            .withExpressionAttributeValues(Map.of(":userId", new AttributeValue().withS(userId)));

        PaginatedQueryList<Game> results = dynamoDbMapper.query(Game.class, queryExpression);

        // Sum up the attribute from all items
        return results.stream()
            .mapToInt(game -> getAttribute(game, indexName))
            .sum();
    }

    // Method to calculate the average of myCompletionPercentage
    private int getAverageMyCompletionPercentage(String userId, String indexName) {
        DynamoDBQueryExpression<Game> queryExpression = new DynamoDBQueryExpression<Game>()
            .withIndexName(indexName)
            .withConsistentRead(false)
            .withKeyConditionExpression("userId = :userId")
            .withExpressionAttributeValues(Map.of(":userId", new AttributeValue().withS(userId)));

        PaginatedQueryList<Game> results = dynamoDbMapper.query(Game.class, queryExpression);

        // Calculate the average of myCompletionPercentage
        int sum = results.stream()
            .mapToInt(game -> getAttribute(game, indexName))
            .sum();

        int count = (int) results.stream()
            .filter(game -> getAttribute(game, indexName) != 0)
            .count();

        return count > 0 ? sum / count : 0;
    }

    // Helper method to get the attribute value based on the GSI
    private int getAttribute(Game game, String indexName) {
        switch (indexName) {
            case GAMER_SCORE_INDEX:
                return game.getGamerScoreWonIncludeDlc() != null ? game.getGamerScoreWonIncludeDlc() : 0;
            case TRUE_ACHIEVEMENT_INDEX:
                return game.getTrueAchievementWonIncludeDlc() != null ? game.getTrueAchievementWonIncludeDlc() : 0;
            case COMPLETION_PERCENTAGE_INDEX:
                return game.getMyCompletionPercentage() != null ? game.getMyCompletionPercentage() : 0;
            default:
                return 0;
        }
    }
}
