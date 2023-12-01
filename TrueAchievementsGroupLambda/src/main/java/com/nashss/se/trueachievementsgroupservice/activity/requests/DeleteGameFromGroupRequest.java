package com.nashss.se.trueachievementsgroupservice.activity.requests;

public class DeleteGameFromGroupRequest {
    private final String userId;
    private final String groupName;
    private final String uniqueId;

    private DeleteGameFromGroupRequest(String userId, String groupName, String uniqueId) {
        this.userId = userId;
        this.groupName = groupName;
        this.uniqueId = uniqueId;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return "DeleteGameFromGroupRequest{" +
                "userId='" + userId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String groupName;
        private String uniqueId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder withUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public DeleteGameFromGroupRequest build() {
            return new DeleteGameFromGroupRequest(userId, groupName, uniqueId);
        }
    }

}
