package com.nashss.se.trueachievementsgroupservice.activity.requests;

import java.util.Objects;

public class GetAllGamesRequest {

    private final String userId;

    private GetAllGamesRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetAllGamesRequest)) {
            return false;
        }
        GetAllGamesRequest that = (GetAllGamesRequest) o;
        return getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return "GetAllGamesRequest{" +
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

        public GetAllGamesRequest build() {
            return new GetAllGamesRequest(userId);
        }

    }
}
