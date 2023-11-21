package com.nashss.se.trueachievementsgroupservice.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class TrueAchievementGroupServiceUtils {

    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");

    private TrueAchievementGroupServiceUtils() {
    }

    public static boolean isValidString(String stringToValidate) {
        return StringUtils.isNotBlank(stringToValidate) && !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
    }
}
