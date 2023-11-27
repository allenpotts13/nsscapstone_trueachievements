package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import java.util.ArrayList;
import java.util.List;

public class GetGamesInGroupResult {

    private final List<GameModel> gameList;

    private GetGamesInGroupResult(List<GameModel> gameList) {
        this.gameList = gameList;
    }

    public List<GameModel> getGameList() {
        return new ArrayList<>(gameList);
    }

    @Override
    public String toString() {
        return "GetGamesInGroupResult{" +
                "gameList=" + gameList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<GameModel> gameList;

        public Builder withGameList(List<GameModel> gameList) {
            this.gameList = new ArrayList<>(gameList);
            return this;
        }

        public GetGamesInGroupResult build() {
            return new GetGamesInGroupResult(gameList);
        }
    }
}
