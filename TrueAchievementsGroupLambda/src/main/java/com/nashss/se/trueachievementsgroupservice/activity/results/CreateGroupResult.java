package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

public class CreateGroupResult {

    private final GroupModel group;

    /**
     * Implementation of the CreateGroupResults for the TrueAchievements Group Service CreateGroup API.
     *
     * @param group the {@link GroupModel} is how a group is formatted.
     */

    public CreateGroupResult(GroupModel group) {
        this.group = group;
    }

    public GroupModel getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "CreateGroupResult{" +
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

        public CreateGroupResult build() {
            return new CreateGroupResult(group);
        }
    }
}
