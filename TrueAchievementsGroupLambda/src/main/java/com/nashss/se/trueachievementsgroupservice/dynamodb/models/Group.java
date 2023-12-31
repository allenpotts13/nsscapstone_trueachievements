package com.nashss.se.trueachievementsgroupservice.dynamodb.models;

import com.nashss.se.trueachievementsgroupservice.converters.GameListConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a record in the groups table.
 */
@DynamoDBTable(tableName = "groups")
public class Group {
    private String userId;
    private String groupName;
    private Set<Game> gamesList;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @DynamoDBRangeKey(attributeName = "groupName")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @DynamoDBTypeConverted(converter = GameListConverter.class)
    @DynamoDBAttribute(attributeName = "games_list")
    public Set<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(Set<Game> gamesList) {
        this.gamesList = gamesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(userId, group.userId) &&
            Objects.equals(groupName, group.groupName) &&
            Objects.equals(gamesList, group.gamesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupName, gamesList);
    }
}
