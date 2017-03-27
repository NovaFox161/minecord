package com.cloudcraftgaming.minecord.discord.listeners;

import com.cloudcraftgaming.minecord.Main;
import com.cloudcraftgaming.minecord.bukkit.utils.MessageManager;
import com.cloudcraftgaming.minecord.data.PlayerDataManager;
import com.cloudcraftgaming.minecord.discord.utils.Message;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
@SuppressWarnings("unused")
public class DMListener {
    /**
     * Checks for command validity and calls the command executor if valid.
     * @param event The event received to check for a command.
     */
    @EventSubscriber
    public void onMessageEvent(MessageReceivedEvent event) {
        try {
            if (event.getMessage().getChannel().isPrivate()) {
                //DMed, let's see if they sent us a code.
                if (event.getMessage().getContent().trim().length() == 6) {
                    //This is a valid code length.
                    if (PlayerDataManager.registerDiscordUser(event.getMessage().getAuthor(), event.getMessage().getContent().trim())) {
                        //Successfully registered... send a message!!
                        Message.sendDirectMessage(MessageManager.getDiscordMessage("Discord.Register.Success"), event.getMessage().getAuthor());
                    } else {
                        //Invalid code...
                        Message.sendDirectMessage(MessageManager.getDiscordMessage("Discord.Register.Failure"), event.getMessage().getAuthor());
                    }
                }
            }
        } catch (Exception e) {
            //Something went wrong?
            Main.plugin.getLogger().warning("A non fatal error occurred! This can be ignored!");
            e.printStackTrace();
        }
    }
}