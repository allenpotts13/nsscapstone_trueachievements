package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GameModel;

public class GetGameResult {

    private final GameModel game;

    private GetGameResult(GameModel game) {
        this.game = game;
    }

    public GameModel getGame() {
        return this.game;
    }

    @Override
    public String toString() {
        return "GetGameResult{" +
                "game=" + game +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private GameModel game;

        public Builder withGame(GameModel game) {
            this.game = game;
            return this;
        }

        public GetGameResult build() {
            return new GetGameResult(game);
        }
    }
}
