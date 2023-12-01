package com.nashss.se.trueachievementsgroupservice.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class TrueAchievementGroupServiceUtils {

    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");

    private TrueAchievementGroupServiceUtils() {
    }

    /**
     * Checks if the provided string is valid.
     * <p>
     * A string is valid if it is not blank and does not contain any of the following characters:
     * <ul>
     *     <li>Double quote (")</li>
     *     <li>Single quote (')</li>
     *     <li>Backslash (\)</li>
     * </ul>
     *
     * @param stringToValidate string to validate
     * @return true if the string is valid, false otherwise
     */

    public static boolean isValidString(String stringToValidate) {
        return StringUtils.isNotBlank(stringToValidate) && !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
    }
}
