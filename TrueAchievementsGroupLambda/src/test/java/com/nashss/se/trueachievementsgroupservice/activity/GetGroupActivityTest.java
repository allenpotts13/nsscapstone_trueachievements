package com.nashss.se.trueachievementsgroupservice.activity;

import com.google.common.collect.Sets;
import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGroupResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetGroupActivityTest {

    @Mock
    private GroupDao groupDao;

    private GetGroupActivity getGroupActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getGroupActivity = new GetGroupActivity(groupDao);
    }

    @Test
    public void handleRequest_savedGroupFound_returnsGroupModelInResult() {
        // GIVEN
        String expectedUserId = "expectedUserId";
        String expectedGroupName = "expectedGroupName";

        Group group = new Group();
        group.setUserId(expectedUserId);
        group.setGroupName(expectedGroupName);

        when(groupDao.getGroup(expectedUserId, expectedGroupName)).thenReturn(group);

        GetGroupRequest request = GetGroupRequest.builder()
                .withUserId(expectedUserId)
                .withGroupName(expectedGroupName)
                .build();

        // WHEN
        GetGroupResult result = getGroupActivity.handleRequest(request);

        // THEN
        assertEquals(expectedUserId, result.getGroup().getUserId());
        assertEquals(expectedGroupName, result.getGroup().getGroupName());
    }
}
