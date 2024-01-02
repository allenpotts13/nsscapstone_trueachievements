package com.nashss.se.trueachievementsgroupservice.utils;

public class MetricsUtil {

    /**
     * Returns the metric name for the provided activity name and metric type.
     * <p>
     * The metric name is the concatenation of the activity name and metric type, separated by a period.
     * <p>
     * For example, if the activity name is "GetTrueAchievementsGroup" and the metric type is "Duration",
     * the metric name will be "GetTrueAchievementsGroup.Duration".
     *
     * @param activityName activity name
     * @param metricType metric type
     * @return metric name
     */

    public static String getMetricName(String activityName, String metricType) {
        return activityName + "." + metricType;
    }
}
