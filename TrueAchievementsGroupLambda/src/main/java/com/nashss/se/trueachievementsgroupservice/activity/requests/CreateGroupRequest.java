package com.nashss.se.trueachievementsgroupservice.activity.requests;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

import static com.nashss.se.trueachievementsgroupservice.utils.CollectionUtils.copyToSet;

/**
 * Implementation of the CreateGroupRequest for the TrueAchievements Group Service CreateGroup API.
 */
@JsonDeserialize(builder = CreateGroupRequest.Builder.class)
public class CreateGroupRequest {

    private final String groupName;
    private final String userId;
    private final Set<Game> gamesList;

    /**
     * Constructor that gets the tags for this Group.
     *
     * @param groupName Name for this group
     * @param userId UserId for this group
     * @param gamesList Set of games for this group
     */
    private CreateGroupRequest(String groupName, String userId, Set<Game> gamesList) {
        this.groupName = groupName;
        this.userId = userId;
        this.gamesList = gamesList;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUserId() {
        return userId;
    }

    public Set<Game> getGamesList() {
        return gamesList;
    }

    @Override
    public String toString() {
        return "CreateGroupRequest{" +
                "groupName='" + groupName + '\'' +
                ", userId='" + userId + '\'' +
                ", gamesList=" + gamesList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String groupName;
        private String userId;
        private Set<Game> gamesList;

        public Builder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withGamesList(Set<Game> gamesList) {
            this.gamesList = copyToSet(gamesList);
            return this;
        }

        public CreateGroupRequest build() {
            return new CreateGroupRequest(groupName, userId, gamesList);
        }
    }
}
