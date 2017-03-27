package com.cloudcraftgaming.minecord.data;

import com.cloudcraftgaming.minecord.Main;
import com.cloudcraftgaming.minecord.bukkit.utils.CodeGenerator;
import com.cloudcraftgaming.minecord.bukkit.utils.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.RequestBuffer;

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

        if (isRegistered(player)) {
            changeDiscordNickname(data.getString("Players." + player.getUniqueId() + ".Discord"), player.getName());
        }
    }

    //Booleans/Checkers
    public static boolean isRegistered(Player player) {
        return FileManager.getPlayerData().contains("Players." + player.getUniqueId() + ".Discord");
    }

    //Functionals
    public static String startRegistration(Player player) {
        YamlConfiguration cache = FileManager.getCache();
        String code = CodeGenerator.getRandomCode();
        cache.set(code, player.getUniqueId().toString());
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

            //TODO: give role.
            changeDiscordNickname(user, playerData.getString("Players." + playerIdString + ".Name"));
            if (Main.plugin.getConfig().getString("Bot.Role.Apply").equalsIgnoreCase("True")) {
                addMemberRole(user, Main.plugin.getConfig().getString("Bot.Role.Name"));
            }
            return true;
        }

        return false;
    }

    private static void changeDiscordNickname(IUser u, String name) {
        IGuild guild = Main.getGuild();
        try {
            guild.setUserNickname(u, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void changeDiscordNickname(String userId, String name) {
        IGuild guild = Main.getGuild();
        if (guild.getUserByID(userId) != null) {
            try {
                guild.setUserNickname(guild.getUserByID(userId), name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addMemberRole(IUser u, String roleName) {
        if (roleExists(roleName)) {
            IRole role = getRoleFromName(roleName);
            if (role != null) {
                RequestBuffer.request(() -> {
                   try {
                       u.addRole(role);
                   } catch (Exception e) {
                       //Failed to add role.
                   }
                });
            }
        }
    }


    private static boolean roleExists(String roleName) {
        for (IRole r : Main.getGuild().getRoles()) {
            if (r.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    private static IRole getRoleFromName(String roleName) {
        for (IRole r : Main.getGuild().getRoles()) {
            if (r.getName().equals(roleName)) {
                return r;
            }
        }
        return null;
    }
}