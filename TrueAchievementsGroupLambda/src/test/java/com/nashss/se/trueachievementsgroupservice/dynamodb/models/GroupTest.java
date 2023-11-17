package com.nashss.se.trueachievementsgroupservice.dynamodb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GroupTest {

    private Group group;

    @BeforeEach
    void setup() {
        group = new Group();
        group.setGroupName("Test Group");

        List<Game> gamesList = new ArrayList<>();
        Game game1 = new Game();
        game1.setUniqueId("123");
        game1.setGameName("Halo");

        Game game2 = new Game();
        game2.setUniqueId("456");
        game2.setGameName("Halo 2");

        gamesList.add(game1);
        gamesList.add(game2);
        group.setGamesList(gamesList);

    }

    @Test
    void testGetGroupName() {
        assertEquals("Test Group", group.getGroupName());
    }

    @Test
    void testGetGamesList() {
        List<Game> gamesListTest = new ArrayList<>();
        Game game1 = new Game();
        game1.setUniqueId("123");
        game1.setGameName("Halo");

        Game game2 = new Game();
        game2.setUniqueId("456");
        game2.setGameName("Halo 2");

        gamesListTest.add(game1);
        gamesListTest.add(game2);


        assertEquals(gamesListTest, group.getGamesList());
    }

    @Test
    void differentGamesList() {
        List<Game> gamesListTest = new ArrayList<>();
        Game game1 = new Game();
        game1.setUniqueId("789");
        game1.setGameName("Gears of War");

        Game game2 = new Game();
        game2.setUniqueId("101");
        game2.setGameName("Gears of War 2");

        gamesListTest.add(game1);
        gamesListTest.add(game2);

        assertNotEquals(gamesListTest, group.getGamesList());
    }
}
