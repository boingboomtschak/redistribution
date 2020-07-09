package net.sonorabuild.redistributionplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class RedisCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack diamond = new ItemStack(Material.DIAMOND);

        } else {
            getLogger().info("This command was run by console!");
        }
        return true;
    }
}
