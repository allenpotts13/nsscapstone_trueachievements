package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGamesInGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGamesInGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetGamesInGroupActivityTest {

    @Mock
    private GroupDao groupDao;

    private GetGamesInGroupActivity getGroupGamesActivity;

    private ModelConverter modelConverter;

    @BeforeEach
    void setup() {
        openMocks(this);
        getGroupGamesActivity = new GetGamesInGroupActivity(groupDao);
        modelConverter = new ModelConverter();
    }

    @Test
    void handleRequest_groupExistsWithGames_returnsGamesInGroup() {
        // GIVEN
        String userId = "userId";
        String groupName = "Test Group";
        Game game1 = new Game();
        game1.setUniqueId("1");
        Game game2 = new Game();
        game2.setUniqueId("2");
        Set<Game> gamesSet = new HashSet<>();
        gamesSet.add(game1);
        gamesSet.add(game2);
        Group group = new Group();
        group.setUserId(userId);
        group.setGroupName(groupName);
        group.setGamesList(gamesSet);

        GetGamesInGroupRequest request = GetGamesInGroupRequest.builder()
                .withUserId(userId)
                .withGroupName(groupName)
                .build();
        when(groupDao.getGroup(userId, groupName)).thenReturn(group);

        // WHEN
        GetGamesInGroupResult result = getGroupGamesActivity.handleRequest(request);

        // THEN
        List<GameModel> resultList = result.getGameList();

        assertEquals(2, resultList.size());
    }

    @Test
    void handleRequest_groupExistsWithoutGames_returnsEmptyList() {
        // GIVEN
        String userId = "userId";
        String groupName = "Test Group";
        Group group = new Group();
        group.setUserId(userId);
        group.setGroupName(groupName);
        group.setGamesList(new HashSet<>());

        GetGamesInGroupRequest request = GetGamesInGroupRequest.builder()
                .withUserId(userId)
                .withGroupName(groupName)
                .build();
        when(groupDao.getGroup(userId, groupName)).thenReturn(group);

        // WHEN
        GetGamesInGroupResult result = getGroupGamesActivity.handleRequest(request);

        // THEN
        List<GameModel> resultList = result.getGameList();

        assertEquals(0, resultList.size());
    }
}
