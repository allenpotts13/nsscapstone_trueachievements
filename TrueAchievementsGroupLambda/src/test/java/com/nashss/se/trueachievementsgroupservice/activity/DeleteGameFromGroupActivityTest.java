package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.DeleteGameFromGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.DeleteGameFromGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GameDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.exceptions.GroupNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import com.nashss.se.trueachievementsgroupservice.test.helper.GroupTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteGameFromGroupActivityTest {

	@Mock
	private GroupDao groupDao;

	@Mock
	private GameDao gameDao;

	@Mock
	private MetricsPublisher metricsPublisher;

	@InjectMocks
	private DeleteGameFromGroupActivity deleteGameFromGroupActivity;

	@BeforeEach
	void setUp() {
		initMocks(this);
		deleteGameFromGroupActivity = new DeleteGameFromGroupActivity(gameDao, groupDao, metricsPublisher);
	}

	@Test
	public void handleRequest_validRequest_deletesGameFromGroup() {
		// Mocking the group retrieved from the DAO
		Group mockGroup = GroupTestHelper.generateGroup();
		String userId = mockGroup.getUserId();
		String groupName = mockGroup.getGroupName();
		String uniqueId = mockGroup.getGamesList().iterator().next().getUniqueId();

		// Mocking the group retrieved after deletion
		Game gameToDelete = mockGroup.getGamesList().iterator().next();
		mockGroup.getGamesList().remove(gameToDelete);


		// Mocking behavior of the groupDao
		when(groupDao.getGroup(userId, groupName)).thenReturn(mockGroup);
		when(groupDao.saveGroup(mockGroup)).thenReturn(mockGroup);
		when(groupDao.deleteGameFromGroup(userId, groupName, uniqueId)).thenReturn(mockGroup);

		// Execute the method
		DeleteGameFromGroupRequest request = DeleteGameFromGroupRequest.builder()
			.withUserId(userId)
			.withGroupName(groupName)
			.withUniqueId(uniqueId)
			.build();

		DeleteGameFromGroupResult result = deleteGameFromGroupActivity.handleRequest(request);

		// Verify that the groupDao methods were called
		verify(groupDao).getGroup(userId, groupName);
		verify(groupDao).saveGroup(mockGroup);

		assertEquals(0, result.getGameModels().size());

	}

	@Test
	void handleRequest_groupNotFoundException_returnsError() {
		// Mock data
		String userId = "user123";
		String groupName = "Favorite Games";
		String uniqueId = "uniqueId123";

		// Mocking behavior of the groupDao to throw GroupNotFoundException
		when(groupDao.getGroup(userId, groupName)).thenThrow(new GroupNotFoundException("Group not found"));

		// Execute the method
		DeleteGameFromGroupRequest request = DeleteGameFromGroupRequest.builder()
			.withUserId(userId)
			.withGroupName(groupName)
			.withUniqueId(uniqueId)
			.build();

		DeleteGameFromGroupResult result = deleteGameFromGroupActivity.handleRequest(request);

		// Verify that the groupDao method was called
		verify(groupDao, times(1)).getGroup(userId, groupName);

	}
}

