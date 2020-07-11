package net.sonorabuild.redistributionplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;


public class RedisCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length < 1){
                player.sendMessage("§cNo subcommand specified!");
            } else if(args[0].equalsIgnoreCase("pool")){
                if (args.length < 2) {
                    player.sendMessage("§cNo subcommand for pool specified!");
                } else if(args[1].equalsIgnoreCase("open")) {
                    if(args.length < 3) {
                        player.sendMessage("§cNo pool specified for open subcommand!");
                    } else {
                        if(!RedistributionPlugin.poolManager.openPool(args[2], player)) {
                            player.sendMessage(String.format("§cPool '%s' not found!", args[2]));
                        }
                    }
                } else if(args[1].equalsIgnoreCase("create")) {
                    if(args.length < 3) {
                        player.sendMessage("§cNo name for pool specified!");
                    } else {
                        if (RedistributionPlugin.poolManager.createPool(args[2])){
                            player.sendMessage(String.format("§aCreated pool %s!", args[2]));
                        } else {
                            player.sendMessage(String.format("§cProblem creating pool %s!", args[2]));
                        }
                    }
                } else if(args[1].equalsIgnoreCase("list")) {
                    Set<String> poolsSet = RedistributionPlugin.poolManager.listPools();
                    if(poolsSet != null && !poolsSet.isEmpty()) {
                        String res = String.join(", ", poolsSet);
                        player.sendMessage("§aPools: " + res);
                    } else {
                        player.sendMessage("§cNo pools found!");
                    }
                } else if(args[1].equalsIgnoreCase("delete")) {
                    if(args.length < 3) {
                        player.sendMessage("§cNo name for pool specified!");
                    } else {
                        if(RedistributionPlugin.poolManager.deletePool(args[2])) {
                            player.sendMessage(String.format("§aSuccessfully deleted pool %s!", args[2]));
                        } else {
                            player.sendMessage(String.format("§cCouldn't delete pool %s!", args[2]));
                        }
                    }
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
