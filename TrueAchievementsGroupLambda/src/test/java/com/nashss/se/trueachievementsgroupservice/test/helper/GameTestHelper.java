package com.nashss.se.trueachievementsgroupservice.test.helper;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;

public class GameTestHelper {

    private GameTestHelper() {

    }

    public static Game generateGame() {
        Game game = new Game();
        game.setUserId("testaccount@gmail.com");
        game.setUniqueId("123-456-789");
        game.setGameName("Halo 3");
        game.setPlatform("Xbox 360");

        return game;
    }
}
