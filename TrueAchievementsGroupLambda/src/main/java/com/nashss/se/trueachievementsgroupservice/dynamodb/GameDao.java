package com.nashss.se.trueachievementsgroupservice.dynamodb;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.exceptions.GameNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.List;
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
    public List<Game> getAllGames(String userId) {
        Game game = new Game();
        game.setUserId(userId);

        DynamoDBQueryExpression<Game> queryExpression = new DynamoDBQueryExpression<Game>()
                .withHashKeyValues(game);

        PaginatedQueryList<Game> gamesList = dynamoDbMapper.query(Game.class, queryExpression);

        return gamesList;
    }
}
