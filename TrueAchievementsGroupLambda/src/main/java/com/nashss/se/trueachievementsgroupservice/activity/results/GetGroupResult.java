package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

public class GetGroupResult {

    private final GroupModel group;

    private GetGroupResult(GroupModel group) {
        this.group = group;
    }

    public GroupModel getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "GetGroupResult{" +
                "group=" + group +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private GroupModel group;

        public Builder withGroup(GroupModel group) {
            this.group = group;
            return this;
        }

        public GetGroupResult build() {
            return new GetGroupResult(group);
        }
    }
}
