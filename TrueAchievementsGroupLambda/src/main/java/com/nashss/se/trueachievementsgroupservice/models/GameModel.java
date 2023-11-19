package com.nashss.se.trueachievementsgroupservice.models;

import java.util.List;
import java.util.Objects;

import static com.nashss.se.trueachievementsgroupservice.utils.CollectionUtils.copyToList;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class GameModel implements Comparable<GameModel> {
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
    private Integer maxGamerscoreIncludeDlc;
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

    private GameModel(String userId, String uniqueId, String gameName, String platform, String gameURL,
                      Integer achievementsWonNoDlc, Integer maxAchievementsNoDlc, Integer achievementsWonIncludeDlc,
                      Integer maxAchievementsIncludeDlc, Integer gamerScoreWonNoDlc, Integer maxGamerScoreNoDlc,
                      Integer gamerScoreWonIncludeDlc, Integer maxGamerscoreIncludeDlc, Integer trueAchievementWonNoDlc,
                      Integer maxTrueAchievementNoDlc, Integer trueAchievementWonIncludeDlc,
                      Integer maxTrueAchievementIncludeDlc, Integer myCompletionPercentage, String completionDate,
                      Integer challengesWon, Integer maxChallenges, Integer hoursPlayed, Float myRating,
                      Float siteRating, Float myRatio, Float siteRatio, String ownershipStatus, String playStatus,
                      String format, String completionEstimate, String walkthrough, List<String> gameNotes,
                      String contestStatus) {
        this.userId = userId;
        this.uniqueId = uniqueId;
        this.gameName = gameName;
        this.platform = defaultIfNull(platform, "");
        this.gameURL = defaultIfNull(gameURL, "");
        this.achievementsWonNoDlc = achievementsWonNoDlc;
        this.maxAchievementsNoDlc = maxAchievementsNoDlc;
        this.achievementsWonIncludeDlc = achievementsWonIncludeDlc;
        this.maxAchievementsIncludeDlc = maxAchievementsIncludeDlc;
        this.gamerScoreWonNoDlc = gamerScoreWonNoDlc;
        this.maxGamerScoreNoDlc = maxGamerScoreNoDlc;
        this.gamerScoreWonIncludeDlc = gamerScoreWonIncludeDlc;
        this.maxGamerscoreIncludeDlc = maxGamerscoreIncludeDlc;
        this.trueAchievementWonNoDlc = trueAchievementWonNoDlc;
        this.maxTrueAchievementNoDlc = maxTrueAchievementNoDlc;
        this.trueAchievementWonIncludeDlc = trueAchievementWonIncludeDlc;
        this.maxTrueAchievementIncludeDlc = maxTrueAchievementIncludeDlc;
        this.myCompletionPercentage = myCompletionPercentage;
        this.completionDate = defaultIfNull(completionDate, "");
        this.challengesWon = challengesWon;
        this.maxChallenges = maxChallenges;
        this.hoursPlayed = hoursPlayed;
        this.myRating = myRating;
        this.siteRating = siteRating;
        this.myRatio = myRatio;
        this.siteRatio = siteRatio;
        this.ownershipStatus = defaultIfNull(ownershipStatus, "");
        this.playStatus = defaultIfNull(playStatus, "");
        this.format = defaultIfNull(format, "");
        this.completionEstimate = defaultIfNull(completionEstimate, "");
        this.walkthrough = defaultIfNull(walkthrough, "");
        this.gameNotes = copyToList(gameNotes);
        this.contestStatus = defaultIfNull(contestStatus, "");
    }

    public String getUserId() { return userId; }
    public String getUniqueId() {
        return uniqueId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getPlatform() {
        return platform;
    }

    public String getGameURL() {
        return gameURL;
    }

    public Integer getAchievementsWonNoDlc() {
        return achievementsWonNoDlc;
    }

    public Integer getMaxAchievementsNoDlc() {
        return maxAchievementsNoDlc;
    }

    public Integer getAchievementsWonIncludeDlc() {
        return achievementsWonIncludeDlc;
    }

    public Integer getMaxAchievementsIncludeDlc() {
        return maxAchievementsIncludeDlc;
    }

    public Integer getGamerScoreWonNoDlc() {
        return gamerScoreWonNoDlc;
    }

    public Integer getMaxGamerScoreNoDlc() {
        return maxGamerScoreNoDlc;
    }

    public Integer getGamerScoreWonIncludeDlc() {
        return gamerScoreWonIncludeDlc;
    }

    public Integer getMaxGamerscoreIncludeDlc() {
        return maxGamerscoreIncludeDlc;
    }

    public Integer getTrueAchievementWonNoDlc() {
        return trueAchievementWonNoDlc;
    }

    public Integer getMaxTrueAchievementNoDlc() {
        return maxTrueAchievementNoDlc;
    }

    public Integer getTrueAchievementWonIncludeDlc() {
        return trueAchievementWonIncludeDlc;
    }

    public Integer getMaxTrueAchievementIncludeDlc() {
        return maxTrueAchievementIncludeDlc;
    }

    public Integer getMyCompletionPercentage() {
        return myCompletionPercentage;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public Integer getChallengesWon() {
        return challengesWon;
    }

    public Integer getMaxChallenges() {
        return maxChallenges;
    }

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public Float getMyRating() {
        return myRating;
    }

    public Float getSiteRating() {
        return siteRating;
    }

    public Float getMyRatio() {
        return myRatio;
    }

    public Float getSiteRatio() {
        return siteRatio;
    }

    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    public String getPlayStatus() {
        return playStatus;
    }

    public String getFormat() {
        return format;
    }

    public String getCompletionEstimate() {
        return completionEstimate;
    }

    public String getWalkthrough() {
        return walkthrough;
    }

    public List<String> getGameNotes() {
        return copyToList(gameNotes);
    }

    public String getContestStatus() {
        return contestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameModel gameModel = (GameModel) o;

        return Objects.equals(userId, gameModel.userId) &&
                Objects.equals(uniqueId, gameModel.uniqueId) &&
                Objects.equals(gameName, gameModel.gameName) &&
                Objects.equals(platform, gameModel.platform) &&
                Objects.equals(gameURL, gameModel.gameURL) &&
                Objects.equals(achievementsWonNoDlc, gameModel.achievementsWonNoDlc) &&
                Objects.equals(maxAchievementsNoDlc, gameModel.maxAchievementsNoDlc) &&
                Objects.equals(achievementsWonIncludeDlc, gameModel.achievementsWonIncludeDlc) &&
                Objects.equals(maxAchievementsIncludeDlc, gameModel.maxAchievementsIncludeDlc) &&
                Objects.equals(gamerScoreWonNoDlc, gameModel.gamerScoreWonNoDlc) &&
                Objects.equals(maxGamerScoreNoDlc, gameModel.maxGamerScoreNoDlc) &&
                Objects.equals(gamerScoreWonIncludeDlc, gameModel.gamerScoreWonIncludeDlc) &&
                Objects.equals(maxGamerscoreIncludeDlc, gameModel.maxGamerscoreIncludeDlc) &&
                Objects.equals(trueAchievementWonNoDlc, gameModel.trueAchievementWonNoDlc) &&
                Objects.equals(maxTrueAchievementNoDlc, gameModel.maxTrueAchievementNoDlc) &&
                Objects.equals(trueAchievementWonIncludeDlc, gameModel.trueAchievementWonIncludeDlc) &&
                Objects.equals(maxTrueAchievementIncludeDlc, gameModel.maxTrueAchievementIncludeDlc) &&
                Objects.equals(myCompletionPercentage, gameModel.myCompletionPercentage) &&
                Objects.equals(completionDate, gameModel.completionDate) &&
                Objects.equals(challengesWon, gameModel.challengesWon) &&
                Objects.equals(maxChallenges, gameModel.maxChallenges) &&
                Objects.equals(hoursPlayed, gameModel.hoursPlayed) &&
                Objects.equals(myRating, gameModel.myRating) &&
                Objects.equals(siteRating, gameModel.siteRating) &&
                Objects.equals(myRatio, gameModel.myRatio) &&
                Objects.equals(siteRatio, gameModel.siteRatio) &&
                Objects.equals(ownershipStatus, gameModel.ownershipStatus) &&
                Objects.equals(playStatus, gameModel.playStatus) &&
                Objects.equals(format, gameModel.format) &&
                Objects.equals(completionEstimate, gameModel.completionEstimate) &&
                Objects.equals(walkthrough, gameModel.walkthrough) &&
                Objects.equals(gameNotes, gameModel.gameNotes) &&
                Objects.equals(contestStatus, gameModel.contestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, uniqueId, gameName, platform, gameURL, achievementsWonNoDlc, maxAchievementsNoDlc,
            achievementsWonIncludeDlc, maxAchievementsIncludeDlc, gamerScoreWonNoDlc, maxGamerScoreNoDlc,
            gamerScoreWonIncludeDlc, maxGamerscoreIncludeDlc, trueAchievementWonNoDlc, maxTrueAchievementNoDlc,
            trueAchievementWonIncludeDlc, maxTrueAchievementIncludeDlc, myCompletionPercentage, completionDate,
            challengesWon, maxChallenges, hoursPlayed, myRating, siteRating, myRatio, siteRatio, ownershipStatus,
            playStatus, format, completionEstimate, walkthrough, gameNotes, contestStatus);
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int compareTo(GameModel gm) {
        if (gameName == null || gm.gameName == null) {
            if (platform.equals(gm.platform)) {
                return 0;
            }
            else if (platform.compareTo(gm.platform) > 0) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if (gameName.equals(gm.gameName)) {
            return 0;
        }
        else if (gameName.compareTo(gm.gameName) > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    public static class Builder {
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
        private Integer maxGamerscoreIncludeDlc;
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

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public Builder withUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }
        public Builder withGameName(String gameName) {
            this.gameName = gameName;
            return this;
        }
        public Builder withPlatform(String platform) {
            this.platform = platform;
            return this;
        }
        public Builder withGameURL(String gameURL) {
            this.gameURL = gameURL;
            return this;
        }
        public Builder withAchievementsWonNoDlc(Integer achievementsWonNoDlc) {
            this.achievementsWonNoDlc = achievementsWonNoDlc;
            return this;
        }
        public Builder withMaxAchievementsNoDlc(Integer maxAchievementsNoDlc) {
            this.maxAchievementsNoDlc = maxAchievementsNoDlc;
            return this;
        }
        public Builder withAchievementsWonIncludeDlc(Integer achievementsWonIncludeDlc) {
            this.achievementsWonIncludeDlc = achievementsWonIncludeDlc;
            return this;
        }
        public Builder withMaxAchievementsIncludeDlc(Integer maxAchievementsIncludeDlc) {
            this.maxAchievementsIncludeDlc = maxAchievementsIncludeDlc;
            return this;
        }
        public Builder withGamerScoreWonNoDlc(Integer gamerScoreWonNoDlc) {
            this.gamerScoreWonNoDlc = gamerScoreWonNoDlc;
            return this;
        }
        public Builder withMaxGamerScoreNoDlc(Integer maxGamerScoreNoDlc) {
            this.maxGamerScoreNoDlc = maxGamerScoreNoDlc;
            return this;
        }
        public Builder withGamerScoreWonIncludeDlc(Integer gamerScoreWonIncludeDlc) {
            this.gamerScoreWonIncludeDlc = gamerScoreWonIncludeDlc;
            return this;
        }
        public Builder withMaxGamerScoreIncludeDlc(Integer maxGamerscoreIncludeDlc) {
            this.maxGamerscoreIncludeDlc = maxGamerscoreIncludeDlc;
            return this;
        }
        public Builder withTrueAchievementWonNoDlc(Integer trueAchievementWonNoDlc) {
            this.trueAchievementWonNoDlc = trueAchievementWonNoDlc;
            return this;
        }
        public Builder withMaxTrueAchievementNoDlc(Integer maxTrueAchievementNoDlc) {
            this.maxTrueAchievementNoDlc = maxTrueAchievementNoDlc;
            return this;
        }
        public Builder withTrueAchievementWonIncludeDlc(Integer trueAchievementWonIncludeDlc) {
            this.trueAchievementWonIncludeDlc = trueAchievementWonIncludeDlc;
            return this;
        }
        public Builder withMaxTrueAchievementIncludeDlc(Integer maxTrueAchievementIncludeDlc) {
            this.maxTrueAchievementIncludeDlc = maxTrueAchievementIncludeDlc;
            return this;
        }
        public Builder withMyCompletionPercentage(Integer myCompletionPercentage) {
            this.myCompletionPercentage = myCompletionPercentage;
            return this;
        }
        public Builder withCompletionDate(String completionDate) {
            this.completionDate = completionDate;
            return this;
        }
        public Builder withChallengesWon(Integer challengesWon) {
            this.challengesWon = challengesWon;
            return this;
        }
        public Builder withMaxChallenges(Integer maxChallenges) {
            this.maxChallenges = maxChallenges;
            return this;
        }
        public Builder withHoursPlayed(Integer hoursPlayed) {
            this.hoursPlayed = hoursPlayed;
            return this;
        }
        public Builder withMyRating(Float myRating) {
            this.myRating = myRating;
            return this;
        }
        public Builder withSiteRating(Float siteRating) {
            this.siteRating = siteRating;
            return this;
        }
        public Builder withMyRatio(Float myRatio) {
            this.myRatio = myRatio;
            return this;
        }
        public Builder withSiteRatio(Float siteRatio) {
            this.siteRatio = siteRatio;
            return this;
        }
        public Builder withOwnershipStatus(String ownershipStatus) {
            this.ownershipStatus = ownershipStatus;
            return this;
        }
        public Builder withPlayStatus(String playStatus) {
            this.playStatus = playStatus;
            return this;
        }
        public Builder withFormat(String format) {
            this.format = format;
            return this;
        }
        public Builder withCompletionEstimate(String completionEstimate) {
            this.completionEstimate = completionEstimate;
            return this;
        }
        public Builder withWalkthrough(String walkthrough) {
            this.walkthrough = walkthrough;
            return this;
        }
        public Builder withGameNotes(List<String> gameNotes) {
            this.gameNotes = gameNotes;
            return this;
        }
        public Builder withContestStatus(String contestStatus) {
            this.contestStatus = contestStatus;
            return this;
        }
        public GameModel build() {
            return new GameModel(userId, uniqueId, gameName, platform, gameURL, achievementsWonNoDlc, maxAchievementsNoDlc,
                achievementsWonIncludeDlc, maxAchievementsIncludeDlc, gamerScoreWonNoDlc, maxGamerScoreNoDlc,
                gamerScoreWonIncludeDlc, maxGamerscoreIncludeDlc, trueAchievementWonNoDlc, maxTrueAchievementNoDlc,
                trueAchievementWonIncludeDlc, maxTrueAchievementIncludeDlc, myCompletionPercentage, completionDate,
                challengesWon, maxChallenges, hoursPlayed, myRating, siteRating, myRatio, siteRatio, ownershipStatus,
                playStatus, format, completionEstimate, walkthrough, gameNotes, contestStatus);
        }
    }
}
