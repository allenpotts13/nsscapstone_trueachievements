package nsscapstone_trueachievements.dynamodb.models;

import java.util.List;
import java.util.Objects;

public class Game {

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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPlatform() {
        return platform;
    }

    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }

    public Integer getAchievementsWonNoDlc() {
        return achievementsWonNoDlc;
    }

    public void setAchievementsWonNoDlc(Integer achievementsWonNoDlc) {
        this.achievementsWonNoDlc = achievementsWonNoDlc;
    }

    public Integer getMaxAchievementsNoDlc() {
        return maxAchievementsNoDlc;
    }

    public void setMaxAchievementsNoDlc(Integer maxAchievementsNoDlc) {
        this.maxAchievementsNoDlc = maxAchievementsNoDlc;
    }

    public Integer getAchievementsWonIncludeDlc() {
        return achievementsWonIncludeDlc;
    }

    public void setAchievementsWonIncludeDlc(Integer achievementsWonIncludeDlc) {
        this.achievementsWonIncludeDlc = achievementsWonIncludeDlc;
    }

    public Integer getMaxAchievementsIncludeDlc() {
        return maxAchievementsIncludeDlc;
    }

    public void setMaxAchievementsIncludeDlc(Integer maxAchievementsIncludeDlc) {
        this.maxAchievementsIncludeDlc = maxAchievementsIncludeDlc;
    }

    public Integer getGamerScoreWonNoDlc() {
        return gamerScoreWonNoDlc;
    }

    public void setGamerScoreWonNoDlc(Integer gamerScoreWonNoDlc) {
        this.gamerScoreWonNoDlc = gamerScoreWonNoDlc;
    }

    public Integer getMaxGamerScoreNoDlc() {
        return maxGamerScoreNoDlc;
    }

    public void setMaxGamerScoreNoDlc(Integer maxGamerScoreNoDlc) {
        this.maxGamerScoreNoDlc = maxGamerScoreNoDlc;
    }

    public Integer getGamerScoreWonIncludeDlc() {
        return gamerScoreWonIncludeDlc;
    }

    public void setGamerScoreWonIncludeDlc(Integer gamerScoreWonIncludeDlc) {
        this.gamerScoreWonIncludeDlc = gamerScoreWonIncludeDlc;
    }

    public Integer getMaxGamerscoreIncludeDlc() {
        return maxGamerscoreIncludeDlc;
    }

    public void setMaxGamerscoreIncludeDlc(Integer maxGamerscoreIncludeDlc) {
        this.maxGamerscoreIncludeDlc = maxGamerscoreIncludeDlc;
    }

    public Integer getTrueAchievementWonNoDlc() {
        return trueAchievementWonNoDlc;
    }

    public void setTrueAchievementWonNoDlc(Integer trueAchievementWonNoDlc) {
        this.trueAchievementWonNoDlc = trueAchievementWonNoDlc;
    }

    public Integer getMaxTrueAchievementNoDlc() {
        return maxTrueAchievementNoDlc;
    }

    public void setMaxTrueAchievementNoDlc(Integer maxTrueAchievementNoDlc) {
        this.maxTrueAchievementNoDlc = maxTrueAchievementNoDlc;
    }

    public Integer getTrueAchievementWonIncludeDlc() {
        return trueAchievementWonIncludeDlc;
    }

    public void setTrueAchievementWonIncludeDlc(Integer trueAchievementWonIncludeDlc) {
        this.trueAchievementWonIncludeDlc = trueAchievementWonIncludeDlc;
    }

    public Integer getMaxTrueAchievementIncludeDlc() {
        return maxTrueAchievementIncludeDlc;
    }

    public void setMaxTrueAchievementIncludeDlc(Integer maxTrueAchievementIncludeDlc) {
        this.maxTrueAchievementIncludeDlc = maxTrueAchievementIncludeDlc;
    }

    public Integer getMyCompletionPercentage() {
        return myCompletionPercentage;
    }

    public void setMyCompletionPercentage(Integer myCompletionPercentage) {
        this.myCompletionPercentage = myCompletionPercentage;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getChallengesWon() {
        return challengesWon;
    }

    public void setChallengesWon(Integer challengesWon) {
        this.challengesWon = challengesWon;
    }

    public Integer getMaxChallenges() {
        return maxChallenges;
    }

    public void setMaxChallenges(Integer maxChallenges) {
        this.maxChallenges = maxChallenges;
    }

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public Float getMyRating() {
        return myRating;
    }

    public void setMyRating(Float myRating) {
        this.myRating = myRating;
    }

    public Float getSiteRating() {
        return siteRating;
    }

    public void setSiteRating(Float siteRating) {
        this.siteRating = siteRating;
    }

    public Float getMyRatio() {
        return myRatio;
    }

    public void setMyRatio(Float myRatio) {
        this.myRatio = myRatio;
    }

    public Float getSiteRatio() {
        return siteRatio;
    }

    public void setSiteRatio(Float siteRatio) {
        this.siteRatio = siteRatio;
    }

    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    public void setOwnershipStatus(String ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
    }

    public String getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCompletionEstimate() {
        return completionEstimate;
    }

    public void setCompletionEstimate(String completionEstimate) {
        this.completionEstimate = completionEstimate;
    }

    public String getWalkthrough() {
        return walkthrough;
    }

    public void setWalkthrough(String walkthrough) {
        this.walkthrough = walkthrough;
    }

    public List<String> getGameNotes() {
        return gameNotes;
    }

    public void setGameNotes(List<String> gameNotes) {
        this.gameNotes = gameNotes;
    }

    public String getContestStatus() {
        return contestStatus;
    }

    public void setContestStatus(String contestStatus) {
        this.contestStatus = contestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(uniqueId, game.uniqueId) && Objects.equals(gameName, game.gameName)
            && Objects.equals(platform, game.platform) && Objects.equals(gameURL, game.gameURL)
            && Objects.equals(achievementsWonNoDlc, game.achievementsWonNoDlc)
            && Objects.equals(maxAchievementsNoDlc, game.maxAchievementsNoDlc)
            && Objects.equals(achievementsWonIncludeDlc, game.achievementsWonIncludeDlc)
            && Objects.equals(maxAchievementsIncludeDlc, game.maxAchievementsIncludeDlc)
            && Objects.equals(gamerScoreWonNoDlc, game.gamerScoreWonNoDlc)
            && Objects.equals(maxGamerScoreNoDlc, game.maxGamerScoreNoDlc)
            && Objects.equals(gamerScoreWonIncludeDlc, game.gamerScoreWonIncludeDlc)
            && Objects.equals(maxGamerscoreIncludeDlc, game.maxGamerscoreIncludeDlc)
            && Objects.equals(trueAchievementWonNoDlc, game.trueAchievementWonNoDlc)
            && Objects.equals(maxTrueAchievementNoDlc, game.maxTrueAchievementNoDlc)
            && Objects.equals(trueAchievementWonIncludeDlc, game.trueAchievementWonIncludeDlc)
            && Objects.equals(maxTrueAchievementIncludeDlc, game.maxTrueAchievementIncludeDlc)
            && Objects.equals(myCompletionPercentage, game.myCompletionPercentage)
            && Objects.equals(completionDate, game.completionDate) && Objects.equals(challengesWon, game.challengesWon)
            && Objects.equals(maxChallenges, game.maxChallenges) && Objects.equals(hoursPlayed, game.hoursPlayed)
            && Objects.equals(myRating, game.myRating) && Objects.equals(siteRating, game.siteRating)
            && Objects.equals(myRatio, game.myRatio) && Objects.equals(siteRatio, game.siteRatio)
            && Objects.equals(ownershipStatus, game.ownershipStatus) && Objects.equals(playStatus, game.playStatus)
            && Objects.equals(format, game.format) && Objects.equals(completionEstimate, game.completionEstimate)
            && Objects.equals(walkthrough, game.walkthrough) && Objects.equals(gameNotes, game.gameNotes)
            && Objects.equals(contestStatus, game.contestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, gameName, platform, gameURL, achievementsWonNoDlc, maxAchievementsNoDlc,
            achievementsWonIncludeDlc, maxAchievementsIncludeDlc, gamerScoreWonNoDlc, maxGamerScoreNoDlc,
            gamerScoreWonIncludeDlc, maxGamerscoreIncludeDlc, trueAchievementWonNoDlc, maxTrueAchievementNoDlc,
            trueAchievementWonIncludeDlc, maxTrueAchievementIncludeDlc, myCompletionPercentage, completionDate,
            challengesWon, maxChallenges, hoursPlayed, myRating, siteRating, myRatio, siteRatio, ownershipStatus,
            playStatus, format, completionEstimate, walkthrough, gameNotes, contestStatus);
    }
}
