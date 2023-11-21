package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.CreateGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.CreateGroupResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.test.helper.GroupTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateGroupActivityTest {

    @Mock
    private GroupDao groupDao;

    private CreateGroupActivity createGroupActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createGroupActivity = new CreateGroupActivity(groupDao);
    }

    @Test
    public void handleRequest_withGameList_createsAndSavesGroupWithGameList() {
        Group expectedGroup = GroupTestHelper.generateGroup();
        String userId = expectedGroup.getUserId();
        String groupName = expectedGroup.getGroupName();
        Set<Game> gameList = expectedGroup.getGamesList();

        CreateGroupRequest request = CreateGroupRequest.builder()
                .withGroupName(groupName)
                .withUserId(userId)
                .withGamesList(gameList)
                .build();

        // WHEN
        CreateGroupResult result = createGroupActivity.handleRequest(request);

        // THEN
        verify(groupDao).saveGroup(any(Group.class));

        assertNotNull(result.getGroup().getUserId());
        assertEquals(groupName, result.getGroup().getGroupName());
        assertEquals(userId, result.getGroup().getUserId());
        assertEquals(gameList, result.getGroup().getGamesList());
    }

}
