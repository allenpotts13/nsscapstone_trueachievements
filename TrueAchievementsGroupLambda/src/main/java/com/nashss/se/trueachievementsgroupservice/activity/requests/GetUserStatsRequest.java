package com.nashss.se.trueachievementsgroupservice.activity.requests;

public class GetUserStatsRequest {

    private final String userId;


    private GetUserStatsRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetUserStatsRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;


        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetUserStatsRequest build() {
            return new GetUserStatsRequest(userId);
        }
    }
}
