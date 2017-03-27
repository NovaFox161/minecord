package com.cloudcraftgaming.minecord.bukkit.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class CodeGenerator {
    public static String getRandomCode() {
        return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }
}