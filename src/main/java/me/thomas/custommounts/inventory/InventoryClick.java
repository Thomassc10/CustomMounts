package me.thomas.custommounts.inventory;

import me.thomas.custommounts.CustomMounts;
import me.thomas.custommounts.mountsmanager.Mount;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!event.getView().getTitle().contains("Mounts")) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) return;
        event.setCancelled(true);
        CustomMounts customMounts = CustomMounts.getInstance();
        Player player = (Player) event.getWhoClicked();
        if (player.isInsideVehicle()) {
            player.sendMessage(ChatColor.RED + "You cannot do this while on a mount!");
            player.closeInventory();
            return;
        }
        Mount mount = customMounts.getCustomMounts(player).get(event.getSlot() - 10);
        LivingEntity livingEntity = (LivingEntity) player.getWorld().spawn(player.getLocation(), mount.getMount().getClass());
        livingEntity.addPassenger(player);
        livingEntity.setGravity(false);
        livingEntity.setInvulnerable(true);
        player.closeInventory();
    }
}
