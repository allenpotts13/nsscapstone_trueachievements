package com.nashss.se.trueachievementsgroupservice.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class UrlDecoderUtils {
    private static final Logger log = LogManager.getLogger();

    private UrlDecoderUtils() {
    }

    /**
     * Decodes a URL encoded string.
     *
     * @param input string to decode
     * @return decoded string
     */

    public static String urlDecode(String input) {
        try {
            return java.net.URLDecoder.decode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("URL decoding error: " + e.getMessage(), e);
            return input;
        }
    }
}
