package net.sonorabuild.redistributionplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Set;

public class RedisPoolManager {
    private HashMap<String, RedisPool> pools = new HashMap<String, RedisPool>();
    private Plugin pluginRef;

    public RedisPoolManager(Plugin plugin) {
        pluginRef = plugin;
    }

    public Boolean openPool(final String name, final HumanEntity ent) {
        if(pools.containsKey(name)) {
            pools.get(name).openInventory(ent, 0);
            return true;
        } else {
            return false;
        }
    }

    public Boolean createPool(final String name) {
        if(!pools.containsKey(name)) {
            pools.put(name, new RedisPool(pluginRef));
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
        // will deserialize pool inventories
    }

    private void savePool(final String name) {
        // complete
        // will serialize selected pool's inventories to config files
    }
}
