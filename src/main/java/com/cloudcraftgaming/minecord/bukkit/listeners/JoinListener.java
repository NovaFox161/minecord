package com.cloudcraftgaming.minecord.bukkit.listeners;

import com.cloudcraftgaming.minecord.data.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
@SuppressWarnings("unused")
public class JoinListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        PlayerDataManager.updatePlayerData(event.getPlayer());
        //TODO: Update Discord Nickname if needed!!
    }
}