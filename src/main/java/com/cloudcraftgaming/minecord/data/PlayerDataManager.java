package com.cloudcraftgaming.minecord.data;

import com.cloudcraftgaming.minecord.bukkit.utils.CodeGenerator;
import com.cloudcraftgaming.minecord.bukkit.utils.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class PlayerDataManager {
    public static void updatePlayerData(Player player) {
        YamlConfiguration data = FileManager.getPlayerData();
        data.set("Players." + player.getUniqueId() + ".Name", player.getName());
        FileManager.savePlayerData(data);
    }

    //Booleans/Checkers
    public static boolean isRegistered(Player player) {
        return FileManager.getPlayerData().contains("Players." + player.getUniqueId() + ".Discord");
    }

    //Functionals
    public static String startRegistration(Player player) {
        YamlConfiguration cache = FileManager.getCache();
        String code = CodeGenerator.getRandomCode();
        cache.set(code, player.getUniqueId());
        FileManager.saveCache(cache);
        return code;
    }

    public static boolean registerDiscordUser(IUser user, String code) {
        YamlConfiguration cache = FileManager.getCache();
        if (cache.contains(code)) {
            String playerIdString = cache.getString(code);
            cache.set(code, null);
            FileManager.saveCache(cache);

            YamlConfiguration playerData = FileManager.getPlayerData();
            playerData.set("Players." + playerIdString + ".Discord", user.getID());
            FileManager.savePlayerData(playerData);

            //TODO: Update nickname AND give role.
            return true;
        }

        return false;
    }
}