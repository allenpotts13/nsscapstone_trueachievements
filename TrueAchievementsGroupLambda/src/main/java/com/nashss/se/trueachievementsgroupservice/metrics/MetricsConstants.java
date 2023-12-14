package com.nashss.se.trueachievementsgroupservice.metrics;

/**
 * Constant values for use with metrics.
 */
public class MetricsConstants {
    public static final String GETGROUP_GROUPNOTFOUND_COUNT = "GetGroup.GroupNotFoundException.Count";
    public static final String SERVICE = "Service";
    public static final String SERVICE_NAME = "TrueAchievements Group Service";
    public static final String NAMESPACE_NAME = "U3/MySocialNetwork";
    public static final String GETGAME_GAMENOTFOUND_COUNT = "GetGame.GameNotFoundException.Count";

    //Metrics for the activity duration, start time, and end time
    public static final String ADD_GAME_TO_GROUP_ACTIVITY = "AddGameToGroupActivity.handleRequest";
    public static final String CREATE_GROUP_ACTIVITY = "CreateGroupActivity.handleRequest";
    public static final String DELETE_GAME_FROM_GROUP_ACTIVITY = "DeleteGameFromGroupActivity.handleRequest";
    public static final String GET_ALL_GAMES_ACTIVITY = "GetAllGamesActivity.handleRequest";
    public static final String GET_ALL_GROUPS_ACTIVITY = "GetAllGroupsActivity.handleRequest";
    public static final String GET_GAME_ACTIVITY = "GetGameActivity.handleRequest";
    public static final String GET_GAMES_IN_GROUP_ACTIVITY = "GetGamesInGroupActivity.handleRequest";
    public static final String GET_GROUP_ACTIVITY = "GetGroupActivity.handleRequest";
    public static final String GET_USER_STATS_ACTIVITY = "GetUserStatsActivity.handleRequest";

}
