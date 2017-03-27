package com.cloudcraftgaming.minecord.discord.utils;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RequestBuffer;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class Message {
    public static String lineBreak = System.getProperty("line.separator");

    public static void sendMessage(String message, MessageReceivedEvent event, IDiscordClient client) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(client).appendContent(message).withChannel(event.getMessage().getChannel()).build();
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendMessage(String message, IChannel channel, IDiscordClient client) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(client).appendContent(message).withChannel(channel).build();
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendMessage(EmbedObject embed, MessageReceivedEvent event, IDiscordClient client) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(client).withEmbed(embed).withChannel(event.getMessage().getChannel()).build();
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendMessage(EmbedObject embed, IChannel channel, IDiscordClient client) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(client).withEmbed(embed).withChannel(channel).build();
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendMessage(EmbedObject embed, String message, MessageReceivedEvent event, IDiscordClient client) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(client).appendContent(message).withEmbed(embed).withChannel(event.getMessage().getChannel()).build();
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendMessage(EmbedObject embed, String message, IChannel channel, IDiscordClient client) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(client).appendContent(message).withEmbed(embed).withChannel(channel).build();
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendDirectMessage(String message, IUser user) {
        RequestBuffer.request(() -> {
            try {
                IPrivateChannel pc = user.getOrCreatePMChannel();
                pc.sendMessage(message);
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendDirectMessage(EmbedObject embed, IUser user) {
        RequestBuffer.request(() -> {
            try {
                IPrivateChannel pc = user.getOrCreatePMChannel();
                pc.sendMessage("", embed, false);
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }

    public static void sendDirectMessage(String message, EmbedObject embed, IUser user) {
        RequestBuffer.request(() -> {
            try {
                IPrivateChannel pc = user.getOrCreatePMChannel();
                pc.sendMessage(message, embed, false);
            } catch (Exception e) {
                //Failed to send message.
            }
        });
    }
}