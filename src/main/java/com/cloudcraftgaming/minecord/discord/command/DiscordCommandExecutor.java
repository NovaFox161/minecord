package com.cloudcraftgaming.minecord.discord.command;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class DiscordCommandExecutor {
    private static DiscordCommandExecutor instance;
    private IDiscordClient client;
    private final ArrayList<ICommand> commands = new ArrayList<>();

    private DiscordCommandExecutor() {}

    /**
     * Gets the instance of the CommandExecutor.
     * @return The instance of the CommandExecutor.
     */
    public static DiscordCommandExecutor getExecutor() {
        if (instance == null) {
            instance = new DiscordCommandExecutor();
        }
        return instance;
    }

    /**
     * Enables the CommandExecutor and sets up the Listener.
     * @param _client The Client associated with the Bot.
     * @return The CommandExecutor's instance.
     */
    public DiscordCommandExecutor enable(IDiscordClient _client) {
        client = _client;
        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new CommandListener(this));
        return instance;
    }


    //Functionals
    /**
     * Registers a command that can be executed.
     * @param _command The command to register.
     */
    public void registerCommand(ICommand _command) {
        commands.add(_command);
    }

    /**
     * Issues a command if valid, else does nothing.
     * @param cmd The Command to issue.
     * @param args The command arguments used.
     * @param event The Event received.
     */
    void issueCommand(String cmd, String[] args, MessageReceivedEvent event) {
        for (ICommand c : commands) {
            if (c.getCommand().equalsIgnoreCase(cmd) || c.getAliases().contains(cmd.toLowerCase())) {
                c.issueCommand(args, event, client);
            }
        }

    }

    /**
     * Gets an ArrayList of all valid commands.
     * @return An ArrayList of all valid commands.
     */
    ArrayList<String> getAllCommands() {
        ArrayList<String> cmds = new ArrayList<>();
        for (ICommand c : commands) {
            if (!cmds.contains(c.getCommand())) {
                cmds.add(c.getCommand());
            }
        }
        return cmds;
    }
}