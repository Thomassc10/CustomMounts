package me.thomas.custommounts;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class AddMount implements Listener {

    /*@EventHandler
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (!player.getInventory().getItemInMainHand().getType().toString().contains("SPAWN_EGG")) return;
        event.setCancelled(true);
        CustomMounts customMounts = CustomMounts.getInstance();

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        String[] name = item.getType().toString().split("_");
        meta.setDisplayName(name[0]);
        item.setItemMeta(meta);

        //customMounts.addMount(player, item);
        player.getInventory().remove(item);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
    }*/

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        Mount mount = new Mount(entity.getUniqueId());
        CustomMounts customMounts = CustomMounts.getInstance();
        if (player.getInventory().getItemInMainHand() == customMounts.tamerStick() && player.isSneaking()) {
            customMounts.addCustomMount(player, mount);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 1);
            entity.remove();
        }
    }
}
