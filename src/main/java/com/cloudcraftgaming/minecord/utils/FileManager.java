package com.cloudcraftgaming.minecord.utils;

import com.cloudcraftgaming.minecord.Main;

import java.io.File;

/**
 * Created by Nova Fox on 3/26/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class FileManager {
    public static void createConfig() {
        File file = new File(Main.plugin.getDataFolder() + "/config.yml");
        if (!file.exists()) {
            Main.plugin.getLogger().info("Generating config.yml...");

            Main.plugin.getConfig().addDefault("DO NOT DELETE.A", "Minecord is developed and managed by NovaFox161");
            Main.plugin.getConfig().addDefault("DO NOT DELETE.B", "Minecord is a paid for plugin and is to only be used by the purchaser!");
        }
        Main.plugin.getConfig().addDefault("Config Version", 1.0);

        Main.plugin.getConfig().options().copyDefaults(true);
        Main.plugin.saveConfig();
        Main.plugin.getConfig().options().copyDefaults(true);
        Main.plugin.saveConfig();
    }
}