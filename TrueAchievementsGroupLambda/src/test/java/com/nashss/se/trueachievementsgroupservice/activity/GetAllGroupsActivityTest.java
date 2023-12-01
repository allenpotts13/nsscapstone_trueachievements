package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetAllGroupsRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetAllGroupsResult;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetAllGroupsActivityTest {

    @Mock
    private GroupDao groupDao;
    private GetAllGroupsActivity getAllGroupsActivity;
    @Mock
    private MetricsPublisher metricsPublisher;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getAllGroupsActivity = new GetAllGroupsActivity(groupDao, metricsPublisher);
    }

    @Test
    public void handleRequestShouldReturnAllGroups() {
        // Arrange
        String userId = "userId123";

        // Create some sample groups for testing
        List<Group> groupList = new ArrayList<>();

        Group group1 = new Group();
        group1.setGroupName("Favorite Games");
        Group group2 = new Group();
        group2.setGroupName("RPGs");
        groupList.add(group1);
        groupList.add(group2);

        when(groupDao.getAllGroups(userId)).thenReturn(groupList);

        // Act
        GetAllGroupsRequest getAllGroupsRequest = GetAllGroupsRequest.builder()
            .withUserId(userId)
            .build();

        GetAllGroupsResult result = getAllGroupsActivity.handleRequest(getAllGroupsRequest);

        // Verify that groupDao.getAllGroups was called with the correct userId
        verify(groupDao).getAllGroups("userId123");

        //Assert that the result contains the correct groups
        assertNotNull(result);
        assertEquals(2, result.getGroupList().size());
    }
}
