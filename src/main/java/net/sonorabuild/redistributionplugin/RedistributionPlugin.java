package net.sonorabuild.redistributionplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class RedistributionPlugin extends JavaPlugin {
    public static RedisVault redisGui;
    public static Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info("Enabling Redistribution plugin!");
        redisGui = new RedisVault();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(redisGui, this);
        this.getCommand("redis").setExecutor(new RedisCommand());
    }

    @Override
    public void onDisable() {
        logger.info("Disabling Redistribution plugin!");

    }
}
