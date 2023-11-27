package com.nashss.se.trueachievementsgroupservice.activity.requests;

public class GetAllGroupsRequest {

    private final String userId;

    private GetAllGroupsRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetAllGroupsRequest)) {
            return false;
        }
        GetAllGroupsRequest that = (GetAllGroupsRequest) o;
        return getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return getUserId().hashCode();
    }

    @Override
    public String toString() {
        return "GetAllGroupsRequest{" +
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

        public GetAllGroupsRequest build() {
            return new GetAllGroupsRequest(userId);
        }

    }
}
