package com.nashss.se.trueachievementsgroupservice.activity.results;

import com.nashss.se.trueachievementsgroupservice.models.GameModel;

import java.util.HashSet;
import java.util.Set;

public class AddGameToGroupResult {

    private final Set<GameModel> gameModels;

    private AddGameToGroupResult(Set<GameModel> gameModels) {
        this.gameModels = gameModels;
    }

    public Set<GameModel> getGameModels() {
        return new HashSet<>(gameModels); }

    @Override
    public String toString() {
        return "AddGameToGroupResult{" +
            "gameModels=" + gameModels +
            '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Set<GameModel> gameModels;

        public Builder withGameSet(Set<GameModel> gameModels) {
            this.gameModels = new HashSet<>(gameModels);
            return this;
        }

        public AddGameToGroupResult build() { return new AddGameToGroupResult(gameModels); }
    }
}
