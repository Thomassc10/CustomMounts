package me.thomas.custommounts.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.thomas.custommounts.CustomMounts;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MoveMount {

    public void vehiclePacket(){
        CustomMounts customMounts = CustomMounts.getInstance();
        customMounts.getProtocolManager().addPacketListener(new PacketAdapter(customMounts,
                ListenerPriority.NORMAL, PacketType.Play.Client.STEER_VEHICLE) {
            @Override
            public void onPacketReceiving(PacketEvent event){
                if (event.getPacketType() == PacketType.Play.Client.STEER_VEHICLE){
                    PacketContainer packet = event.getPacket();
                    Player player = event.getPlayer();
                    LivingEntity entity = (LivingEntity) player.getVehicle();
                    if (packet.getFloat().read(1) > 0){
                        assert entity != null;
                        entity.setVelocity(player.getLocation().getDirection().multiply(0.5));
                    }
                    if (packet.getFloat().read(1) < 0){
                        assert entity != null;
                        entity.setVelocity(player.getLocation().getDirection().multiply(-0.5));
                    }
                    if (packet.getFloat().read(0) > 0){
                        assert entity != null;
                        entity.setVelocity(player.getLocation().getDirection().add(new Vector(5, 0, 5)).multiply(0.5));
                    }
                    if (packet.getFloat().read(0) < 0){
                        assert entity != null;
                        entity.setVelocity(player.getLocation().getDirection().subtract(new Vector(5, 0, 5)).multiply(0.5));
                    }
                }
            }
        });
    }
}
