package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import java.util.HashSet;
import java.util.Set;

public class DeleteGameFromGroupResult {

    private final Set<GameModel> gameModels;
    private final String error;

    private DeleteGameFromGroupResult(Set<GameModel> gameModels, String error) {
        this.gameModels = gameModels;
        this.error = error;
    }

    public Set<GameModel> getGameModels() {
        return new HashSet<>(gameModels);
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "DeleteGameFromGroupResult{" +
                "gameModels=" + gameModels +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Set<GameModel> gameModels;
        private String error;

        public Builder withGameSet(Set<GameModel> gameModels) {
            this.gameModels = new HashSet<>(gameModels);
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public DeleteGameFromGroupResult build() {
            return new DeleteGameFromGroupResult(gameModels, error);
        }
    }
}
