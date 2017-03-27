package com.cloudcraftgaming.minecord.discord.command;

import com.cloudcraftgaming.minecord.Main;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
@SuppressWarnings("unused")
class CommandListener {
    private DiscordCommandExecutor cmd;

    /**
     * Creates a new CommandListener listener.
     * @param _cmd The CommandExecutor instance to use.
     */
    CommandListener(DiscordCommandExecutor _cmd) {
        cmd = _cmd;
    }

    /**
     * Checks for command validity and calls the command executor if valid.
     * @param event The event received to check for a command.
     */
    @EventSubscriber
    public void onMessageEvent(MessageReceivedEvent event) {
        try {
            if (event.getMessage() != null && event.getMessage().getGuild() != null && event.getMessage().getChannel() != null && !event.getMessage().getChannel().isPrivate() && event.getMessage().getContent() != null && event.getMessage().getContent().length() > 0) {
                //Message is a valid guild message (not DM). Check if in correct channel.
                if (event.getMessage().getContent().startsWith("!")) {
                    //Prefixed with ! which should mean it is a command, convert and confirm.
                    String[] argsOr = event.getMessage().getContent().split(" ");
                    if (argsOr.length > 1) {
                        ArrayList<String> argsOr2 = new ArrayList<>();
                        argsOr2.addAll(Arrays.asList(argsOr).subList(1, argsOr.length));
                        String[] args = argsOr2.toArray(new String[argsOr2.size()]);

                        String command = argsOr[0].replaceAll("!", "");
                        cmd.issueCommand(command, args, event);
                    } else if (argsOr.length == 1) {
                        //Only command... no args.
                        cmd.issueCommand(argsOr[0].replaceAll("!", ""), new String[0], event);
                    }
                } else if (!event.getMessage().mentionsEveryone() && !event.getMessage().mentionsHere() && (event.getMessage().toString().startsWith("<@" + Main.getSelfUser().getID() + ">") || event.getMessage().toString().startsWith("<@!" + Main.getSelfUser().getID() + ">"))) {
                    String[] argsOr = event.getMessage().getContent().split(" ");
                    if (argsOr.length > 2) {
                        ArrayList<String> argsOr2 = new ArrayList<>();
                        argsOr2.addAll(Arrays.asList(argsOr).subList(2, argsOr.length));
                        String [] args = argsOr2.toArray(new String[argsOr2.size()]);

                        String command = argsOr[1];
                        cmd.issueCommand(command, args, event);
                    } else if (argsOr.length == 2) {
                        //No args...
                        cmd.issueCommand(argsOr[1], new String[0], event);
                    } else if (argsOr.length == 1) {
                        //Only Minecord mentioned...
                        cmd.issueCommand("Minecord", new String[0], event);
                    }
                }
            }
        } catch (Exception e) {
            //Non fatal error, ignore.
        }
    }
}