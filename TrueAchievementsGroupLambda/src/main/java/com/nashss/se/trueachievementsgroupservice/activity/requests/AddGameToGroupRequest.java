package com.nashss.se.trueachievementsgroupservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddGameToGroupRequest.Builder.class)
public class AddGameToGroupRequest {

    private final String userId;

    private final String uniqueId;
    private final String groupName;

    /**
     *
     * @param userId the userId for the user
     * @param uniqueId the uniqueId of the game
     * @param groupName the name of the group
     */

    public AddGameToGroupRequest(String userId, String uniqueId, String groupName) {
        this.userId = userId;
        this.uniqueId = uniqueId;
        this.groupName = groupName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "AddGameToGroupRequest{" +
            "userId='" + userId + '\'' +
            ", uniqueId='" + uniqueId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String uniqueId;
        private String groupName;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public Builder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public AddGameToGroupRequest build() {
            return new AddGameToGroupRequest(userId, uniqueId, groupName);
        }
    }


}
