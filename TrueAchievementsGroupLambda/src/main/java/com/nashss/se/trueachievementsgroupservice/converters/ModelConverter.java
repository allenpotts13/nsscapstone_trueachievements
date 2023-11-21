package com.nashss.se.trueachievementsgroupservice.converters;

import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Game;
import com.nashss.se.trueachievementsgroupservice.dynamodb.models.Group;
import com.nashss.se.trueachievementsgroupservice.models.GameModel;
import com.nashss.se.trueachievementsgroupservice.models.GroupModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.nashss.se.trueachievementsgroupservice.utils.CollectionUtils.copyToList;
import static com.nashss.se.trueachievementsgroupservice.utils.CollectionUtils.copyToSet;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link Group} into a {@link GroupModel} representation.
     *
     * @param group the group to convert
     * @return the converted playlist
     */
    public GroupModel toGroupModel(Group group) {
        List<Game> gamesList = null;
        if (group.getGamesList() != null) {
            gamesList = new ArrayList<>(group.getGamesList());
        }

        return GroupModel.builder()
                .withUserId(group.getUserId())
                .withGroupName(group.getGroupName())
                .withGamesList(copyToSet(group.getGamesList()))
                .build();
    }

    /**
     * Converts a provided Game into a GameModel representation.
     *
     * @param game the Game to convert to GameModel
     * @return the converted GameModel with fields mapped from game
     */
    public GameModel toGameModel(Game game) {
        return GameModel.builder()
            .withUserId(game.getUserId())
            .withUniqueId(game.getUniqueId())
            .withGameName(game.getGameName())
            .withPlatform(game.getPlatform())
            .withGameURL(game.getGameURL())
            .withAchievementsWonNoDlc(game.getAchievementsWonNoDlc())
            .withMaxAchievementsNoDlc(game.getMaxAchievementsNoDlc())
            .withAchievementsWonIncludeDlc(game.getAchievementsWonIncludeDlc())
            .withMaxAchievementsIncludeDlc(game.getMaxAchievementsIncludeDlc())
            .withGamerScoreWonNoDlc(game.getGamerScoreWonNoDlc())
            .withMaxGamerScoreNoDlc(game.getMaxGamerScoreNoDlc())
            .withGamerScoreWonIncludeDlc(game.getGamerScoreWonIncludeDlc())
            .withMaxGamerScoreIncludeDlc(game.getMaxGamerScoreIncludeDlc())
            .withTrueAchievementWonNoDlc(game.getTrueAchievementWonNoDlc())
            .withMaxTrueAchievementNoDlc(game.getMaxTrueAchievementNoDlc())
            .withTrueAchievementWonIncludeDlc(game.getTrueAchievementWonIncludeDlc())
            .withMaxTrueAchievementIncludeDlc(game.getMaxTrueAchievementIncludeDlc())
            .withMyCompletionPercentage(game.getMyCompletionPercentage())
            .withCompletionDate(game.getCompletionDate())
            .withChallengesWon(game.getChallengesWon())
            .withMaxChallenges(game.getMaxChallenges())
            .withHoursPlayed(game.getHoursPlayed())
            .withMyRating(game.getMyRating())
            .withSiteRating(game.getSiteRating())
            .withMyRatio(game.getMyRatio())
            .withSiteRatio(game.getSiteRatio())
            .withOwnershipStatus(game.getOwnershipStatus())
            .withPlayStatus(game.getPlayStatus())
            .withFormat(game.getFormat())
            .withCompletionEstimate(game.getCompletionEstimate())
            .withWalkthrough(game.getWalkthrough())
            .withGameNotes(copyToList(game.getGameNotes()))
            .withContestStatus(game.getContestStatus())
            .build();
    }

    /**
     * Converts a list of Games to a list of GameModels.
     *
     * @param games The Games to convert to GameModels
     * @return The converted list of GameModels
     */
    public List<GameModel> toGameModelList(Set<Game> games) {
        List<GameModel> gameModels = new ArrayList<>();

        for (Game game : games) {
            gameModels.add(toGameModel(game));
        }

        return gameModels;
    }

    /**
     * Converts a list of Groups to a list of GroupModels.
     *
     * @param groups The Groups to convert to GroupModels
     * @return The converted list of GroupModels
     */
    public List<GroupModel> toGroupModelList(List<Group> groups) {
        List<GroupModel> groupModels = new ArrayList<>();

        for (Group group : groups) {
            groupModels.add(toGroupModel(group));
        }

        return groupModels;
    }
}

