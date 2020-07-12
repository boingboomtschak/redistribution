package net.sonorabuild.redistributionplugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class RedistributionPlugin extends JavaPlugin {
    public static RedisPoolManager poolManager;
    public static Logger logger;
    public static Plugin instance;

    @Override
    public void onEnable() {
        logger = getLogger();
        instance = this;
        poolManager = new RedisPoolManager(this);
        logger.info("Created pool manager!");
        this.getCommand("redis").setExecutor(new RedisCommand());
        logger.info("Registered /redis command!");
    }

    @Override
    public void onDisable() {
        logger.info("Disabling Redistribution plugin!");
    }
}
