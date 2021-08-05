package me.thomas.custommounts.inventory;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClose implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (!event.getView().getTitle().contains("Mounts")) return;
        Inventory inv = (Inventory) event.getView();
        for (ItemStack item : inv.getContents()){
            if (item.getType() != Material.GRAY_STAINED_GLASS_PANE && item.getType() != Material.BONE)
                inv.remove(item);
        }
    }
}
