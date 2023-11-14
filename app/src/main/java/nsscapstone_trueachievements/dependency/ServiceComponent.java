package nsscapstone_trueachievements.dependency;

import nsscapstone_trueachievements.activity.AddGameToGroupActivity;
import nsscapstone_trueachievements.activity.CreateGroupActivity;
import nsscapstone_trueachievements.activity.GetAllGamesActivity;
import nsscapstone_trueachievements.activity.GetAllGroupsActivity;
import nsscapstone_trueachievements.activity.GetGameActivity;
import nsscapstone_trueachievements.activity.GetGamesInGroupActivity;
import nsscapstone_trueachievements.activity.GetGroupActivity;
import nsscapstone_trueachievements.activity.GetUserStatsActivity;

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
}
