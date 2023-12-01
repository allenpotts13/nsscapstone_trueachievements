package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.AddGameToGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.AddGameToGroupResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.test.helper.GameTestHelper;
import com.nashss.se.trueachievementsgroupservice.test.helper.GroupTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddGameToGroupActivityTest {
    @Mock
    private GroupDao groupDao;

    @Mock
    private GameDao gameDao;

    private AddGameToGroupActivity addGameToGroupActivity;
    @Mock
    private MetricsPublisher metricsPublisher;

    @BeforeEach
    void setup() {
        openMocks(this);
        addGameToGroupActivity = new AddGameToGroupActivity(gameDao, groupDao, metricsPublisher);
    }

    @Test
    void handleRequest_validRequest_addsGameToGroupList() {
        Group startingGroup = GroupTestHelper.generateGroup();
        String userId = startingGroup.getUserId();
        String groupName = startingGroup.getGroupName();

        Game gameToAdd = GameTestHelper.generateGame();
        String addedUserId = gameToAdd.getUserId();
        String addedUniqueId = gameToAdd.getUniqueId();

        when(groupDao.getGroup(userId, groupName)).thenReturn(startingGroup);
        when(groupDao.saveGroup(startingGroup)).thenReturn(startingGroup);
        when(gameDao.getGame(addedUserId, addedUniqueId)).thenReturn(gameToAdd);

        AddGameToGroupRequest request = AddGameToGroupRequest.builder()
            .withUserId(userId)
            .withUniqueId(addedUniqueId)
            .withGroupName(groupName)
            .build();

        AddGameToGroupResult result = addGameToGroupActivity.handleRequest(request);

        verify(groupDao).saveGroup(startingGroup);

        assertEquals(1, result.getGameModels().size());
    }

    @Test
    void handleRequest_validRequest_doesNotDuplicateGame() {
        Group startingGroup = GroupTestHelper.generateGroup();
        String userId = startingGroup.getUserId();
        String groupName = startingGroup.getGroupName();

        Game originalContact = GameTestHelper.generateGame();
        Set<Game> baseContactSet = new HashSet<>();
        baseContactSet.add(originalContact);
        startingGroup.setGamesList(baseContactSet);

        Game gameToAdd = GameTestHelper.generateGame();
        String addedUserId = gameToAdd.getUserId();
        String addedUniqueId = gameToAdd.getUniqueId();

        when(groupDao.getGroup(userId, groupName)).thenReturn(startingGroup);
        when(groupDao.saveGroup(startingGroup)).thenReturn(startingGroup);
        when(gameDao.getGame(addedUserId, addedUniqueId)).thenReturn(gameToAdd);

        AddGameToGroupRequest request = AddGameToGroupRequest.builder()
            .withUserId(userId)
            .withGroupName(groupName)
            .withUniqueId(addedUniqueId)
            .build();

        AddGameToGroupResult result = addGameToGroupActivity.handleRequest(request);

        verify(groupDao).saveGroup(startingGroup);

        assertEquals(1, result.getGameModels().size());
    }
}
