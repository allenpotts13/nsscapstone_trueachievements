package com.nashss.se.trueachievementsgroupservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the groups table.
 */
@DynamoDBTable(tableName = "groups")
public class Group {
    private String groupName;
    private List<Game> gamesList;

    @DynamoDBAttribute(attributeName = "groupName")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @DynamoDBAttribute(attributeName = "games_list")
    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) && Objects.equals(gamesList, group.gamesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, gamesList);
    }
}
