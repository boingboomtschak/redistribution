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
            if(args.length < 1) {
                printHelp(player);
            } else if(args[0].equalsIgnoreCase("pool")) {
                if (args.length < 2) {
                    printHelp(player, "pool");
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
                } else if(args[1].equalsIgnoreCase("save")) {
                    if(args.length < 3) {
                        player.sendMessage("§cNo name for pool specified!");
                    } else {
                        if(RedistributionPlugin.poolManager.savePool(args[2])) {
                            player.sendMessage(String.format("§aSuccessfully saved pool %s!", args[2]));
                        } else {
                            player.sendMessage(String.format("§cCouldn't save pool %s!", args[2]));
                        }
                    }
                } else if(args[1].equalsIgnoreCase("help")) {
                    printHelp(player, "pool");
                } else {
                    player.sendMessage("§cUnknown subcommand for pool!");
                }
            } else if(args[0].equalsIgnoreCase("help")) {
                printHelp(player);
            } else {
                player.sendMessage("§cUnknown subcommand!");
            }
        }
        return true;
    }

    private void printHelp(final Player player) {
        player.sendMessage("§8-- §6Redistribution Help §8--");
        player.sendMessage("§epool §8|§7 Subcommand to manipulate item pools");
    }

    private void printHelp(final Player player, final String topic) {
        if(topic == "pool") {
            player.sendMessage("§8-- §6Redistribution 'pool' Help §8--");
            player.sendMessage("§epool create <name> §8|§7 Creates new item pool with set name");
            player.sendMessage("§epool open <name>   §8|§7 Opens specified item pool by name");
            player.sendMessage("§epool delete <name> §8|§7 Deletes item pool by name");
            player.sendMessage("§epool list               §8|§7 Lists all existing item pools");
        } else {
            printHelp(player);
        }
    }
}
