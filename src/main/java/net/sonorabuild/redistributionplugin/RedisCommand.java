package net.sonorabuild.redistributionplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class RedisCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0){
                player.sendMessage("§cNo subcommand specified!");
            } else if(args[0].equalsIgnoreCase("vault")){
                RedistributionPlugin.redisGui.openInventory(player);
            } else {
                player.sendMessage("§cUnknown subcommand!");
            }
        }
        return true;
    }
}
