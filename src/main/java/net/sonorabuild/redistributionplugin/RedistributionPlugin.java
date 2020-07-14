package net.sonorabuild.redistributionplugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class RedistributionPlugin extends JavaPlugin {
    public static RedisPoolManager poolManager;
    public static Logger logger;
    public static Plugin instance;

    @Override
    public void onEnable() {
        logger = getLogger();
        instance = this;
        initializeFiles();
        poolManager = new RedisPoolManager(this);
        logger.info("Created pool manager!");
        this.getCommand("redis").setExecutor(new RedisCommand());
        logger.info("Registered /redis command!");
    }

    @Override
    public void onDisable() {
        logger.info("Saving all loaded pools to file!");
        poolManager.saveAllPools();
        logger.info("Disabling Redistribution plugin!");
    }

    private void initializeFiles() {
        File mainFolder = new File("plugins/Redistribution");
        File poolFolder = new File("plugins/Redistribution/pools");
        if(!mainFolder.exists()) { mainFolder.mkdirs(); }
        if(!poolFolder.exists()) { poolFolder.mkdirs(); }



    }
}
