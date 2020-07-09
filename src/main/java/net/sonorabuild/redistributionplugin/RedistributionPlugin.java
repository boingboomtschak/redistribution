package net.sonorabuild.redistributionplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class RedistributionPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Enabling Redistribution plugin!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Redistribution pluign!");

    }
}
