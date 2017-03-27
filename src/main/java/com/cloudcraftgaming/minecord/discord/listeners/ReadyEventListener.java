package com.cloudcraftgaming.minecord.discord.listeners;

import com.cloudcraftgaming.minecord.Main;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.Status;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
@SuppressWarnings("unused")
public class ReadyEventListener {
    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        Main.client.changeStatus(Status.game(Main.plugin.getConfig().getString("Bot.Playing")));
    }
}