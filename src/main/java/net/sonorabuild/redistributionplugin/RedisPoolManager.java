package net.sonorabuild.redistributionplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class RedisPoolManager {
    private HashMap<String, RedisPool> pools = new HashMap<String, RedisPool>();
    private JavaPlugin pluginRef;

    public RedisPoolManager(JavaPlugin plugin) {
        pluginRef = plugin;
    }

    public Boolean openPool(final String name, final HumanEntity ent) {
        if(pools.containsKey(name)) {
            pools.get(name).openInventory(ent);
            return true;
        } else {
            return false;
        }
    }

    public Boolean createPool(final String name) {
        if(!pools.containsKey(name)) {
            pools.put(name, new RedisPool());
            if(registerPoolEvents(name)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void listPools(final HumanEntity ent) {
        // format better
        ent.sendMessage(pools.keySet().toString());
    }

    public void deletePool(final String name){
        if(pools.containsKey(name)) {
            pools.remove(name);
        } else {
            // load list of configs in pools/ and check for pool

        }
    }

    public void loadPool(final String name){
        // complete
    }

    private void savePool(final String name) {
        // complete
        // will serialize selected pool's inventories to config files
    }

    private Boolean registerPoolEvents(final String name) {
        if(pools.containsKey(name)) {
            Bukkit.getPluginManager().registerEvents(pools.get(name), pluginRef);
            return true;
        } else {
            return false;
        }
    }
}
