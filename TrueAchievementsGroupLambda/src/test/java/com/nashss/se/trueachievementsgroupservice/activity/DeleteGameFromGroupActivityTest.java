package com.nashss.se.trueachievementsgroupservice.activity;

import com.nashss.se.trueachievementsgroupservice.activity.requests.DeleteGameFromGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.DeleteGameFromGroupResult;
import com.nashss.se.trueachievementsgroupservice.converters.ModelConverter;
import com.nashss.se.trueachievementsgroupservice.dynamodb.GroupDao;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.exceptions.GroupNotFoundException;
import com.nashss.se.trueachievementsgroupservice.metrics.MetricsPublisher;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteGameFromGroupActivityTest {

	@Mock
	private GroupDao groupDao;

	@Mock
	private MetricsPublisher metricsPublisher;

	@InjectMocks
	private DeleteGameFromGroupActivity deleteGameFromGroupActivity;

	@BeforeEach
	void setUp() {
		initMocks(this);
		deleteGameFromGroupActivity = new DeleteGameFromGroupActivity(groupDao, metricsPublisher);
	}

	@Test
	public void handleRequest_validRequest_deletesGameFromGroup() {
		// Mock data
		String userId = "user123";
		String groupName = "Favorite Games";
		String uniqueId = "uniqueId123";

		// Mocking the group retrieved from the DAO
		Group mockGroup = new Group();
		mockGroup.setUserId(userId);
		mockGroup.setGroupName(groupName);
		mockGroup.setGamesList(new HashSet<>());

		// Mocking the group retrieved after deletion
		Group groupAfterDeletion = new Group();
		groupAfterDeletion.setUserId(userId);
		groupAfterDeletion.setGroupName(groupName);
		groupAfterDeletion.setGamesList(new HashSet<>());

		// Mocking behavior of the groupDao
		when(groupDao.getGroup(userId, groupName)).thenReturn(mockGroup);
		when(groupDao.deleteGameFromGroup(userId, groupName, uniqueId)).thenReturn(groupAfterDeletion);

		// Execute the method
		DeleteGameFromGroupRequest request = DeleteGameFromGroupRequest.builder()
			.withUserId(userId)
			.withGroupName(groupName)
			.withUniqueId(uniqueId)
			.build();

		DeleteGameFromGroupResult result = deleteGameFromGroupActivity.handleRequest(request);

		// Verify that the groupDao methods were called
		verify(groupDao, times(1)).getGroup(userId, groupName);
		verify(groupDao, times(1)).deleteGameFromGroup(userId, groupName, uniqueId);

		// Check the result
		ModelConverter modelConverter = new ModelConverter();
		GroupModel expectedGroupModel = modelConverter.toGroupModel(groupAfterDeletion);
		assertEquals(expectedGroupModel, result.getGroup());
		assertEquals(0, result.getGroup().getGamesList().size());
		assertNull(result.getError());
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

		// Check the result
		assertNull(result.getGroup());
		assertEquals("Group not found", result.getError());
	}
}

