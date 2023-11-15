package com.nashss.se.trueachievementsgroupservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class GameListConverter implements DynamoDBTypeConverter<String, Set<Game>> {
    private static final Gson GSON = new Gson();
    private final Logger log = LogManager.getLogger();

    @Override
    public String convert(Set setToBeConverted) {
        return GSON.toJson(setToBeConverted);
    }

    @Override
    public Set<Game> unconvert(String dynamoDbRepresentation) {
        // need to provide the type parameter of the list to convert correctly
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<Set<Game>>() { } .getType());
    }
}
