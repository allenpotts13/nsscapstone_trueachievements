package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import java.util.List;

public class GetAllGroupsResult {

    private final List<GroupModel> groupList;

    private GetAllGroupsResult(List<GroupModel> groupList) {
        this.groupList = groupList;
    }

    public List<GroupModel> getGroupList() {
        return this.groupList;
    }

    @Override
    public String toString() {
        return "GetAllGroupsResult{" +
            "groupList=" + groupList +
            '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<GroupModel> groupList;

        public Builder withGroupList(List<GroupModel> groupList) {
            this.groupList = groupList;
            return this;
        }

        public GetAllGroupsResult build() {
            return new GetAllGroupsResult(groupList);
        }
    }
}
