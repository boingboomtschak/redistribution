package net.sonorabuild.redistributionplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RedisVault implements Listener {
    private final Integer invNum = 5;
    private final Inventory[] inv = new Inventory[invNum];
    public RedisVault() {
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
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv[0]);
    }

    // anti click/drag code here
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        //RedistributionPlugin.logger.info("DEBUG WORKING");
        Boolean correctInv = false;
        for(int i=0; i<inv.length; i++){
            if(e.getInventory() == inv[i]) {
                correctInv = true;
            }
        }
        if(!correctInv) return;
        final Player p = (Player) e.getWhoClicked();
        if(e.getSlot() >= 45) {
            e.setCancelled(true);
        }
        // prev/next page function
        if(e.getSlot() == 45) {
            p.sendMessage("Previous page"); //debug
            Integer n;
            p.openInventory(inv[4]);
        } else if(e.getSlot() == 53) {
            p.sendMessage("Next page"); //debug
            Integer n;
            p.openInventory(inv[1]);
        }

        /*final ItemStack clickedItem = e.getCurrentItem();
        if(clickedItem == null || clickedItem.getType() == Material.AIR) return;*/
    }

    /*@EventHandler
    public void onInventoryClick(final InventoryDragEvent e){
        for(int i=0; i<inv.length; i++){
            if(e.getInventory() == inv[i]){
                e.setCancelled(true);
            }
        }
    }*/

}
