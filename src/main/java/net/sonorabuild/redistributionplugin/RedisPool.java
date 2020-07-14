package net.sonorabuild.redistributionplugin;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;

public class RedisPool implements Listener {
    private final Integer invDefaultMax = 10;
    private final Inventory[] inv = new Inventory[invDefaultMax];
    private HashMap<String, Integer> current = new HashMap<String, Integer>();
    private Plugin pluginRef;

    public RedisPool(Plugin plugin) {
        pluginRef = plugin;
        for(int i=0; i<inv.length; i++){
            inv[i] = Bukkit.createInventory(null, 54, String.format("Redistribution Vault | Page %d", i+1));
            initializeItems(inv[i]);
        }
    }

    public void initializeItems(Inventory inv) {
        inv.setItem(45, createGuiItem(Material.RED_STAINED_GLASS_PANE, "§c§lPrevious Page"));
        for(int i=46; i<53; i++){
            inv.setItem(i, createGuiItem(Material.WHITE_STAINED_GLASS_PANE, "§f§l████████"));
        }
        inv.setItem(53, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§a§lNext Page"));
    }

    // returns ItemStack with title and lore for GUi
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
    public void openInventory(final HumanEntity ent, final Integer n) {
        ent.openInventory(inv[n]);
        Bukkit.getPluginManager().registerEvents(this, pluginRef);
        current.put(ent.getName(), n);
    }

    public String serializePool(final String name) {
        Gson poolJson = new Gson();
        HashMap<Material, Integer> poolItems = new HashMap();
        Inventory tempInv;
        Integer curStack;
        for(int i=0; i<inv.length; i++) {
            tempInv = inv[i];
            for(int j=0; j<tempInv.getSize(); j++) {
                if(j < 45){
                    ItemStack item = tempInv.getItem(j);
                    if(item != null) {
                        curStack = poolItems.getOrDefault(item.getType(), 0);
                        poolItems.put(item.getType(), curStack + item.getAmount());
                    }
                }
            }
        }
        return poolJson.toJson(poolItems);
    }

    // anti click/drag code here
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        // check if clicked inventory is part of this pool, can be reduced to checking nth inventory
        // once next/prev page functions work
        final Player p = (Player) e.getWhoClicked();
        Integer n = current.get(p.getName());
        if(e.getInventory() != inv[n]) return;

        // check if clicked slot is not a GUI item
        if(e.getSlot() >= 45) {
            e.setCancelled(true);
        }

        // previous page function
        if(e.getSlot() == 45) {
            if(n > 0) {
                openInventory(p, n-1);
                current.put(p.getName(), n-1);
            } else {
                openInventory(p, n);
            }
        // next page function
        } else if(e.getSlot() == 53) {
            if(n+1 < inv.length) {
                openInventory(p, n+1);
                current.put(p.getName(), n+1);
            } else {
                openInventory(p, n);
            }
        }
    }

    // need to figure out if this drag event is necessary - haven't found way to break the inventoryclickevent handler
    // yet, so it may not be
    /*@EventHandler
    public void onInventoryClick(final InventoryDragEvent e){
        for(int i=0; i<inv.length; i++){
            if(e.getInventory() == inv[i]){
                e.setCancelled(true);
            }
        }
    }*/

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        Boolean open = false;
        Inventory top = p.getOpenInventory().getTopInventory();
        for (int i=0; i<inv.length; i++) {
            if(top == inv[i]){
                open = true;
            }
        }
        if (open) {
            HandlerList.unregisterAll(this);
        }
    }
}
