package com.nashss.se.trueachievementsgroupservice.activity.results;

public class GetUserStatsResult {

    private final Integer gamerScoreWonIncludeDlc;
    private final Integer trueAchievementWonIncludeDlc;
    private final Integer myCompletionPercentage;
    private final String errorMessage;


    private GetUserStatsResult(Integer gamerScoreWonIncludeDlc, Integer trueAchievementWonIncludeDlc,
                               Integer myCompletionPercentage, String errorMessage) {
        this.gamerScoreWonIncludeDlc = gamerScoreWonIncludeDlc;
        this.trueAchievementWonIncludeDlc = trueAchievementWonIncludeDlc;
        this.myCompletionPercentage = myCompletionPercentage;
        this.errorMessage = errorMessage;
    }

    public Integer getGamerScoreWonIncludeDlc() {
        return gamerScoreWonIncludeDlc;
    }

    public Integer getTrueAchievementWonIncludeDlc() {
        return trueAchievementWonIncludeDlc;
    }

    public Integer getMyCompletionPercentage() {
        return myCompletionPercentage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "GetUserStatsResult{" +
                "GamerScoreWonIncludeDlc=" + gamerScoreWonIncludeDlc +
                ", TrueAchievementWonIncludeDlc=" + trueAchievementWonIncludeDlc +
                ", MyCompletionPercentage=" + myCompletionPercentage +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer gamerScoreWonIncludeDlc;
        private Integer trueAchievementWonIncludeDlc;
        private Integer myCompletionPercentage;
        private String errorMessage;

        public Builder withGamerScoreWonIncludeDlc(Integer gamerScoreWonIncludeDlc) {
            this.gamerScoreWonIncludeDlc = gamerScoreWonIncludeDlc;
            return this;
        }

        public Builder withTrueAchievementWonIncludeDlc(Integer trueAchievementWonIncludeDlc) {
            this.trueAchievementWonIncludeDlc = trueAchievementWonIncludeDlc;
            return this;
        }

        public Builder withMyCompletionPercentage(Integer myCompletionPercentage) {
            this.myCompletionPercentage = myCompletionPercentage;
            return this;
        }

        public Builder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public GetUserStatsResult build() {
            return new GetUserStatsResult(gamerScoreWonIncludeDlc, trueAchievementWonIncludeDlc, myCompletionPercentage, errorMessage);
        }
    }
}
