package com.nashss.se.trueachievementsgroupservice.models;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;

import java.util.List;
import java.util.Objects;

import static com.nashss.se.trueachievementsgroupservice.utils.CollectionUtils.copyToList;

public class GroupModel implements Comparable<GroupModel> {
    private String groupName;
    private List<Game> gamesList;

    private GroupModel(String groupName, List<Game> gamesList) {
        this.groupName = groupName;
        this.gamesList = gamesList;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupModel that = (GroupModel) o;
        return Objects.equals(groupName, that.groupName) &&
                Objects.equals(gamesList, that.gamesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, gamesList);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int compareTo(GroupModel gm) {
        if (groupName.equals(gm.groupName)) {
            return 0;
        } else if(groupName.compareTo(gm.groupName) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static class Builder {
        private String groupName;
        private List<Game> gamesList;

        public Builder groupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder gamesList(List<Game> gamesList) {
            this.gamesList = copyToList(gamesList);
            return this;
        }

        public GroupModel build() {
            return new GroupModel(groupName, gamesList);
        }
    }
}
