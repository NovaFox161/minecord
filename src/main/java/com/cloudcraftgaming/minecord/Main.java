package com.cloudcraftgaming.minecord;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Nova Fox on 3/25/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class Main extends JavaPlugin {
    public static Main plugin;

    @Override
    public void onDisable() {
        //Disconnect bot
    }

    public void onEnable() {
        plugin = this;

        //Connect bot
    }
}