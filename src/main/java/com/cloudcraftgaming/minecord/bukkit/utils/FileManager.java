package com.cloudcraftgaming.minecord.bukkit.utils;

import com.cloudcraftgaming.minecord.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nova Fox on 3/26/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class FileManager {
    private static Double conVersion = 1.0;

    public static void createConfig() {
        File file = new File(Main.plugin.getDataFolder() + "/config.yml");
        if (!file.exists()) {
            Main.plugin.getLogger().info("Generating config.yml...");

            Main.plugin.getConfig().addDefault("DO NOT DELETE.A", "Minecord is developed and managed by NovaFox161");
            Main.plugin.getConfig().addDefault("DO NOT DELETE.B", "Minecord is a paid for plugin and is to only be used by the purchaser!");

            Main.plugin.getConfig().addDefault("Config Version", conVersion);

            Main.plugin.getConfig().addDefault("Bot.Token", "Enter-token-here");

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }

    public static void createCache() {
        if (!getCacheFile().exists()) {
            YamlConfiguration yml = getCache();
            Main.plugin.getLogger().info("Generating cache.yml...");

            yml.addDefault("DO NOT DELETE.A", "Minecord is developed and managed by NovaFox161");
            yml.addDefault("DO NOT DELETE.B", "Minecord is a paid for plugin and is to only be used by the purchaser!");

            yml.options().copyDefaults(true);
            saveCache(yml);
            yml.options().copyDefaults(true);
            saveCache(yml);
        }
    }

    public static void createPlayerData() {
        if (!getPlayerDataFile().exists()) {
            YamlConfiguration yml = getPlayerData();
            Main.plugin.getLogger().info("Generating players.yml...");

            yml.addDefault("DO NOT DELETE.A", "Minecord is developed and managed by NovaFox161");
            yml.addDefault("DO NOT DELETE.B", "Minecord is a paid for plugin and is to only be used by the purchaser!");

            yml.options().copyDefaults(true);
            savePlayerData(yml);
            yml.options().copyDefaults(true);
            savePlayerData(yml);
        }
    }

    //Getters
    public static File getCacheFile() {
        return new File(Main.plugin.getDataFolder() + "/cache.yml");
    }

    public static YamlConfiguration getCache() {
        return YamlConfiguration.loadConfiguration(getCacheFile());
    }

    public static File getPlayerDataFile() {
        return new File(Main.plugin.getDataFolder() + "/players.yml");
    }

    public static YamlConfiguration getPlayerData() {
        return YamlConfiguration.loadConfiguration(getPlayerDataFile());
    }

    //Functionals
    public static void saveCache(YamlConfiguration yml) {
        try {
            yml.save(getCacheFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePlayerData(YamlConfiguration yml) {
        try {
            yml.save(getPlayerDataFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkFileVersions() {
        if (Main.plugin.getConfig().getDouble("Config Version") != conVersion) {
            Main.plugin.getLogger().severe("Config out dated!!! Please save settings, delete config, and restart server!! Disabling plugin to prevent further errors!");
            Main.plugin.getPluginLoader().disablePlugin(Main.plugin);
        }
    }
}