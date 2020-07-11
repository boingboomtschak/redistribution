package net.sonorabuild.redistributionplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;

import static org.bukkit.Bukkit.getServer;

public class RedisPoolManager {
    private HashMap<String, RedisPool> pools = new HashMap<String, RedisPool>();

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
            RedistributionPlugin.registerPool(pools.get(name));
            /*if(registerPoolEvents(name)) {
                return true;
            } else {
                return false;
            }*/
            return true;
        } else {
            return false;
        }
    }

    public Set<String> listPools() {
        return pools.keySet();
    }

    public Boolean deletePool(final String name){
        if(pools.containsKey(name)) {
            pools.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public void loadPool(final String name){
        // complete
    }

    private void savePool(final String name) {
        // complete
        // will serialize selected pool's inventories to config files
    }

    /*private Boolean registerPoolEvents(final String name) {
        if(pools.containsKey(name)) {
            Bukkit.getPluginManager().registerEvents(pools.get(name), pluginRef);
            return true;
        } else {
            return false;
        }
    }*/
}
