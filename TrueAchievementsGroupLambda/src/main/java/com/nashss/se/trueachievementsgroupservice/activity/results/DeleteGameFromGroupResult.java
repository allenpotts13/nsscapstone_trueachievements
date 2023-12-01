package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

public class DeleteGameFromGroupResult {

    private final GroupModel group;
    private final String error;

    private DeleteGameFromGroupResult(GroupModel group, String error) {
        this.group = group;
        this.error = error;
    }

    public GroupModel getGroup() {
        return group;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "DeleteGameFromGroupResult{" +
                "group=" + group +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GroupModel group;
        private String error;

        public Builder withGroup(GroupModel group) {
            this.group = group;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public DeleteGameFromGroupResult build() {
            return new DeleteGameFromGroupResult(group, error);
        }
    }
}
