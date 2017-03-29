package com.cloudcraftgaming.minecord;

import com.cloudcraftgaming.minecord.bukkit.command.BaseCommand;
import com.cloudcraftgaming.minecord.bukkit.listeners.JoinListener;
import com.cloudcraftgaming.minecord.bukkit.utils.FileManager;
import com.cloudcraftgaming.minecord.bukkit.utils.MessageManager;
import com.cloudcraftgaming.minecord.discord.listeners.DMListener;
import com.cloudcraftgaming.minecord.discord.listeners.ReadyEventListener;
import com.cloudcraftgaming.minecord.discord.listeners.UserJoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.IGuild;
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
        try {
            client.logout();
        } catch (DiscordException e) {
            //Didn't log out, but that's fine.
        }
    }

    public void onEnable() {
        plugin = this;

        //File stuffs
        FileManager.createConfig();
        FileManager.createCache();
        FileManager.createPlayerData();

        MessageManager.createMessagesFile();
        FileManager.checkFileVersions();

        //Connect bot
        client = createClient(getConfig().getString("Bot.Token"));
        if (client == null)
            throw new NullPointerException("Failed to log in! Client cannot be null!");

        //Register discord event listeners
        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new DMListener());
        dispatcher.registerListener(new UserJoinListener());
        dispatcher.registerListener(new ReadyEventListener());

        //Register Bukkit commands
        getCommand("discord").setExecutor(new BaseCommand());

        //Register Bukkit event Listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
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

    public static IGuild getGuild() {
        return client.getGuilds().get(0);
    }
}