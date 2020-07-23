package net.sonorabuild.redistributionplugin;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public Boolean loadPool(final String name){
        try {
            String poolFilePath = String.format("plugins/Redistribution/pools/%s.json", name);
            String fileString = new String(Files.readAllBytes(Paths.get(poolFilePath)), StandardCharsets.UTF_8);
            if(!pools.containsKey(name)){
                createPool(name);
            }
            pools.get(name).deserializePool(fileString);
            return true;
        } catch (IOException e) {
            RedistributionPlugin.logger.severe(e.getMessage());
            return false;
        }
    }

    public Boolean savePool(final String name){
        try {
            String poolFilePath = String.format("plugins/Redistribution/pools/%s.json", name);
            File poolFileHolder = new File(poolFilePath);
            poolFileHolder.createNewFile();
            FileWriter poolFile = new FileWriter(poolFilePath);
            poolFile.write(pools.get(name).serializePool());
            poolFile.close();
            return true;
        } catch (IOException e) {
            RedistributionPlugin.logger.severe(e.getMessage());
            return false;
        }
    }

    public void saveAllPools() {
        Boolean exception = false;
        for (String name : pools.keySet()) {
            if(!savePool(name)){
                exception = true;
            }
        }
        if(exception){
            RedistributionPlugin.logger.severe("Error serializing all pools to files!");
        }
    }


}
