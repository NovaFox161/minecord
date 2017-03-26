package com.cloudcraftgaming.minecord;

import com.cloudcraftgaming.minecord.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * Created by Nova Fox on 3/25/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class Main extends JavaPlugin {
    public static Main plugin;

    public static IDiscordClient client;

    @Override
    public void onDisable() {
        //Disconnect bot
    }

    public void onEnable() {
        plugin = this;

        //File stufs
        FileManager.createConfig();

        //Connect bot
        client = createClient("token"); //TODO: Token from config
        if (client == null)
            throw new NullPointerException("Failed to log in! Client cannot be null!");


    }

    private static IDiscordClient createClient(String token) {
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            return clientBuilder.login();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
        return null;
    }
}