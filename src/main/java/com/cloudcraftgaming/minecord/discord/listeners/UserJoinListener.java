package com.cloudcraftgaming.minecord.discord.listeners;

import com.cloudcraftgaming.minecord.bukkit.utils.MessageManager;
import com.cloudcraftgaming.minecord.discord.utils.Message;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.UserJoinEvent;

/**
 * Created by Nova Fox on 3/28/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
@SuppressWarnings("unused")
public class UserJoinListener {
    @EventSubscriber
    public void onJoinEvent(UserJoinEvent event) {
        Message.sendDirectMessage(MessageManager.getDiscordMessage("Discord.Join.DM"), event.getUser());
    }
}