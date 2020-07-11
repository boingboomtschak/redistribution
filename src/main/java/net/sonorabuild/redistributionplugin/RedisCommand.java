package net.sonorabuild.redistributionplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class RedisCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0){
                player.sendMessage("§cNo subcommand specified!");
            } else if(args[0].equalsIgnoreCase("pool")){
                if (args[1] != null) {
                    player.sendMessage("§cNo subcommand for pool specified!");
                } else if(args[1].equalsIgnoreCase("open")) {
                    if(args[2] != null) {
                        if(!RedistributionPlugin.poolManager.openPool(args[2], player)) {
                            player.sendMessage(String.format("§cPool '%s' not found!", args[2]));
                        }
                    } else {

                    }
                } else if(args[1].equalsIgnoreCase("create")) {
                    player.sendMessage("§bNot implemented yet!");
                } else if(args[1].equalsIgnoreCase("list")) {
                    player.sendMessage("§bNot implemented yet!");
                } else if(args[1].equalsIgnoreCase("delete")) {
                    player.sendMessage("§bNot implemented yet!");
                } else {
                    player.sendMessage("§cUnknown subcommand for pool!");
                }
            } else {
                player.sendMessage("§cUnknown subcommand!");
            }
        }
        return true;
    }
}
