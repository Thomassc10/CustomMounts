package me.thomas.custommounts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class Mount {

    private final UUID uuid;
    private double speed;
    private final String name;
    public Mount(UUID uuid){
        this.uuid = uuid;
        name = this.getMount().getType().toString();
    }

    public Entity getMount(){
        return Bukkit.getEntity(uuid);
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public String getName(){
        return name;
    }

    public Material getItem(){
        for (Material material : Material.values()){
            if (material.toString().contains(name) && material.toString().contains("SPAWN_EGG"))
                return material;
        }
        return null;
    }

}
