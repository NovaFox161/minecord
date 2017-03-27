package com.cloudcraftgaming.minecord.bukkit.command;

import com.cloudcraftgaming.minecord.bukkit.utils.MessageManager;
import com.cloudcraftgaming.minecord.data.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 3/27/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: Minecord
 */
public class BaseCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("discord")) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (args.length < 1) {
                    //Not enough args
                    player.sendMessage(MessageManager.getMessage("Notif.Args.Few"));
                } else if (args.length == 1) {
                    String function = args[0];
                    if (function.equalsIgnoreCase("register")) {
                        if (!PlayerDataManager.isRegistered(player)) {
                            //Lets generate a code for them and put them in the cache!!!
                            String code = PlayerDataManager.startRegistration(player);
                            String msgOr = MessageManager.getMessageYml().getString("Command.Register.Start");
                            String msg = msgOr.replaceAll("%code%", code);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            //Already registered!
                            player.sendMessage(MessageManager.getMessage("Notif.Command.Register.Already"));
                        }
                    } else {
                        //Invalid args
                        player.sendMessage(MessageManager.getMessage("Notif.args.Invalid"));
                    }
                } else {
                    //Too many args
                    player.sendMessage(MessageManager.getMessage("Notif.args.Many"));
                }
            } else {
                //Non-player cannot register.
                sender.sendMessage(MessageManager.getMessage("Notif.Command.PlayerOnly"));
            }
        }
        return false;
    }
}