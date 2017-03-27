package com.cloudcraftgaming.minecord.data;

import com.cloudcraftgaming.minecord.bukkit.utils.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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
}