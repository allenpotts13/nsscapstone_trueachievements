package com.nashss.se.trueachievementsgroupservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the games table.
 */
@DynamoDBTable(tableName = "games")
public class Game {
    private String userId;
    private String uniqueId;
    private String gameName;
    private String platform;
    private String gameURL;
    private Integer achievementsWonNoDlc;
    private Integer maxAchievementsNoDlc;
    private Integer achievementsWonIncludeDlc;
    private Integer maxAchievementsIncludeDlc;
    private Integer gamerScoreWonNoDlc;
    private Integer maxGamerScoreNoDlc;
    private Integer gamerScoreWonIncludeDlc;
    private Integer maxGamerScoreIncludeDlc;
    private Integer trueAchievementWonNoDlc;
    private Integer maxTrueAchievementNoDlc;
    private Integer trueAchievementWonIncludeDlc;
    private Integer maxTrueAchievementIncludeDlc;
    private Integer myCompletionPercentage;
    private String completionDate;
    private Integer challengesWon;
    private Integer maxChallenges;
    private Integer hoursPlayed;
    private Float myRating;
    private Float siteRating;
    private Float myRatio;
    private Float siteRatio;
    private String ownershipStatus;
    private String playStatus;
    private String format;
    private String completionEstimate;
    private String walkthrough;
    private List<String> gameNotes;
    private String contestStatus;
    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @DynamoDBRangeKey(attributeName = "uniqueId")
    public String getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    @DynamoDBAttribute(attributeName = "gameName")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    @DynamoDBAttribute(attributeName = "platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform; }
    @DynamoDBAttribute(attributeName = "gameURL")
    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }
    @DynamoDBAttribute(attributeName = "achievementsWonNoDlc")
    public Integer getAchievementsWonNoDlc() {
        return achievementsWonNoDlc;
    }

    public void setAchievementsWonNoDlc(Integer achievementsWonNoDlc) {
        this.achievementsWonNoDlc = achievementsWonNoDlc;
    }
    @DynamoDBAttribute(attributeName = "maxAchievementsNoDlc")
    public Integer getMaxAchievementsNoDlc() {
        return maxAchievementsNoDlc;
    }

    public void setMaxAchievementsNoDlc(Integer maxAchievementsNoDlc) {
        this.maxAchievementsNoDlc = maxAchievementsNoDlc;
    }
    @DynamoDBAttribute(attributeName = "achievementsWonIncludeDlc")
    public Integer getAchievementsWonIncludeDlc() {
        return achievementsWonIncludeDlc;
    }

    public void setAchievementsWonIncludeDlc(Integer achievementsWonIncludeDlc) {
        this.achievementsWonIncludeDlc = achievementsWonIncludeDlc;
    }
    @DynamoDBAttribute(attributeName = "maxAchievementsIncludeDlc")
    public Integer getMaxAchievementsIncludeDlc() {
        return maxAchievementsIncludeDlc;
    }

    public void setMaxAchievementsIncludeDlc(Integer maxAchievementsIncludeDlc) {
        this.maxAchievementsIncludeDlc = maxAchievementsIncludeDlc;
    }
    @DynamoDBAttribute(attributeName = "gamerScoreWonNoDlc")
    public Integer getGamerScoreWonNoDlc() {
        return gamerScoreWonNoDlc;
    }

    public void setGamerScoreWonNoDlc(Integer gamerScoreWonNoDlc) {
        this.gamerScoreWonNoDlc = gamerScoreWonNoDlc;
    }
    @DynamoDBAttribute(attributeName = "maxGamerScoreNoDlc")
    public Integer getMaxGamerScoreNoDlc() {
        return maxGamerScoreNoDlc;
    }

    public void setMaxGamerScoreNoDlc(Integer maxGamerScoreNoDlc) {
        this.maxGamerScoreNoDlc = maxGamerScoreNoDlc;
    }
    @DynamoDBAttribute(attributeName = "gamerScoreWonIncludeDlc")
    public Integer getGamerScoreWonIncludeDlc() {
        return gamerScoreWonIncludeDlc;
    }

    public void setGamerScoreWonIncludeDlc(Integer gamerScoreWonIncludeDlc) {
        this.gamerScoreWonIncludeDlc = gamerScoreWonIncludeDlc;
    }
    @DynamoDBAttribute(attributeName = "maxGamerscoreIncludeDlc")
    public Integer getMaxGamerScoreIncludeDlc() {
        return maxGamerScoreIncludeDlc;
    }

    public void setMaxGamerScoreIncludeDlc(Integer maxGamerScoreIncludeDlc) {
        this.maxGamerScoreIncludeDlc = maxGamerScoreIncludeDlc;
    }
    @DynamoDBAttribute(attributeName = "trueAchievementWonNoDlc")
    public Integer getTrueAchievementWonNoDlc() {
        return trueAchievementWonNoDlc;
    }

    public void setTrueAchievementWonNoDlc(Integer trueAchievementWonNoDlc) {
        this.trueAchievementWonNoDlc = trueAchievementWonNoDlc;
    }
    @DynamoDBAttribute(attributeName = "maxTrueAchievementNoDlc")
    public Integer getMaxTrueAchievementNoDlc() {
        return maxTrueAchievementNoDlc;
    }

    public void setMaxTrueAchievementNoDlc(Integer maxTrueAchievementNoDlc) {
        this.maxTrueAchievementNoDlc = maxTrueAchievementNoDlc;
    }
    @DynamoDBAttribute(attributeName = "trueAchievementWonIncludeDlc")
    public Integer getTrueAchievementWonIncludeDlc() {
        return trueAchievementWonIncludeDlc;
    }

    public void setTrueAchievementWonIncludeDlc(Integer trueAchievementWonIncludeDlc) {
        this.trueAchievementWonIncludeDlc = trueAchievementWonIncludeDlc;
    }
    @DynamoDBAttribute(attributeName = "maxTrueAchievementIncludeDlc")
    public Integer getMaxTrueAchievementIncludeDlc() {
        return maxTrueAchievementIncludeDlc;
    }

    public void setMaxTrueAchievementIncludeDlc(Integer maxTrueAchievementIncludeDlc) {
        this.maxTrueAchievementIncludeDlc = maxTrueAchievementIncludeDlc;
    }
    @DynamoDBAttribute(attributeName = "myCompletionPercentage")
    public Integer getMyCompletionPercentage() {
        return myCompletionPercentage;
    }

    public void setMyCompletionPercentage(Integer myCompletionPercentage) {
        this.myCompletionPercentage = myCompletionPercentage;
    }
    @DynamoDBAttribute(attributeName = "completionDate")
    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }
    @DynamoDBAttribute(attributeName = "challengesWon")
    public Integer getChallengesWon() {
        return challengesWon;
    }

    public void setChallengesWon(Integer challengesWon) {
        this.challengesWon = challengesWon;
    }
    @DynamoDBAttribute(attributeName = "maxChallenges")
    public Integer getMaxChallenges() {
        return maxChallenges;
    }

    public void setMaxChallenges(Integer maxChallenges) {
        this.maxChallenges = maxChallenges;
    }
    @DynamoDBAttribute(attributeName = "hoursPlayed")
    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }
    @DynamoDBAttribute(attributeName = "myRating")
    public Float getMyRating() {
        return myRating;
    }

    public void setMyRating(Float myRating) {
        this.myRating = myRating;
    }
    @DynamoDBAttribute(attributeName = "siteRating")
    public Float getSiteRating() {
        return siteRating;
    }

    public void setSiteRating(Float siteRating) {
        this.siteRating = siteRating;
    }
    @DynamoDBAttribute(attributeName = "myRatio")
    public Float getMyRatio() {
        return myRatio;
    }

    public void setMyRatio(Float myRatio) {
        this.myRatio = myRatio;
    }
    @DynamoDBAttribute(attributeName = "siteRatio")
    public Float getSiteRatio() {
        return siteRatio; }

    public void setSiteRatio(Float siteRatio) {
        this.siteRatio = siteRatio; }
    @DynamoDBAttribute(attributeName = "ownershipStatus")
    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    public void setOwnershipStatus(String ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
    }
    @DynamoDBAttribute(attributeName = "playStatus")
    public String getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus;
    }
    @DynamoDBAttribute(attributeName = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    @DynamoDBAttribute(attributeName = "completionEstimate")
    public String getCompletionEstimate() {
        return completionEstimate;
    }

    public void setCompletionEstimate(String completionEstimate) {
        this.completionEstimate = completionEstimate;
    }
    @DynamoDBAttribute(attributeName = "walkthrough")
    public String getWalkthrough() {
        return walkthrough;
    }

    public void setWalkthrough(String walkthrough) {
        this.walkthrough = walkthrough;
    }
    @DynamoDBAttribute(attributeName = "gameNotes")
    public List<String> getGameNotes() {
        return gameNotes;
    }

    public void setGameNotes(List<String> gameNotes) {
        this.gameNotes = gameNotes;
    }
    @DynamoDBAttribute(attributeName = "contestStatus")
    public String getContestStatus() {
        return contestStatus;
    }

    public void setContestStatus(String contestStatus) {
        this.contestStatus = contestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(userId, game.userId) &&
            Objects.equals(uniqueId, game.uniqueId) &&
            Objects.equals(gameName, game.gameName) &&
            Objects.equals(platform, game.platform) &&
            Objects.equals(gameURL, game.gameURL) &&
            Objects.equals(achievementsWonNoDlc, game.achievementsWonNoDlc) &&
            Objects.equals(maxAchievementsNoDlc, game.maxAchievementsNoDlc) &&
            Objects.equals(achievementsWonIncludeDlc, game.achievementsWonIncludeDlc) &&
            Objects.equals(maxAchievementsIncludeDlc, game.maxAchievementsIncludeDlc) &&
            Objects.equals(gamerScoreWonNoDlc, game.gamerScoreWonNoDlc) &&
            Objects.equals(maxGamerScoreNoDlc, game.maxGamerScoreNoDlc) &&
            Objects.equals(gamerScoreWonIncludeDlc, game.gamerScoreWonIncludeDlc) &&
            Objects.equals(maxGamerScoreIncludeDlc, game.maxGamerScoreIncludeDlc) &&
            Objects.equals(trueAchievementWonNoDlc, game.trueAchievementWonNoDlc) &&
            Objects.equals(maxTrueAchievementNoDlc, game.maxTrueAchievementNoDlc) &&
            Objects.equals(trueAchievementWonIncludeDlc, game.trueAchievementWonIncludeDlc) &&
            Objects.equals(maxTrueAchievementIncludeDlc, game.maxTrueAchievementIncludeDlc) &&
            Objects.equals(myCompletionPercentage, game.myCompletionPercentage) &&
            Objects.equals(completionDate, game.completionDate) &&
            Objects.equals(challengesWon, game.challengesWon) &&
            Objects.equals(maxChallenges, game.maxChallenges) &&
            Objects.equals(hoursPlayed, game.hoursPlayed) &&
            Objects.equals(myRating, game.myRating) &&
            Objects.equals(siteRating, game.siteRating) &&
            Objects.equals(myRatio, game.myRatio) &&
            Objects.equals(siteRatio, game.siteRatio) &&
            Objects.equals(ownershipStatus, game.ownershipStatus) &&
            Objects.equals(playStatus, game.playStatus) &&
            Objects.equals(format, game.format) &&
            Objects.equals(completionEstimate, game.completionEstimate) &&
            Objects.equals(walkthrough, game.walkthrough) &&
            Objects.equals(gameNotes, game.gameNotes) &&
            Objects.equals(contestStatus, game.contestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, uniqueId, gameName, platform, gameURL, achievementsWonNoDlc, maxAchievementsNoDlc,
            achievementsWonIncludeDlc, maxAchievementsIncludeDlc, gamerScoreWonNoDlc, maxGamerScoreNoDlc,
            gamerScoreWonIncludeDlc, maxGamerScoreIncludeDlc, trueAchievementWonNoDlc, maxTrueAchievementNoDlc,
            trueAchievementWonIncludeDlc, maxTrueAchievementIncludeDlc, myCompletionPercentage, completionDate,
            challengesWon, maxChallenges, hoursPlayed, myRating, siteRating, myRatio, siteRatio, ownershipStatus,
            playStatus, format, completionEstimate, walkthrough, gameNotes, contestStatus);
    }
}
