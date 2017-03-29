package com.cloudcraftgaming.minecord.bukkit.utils;

import com.cloudcraftgaming.minecord.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class MessageManager {

    private static File getMessageFile() {
        return new File(Main.plugin.getDataFolder() + "/Messages.yml");
    }

    public static FileConfiguration getMessageYml() {
        File messageFile = getMessageFile();
        return YamlConfiguration.loadConfiguration(messageFile);
    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', getMessageYml().getString(path));
    }

    public static String getDiscordMessage(String path) {
        return getMessageYml().getString(path);
    }

    public static void createMessagesFile() {
        File enFile = getMessageFile();
        if (!enFile.exists()) {
            Main.plugin.getLogger().info("Generating Messages.yml messages file...");
            YamlConfiguration msg = YamlConfiguration.loadConfiguration(enFile);

            msg.addDefault("DO NOT DELETE.A", "Minecord is developed and managed by NovaFox161");
            msg.addDefault("DO NOT DELETE.B", "Minecord is a paid for plugin and is to only be used by the purchaser!");
            msg.addDefault("Messages Version", FileManager.msgVersion);

            msg.addDefault("Command.Register.Start", "&6Please DM the bot with this code to register &5%code%&6!");

            msg.addDefault("Discord.Register.Success", "You have successfully registered!");
            msg.addDefault("Discord.Register.Failure", "Uh oh! The code seems to be invalid! Please make sure you copied it correctly!");
            msg.addDefault("Discord.Join.DM", "Welcome! Please enter your registration code, if you do not have one, in-game type /discord register");

            msg.addDefault("Notif.Command.PlayerOnly", "&4Only players can use that command!");
            msg.addDefault("Notif.Command.Register.Already", "&4You are already registered!");
            msg.addDefault("Notif.Args.Few", "&4Too few arguments!");
            msg.addDefault("Notif.args.Many", "&4Too many arguments!");
            msg.addDefault("Notif.args.Invalid", "&4Invalid arguments!");

            msg.options().copyDefaults(true);
            saveLangFile(msg);

            msg.options().copyDefaults(true);
            saveLangFile(msg);
        }
    }

    private static void saveLangFile(YamlConfiguration lang) {
        try {
            lang.save(getMessageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}