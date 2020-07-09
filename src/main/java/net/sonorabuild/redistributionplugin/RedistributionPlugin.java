package net.sonorabuild.redistributionplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class RedistributionPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Enabling Redistribution plugin!");
        this.getCommand("redis").setExecutor(new RedisCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Redistribution plugin!");

    }
}
