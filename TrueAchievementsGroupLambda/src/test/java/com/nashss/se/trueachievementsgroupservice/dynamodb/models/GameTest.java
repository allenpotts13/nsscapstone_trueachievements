package com.nashss.se.trueachievementsgroupservice.dynamodb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new Game();
        game.setUniqueId("123");
        game.setGameName("Halo");
        game.setPlatform("Xbox");
        game.setGameURL("https://www.trueachievements.com/game/Halo");
        game.setAchievementsWonNoDlc(10);
        game.setMaxAchievementsNoDlc(1000);
        game.setAchievementsWonIncludeDlc(20);
    }

    @Test
    void testGetUniqueId() {
        assertEquals("123", game.getUniqueId());
    }

    @Test
    void testGetGameName() {
        assertEquals("Halo", game.getGameName());
    }

    @Test
    void testGetPlatform() {
        assertEquals("Xbox", game.getPlatform());
    }

    @Test
    void testGetGameURL() {
        assertEquals("https://www.trueachievements.com/game/Halo", game.getGameURL());
    }

    @Test
    void testGetAchievementsWonNoDlc() {
        assertEquals(10, game.getAchievementsWonNoDlc());
    }

    @Test
    void testGetMaxAchievementsNoDlc() {
        assertEquals(1000, game.getMaxAchievementsNoDlc());
    }

    @Test
    void testGetAchievementsWonIncludeDlc() {
        assertEquals(20, game.getAchievementsWonIncludeDlc());
    }

    @Test
    void testEquality() {
        Game sameGame = new Game();
        sameGame.setUniqueId("123");
        sameGame.setGameName("Halo");

        assertEquals(game.getUniqueId(), sameGame.getUniqueId());
        assertNotEquals(game, null);
    }
}
