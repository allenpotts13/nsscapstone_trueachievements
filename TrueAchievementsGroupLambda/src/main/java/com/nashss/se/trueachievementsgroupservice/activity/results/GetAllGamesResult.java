package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import java.util.List;

public class GetAllGamesResult {

    private final List<GameModel> gameList;

    private GetAllGamesResult(List<GameModel> gameList) {
        this.gameList = gameList;
    }

    public List<GameModel> getGame() {
        return this.gameList;
    }

    @Override
    public String toString() {
        return "GetAllGamesResult{" +
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
            this.gameList = gameList;
            return this;
        }

        public GetAllGamesResult build() {
            return new GetAllGamesResult(gameList);
        }
    }
}
