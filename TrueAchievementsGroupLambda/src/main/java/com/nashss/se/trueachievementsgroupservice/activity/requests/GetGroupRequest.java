package com.nashss.se.trueachievementsgroupservice.activity.requests;

public class GetGroupRequest {

    private final String userId;

    private final String groupName;

    private GetGroupRequest(String userId, String groupName) {
        this.userId = userId;
        this.groupName = groupName;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "GetGroupRequest{" +
                "userId='" + userId + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String userId;

        private String groupName;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public GetGroupRequest build() {
            return new GetGroupRequest(userId, groupName);
        }
    }
}
