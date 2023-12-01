package com.nashss.se.trueachievementsgroupservice.dependency;

import com.nashss.se.trueachievementsgroupservice.activity.AddGameToGroupActivity;
import com.nashss.se.trueachievementsgroupservice.activity.CreateGroupActivity;
import com.nashss.se.trueachievementsgroupservice.activity.DeleteGameFromGroupActivity;
import com.nashss.se.trueachievementsgroupservice.activity.GetAllGamesActivity;
import com.nashss.se.trueachievementsgroupservice.activity.GetAllGroupsActivity;
import com.nashss.se.trueachievementsgroupservice.activity.GetGameActivity;
import com.nashss.se.trueachievementsgroupservice.activity.GetGamesInGroupActivity;
import com.nashss.se.trueachievementsgroupservice.activity.GetGroupActivity;
import com.nashss.se.trueachievementsgroupservice.activity.GetUserStatsActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in TrueAchievements Group Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddGameToGroupActivity
     */
    AddGameToGroupActivity provideAddGameToGroupActivity();

    /**
     * Provides the relevant activity.
     * @return CreateGroupActivity
     */
    CreateGroupActivity provideCreateGroupActivity();
    /**
     * Provides the relevant activity.
     * @return GetAllGamesActivity
     */
    GetAllGamesActivity provideGetAllGamesActivity();
    /**
     * Provides the relevant activity.
     * @return GetAllGroupsActivity
     */
    GetAllGroupsActivity provideGetAllGroupsActivity();
    /**
     * Provides the relevant activity.
     * @return GetGameActivity
     */
    GetGameActivity provideGetGameActivity();

    /**
     * Provides the relevant activity.
     * @return GetGamesInGroupActivity
     */
    GetGamesInGroupActivity provideGetGamesInGroupActivity();
    /**
     * Provides the relevant activity.
     * @return GetGroupActivity
     */
    GetGroupActivity provideGetGroupActivity();

    /**
     * Provides the relevant activity.
     * @return GetUserStatsActivity
     */
    GetUserStatsActivity provideGetUserStatsActivity();

    /**
     * Provides the relevant activity.
     * @return DeleteGameFromGroupActivity
     */
    DeleteGameFromGroupActivity provideDeleteGameFromGroupActivity();
}
