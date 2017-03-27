package com.cloudcraftgaming.minecord.discord.utils;

import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.RequestBuffer;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class Message {
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
}