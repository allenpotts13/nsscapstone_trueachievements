package com.nashss.se.trueachievementsgroupservice.test.helper;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;

import java.util.HashSet;
import java.util.Set;

public class GroupTestHelper {

    private GroupTestHelper() {

    }

    public static Group generateGroup() {
        return generateGroupWithGame();
    }

    public static Group generateGroupWithGame() {
        Group group = new Group();
        group.setUserId("testaccount@gmail.com");
        group.setGroupName("Favorite Xbox 360 Games");

        Set<Game> games = new HashSet<>();
        games.add(GameTestHelper.generateGame());

        group.setGamesList(games);

        return group;
    }
}
