package com.nashss.se.trueachievementsgroupservice.activity.requests;

import java.util.Objects;

public class GetGameRequest {

    private final String userId;

    private final String uniqueId;

    private GetGameRequest(String userId, String uniqueId) {
        this.userId = userId;
        this.uniqueId = uniqueId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof GetGameRequest)) {
            return false;
        }

        GetGameRequest that = (GetGameRequest) o;
        return getUserId().equals(that.getUserId()) && getUniqueId().equals(that.getUniqueId());
    }

    @Override
    public String toString() {
        return "GetGameRequest{" +
                "userId='" + userId + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUniqueId());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String userId;

        private String uniqueId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public GetGameRequest build() {
            return new GetGameRequest(userId, uniqueId);
        }
    }
}
