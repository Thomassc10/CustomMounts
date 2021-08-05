package me.thomas.custommounts;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerDismount implements Listener {

    @EventHandler
    public void onDismount(EntityDismountEvent event){
        if (!(event.getEntity() instanceof Player)) return;
        //if (!(event.getEntity() instanceof Mount)) return;
        Entity e = event.getDismounted();
        Player player = (Player) event.getEntity();
        e.remove();
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
        player.spawnParticle(Particle.SMOKE_LARGE, e.getLocation(), 5);
    }
}
