package com.nashss.se.trueachievementsgroupservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.exceptions.GroupNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsConstants;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GroupDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;

    private GroupDao groupDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        groupDao = new GroupDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void saveGroup_callsMapperWithGroup() {
        Group group = new Group();

        Group results = groupDao.saveGroup(group);

        verify(dynamoDBMapper).save(group);
        assertEquals(group, results);
    }

    @Test
    public void getGroup_withGroupId_callsMapperWithPartitionKey() {
        // GIVEN
        String userId = "userId";
        String groupName = "groupName";
        when(dynamoDBMapper.load(Group.class, userId, groupName)).thenReturn(new Group());

        // WHEN
        Group group = groupDao.getGroup(userId, groupName);

        // THEN
        assertNotNull(group);
        verify(dynamoDBMapper).load(Group.class, userId, groupName);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT), anyDouble());

    }

    @Test
    public void getGroup_groupIdNotFound_throwsGroupNotFoundException() {
        // GIVEN
        String nonexistentGroupId = "NotReal";
        String nonexistentName = "NotReal";
        when(dynamoDBMapper.load(Group.class, nonexistentGroupId, nonexistentName)).thenReturn(null);

        // WHEN + THEN
        assertThrows(GroupNotFoundException.class, () -> groupDao.getGroup(nonexistentGroupId, nonexistentName));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT), anyDouble());
    }

}
