package me.thomas.custommounts.inventory;

import me.thomas.custommounts.CustomMounts;
import me.thomas.custommounts.mountsmanager.Mount;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    private static Inventory INV;
    
    public static void register(){
        Inventory inv = Bukkit.createInventory(null, 45, "Mounts");
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        for (int i = 0; i < 9; i++){
            inv.setItem(i, item);
        }
        for (int i = 9; i < 45; i+=9){
            inv.setItem(i, item);
        }
        for (int i = 36; i < 45; i++){
            inv.setItem(i, item);
        }
        for (int i = 17; i < 36; i+=9){
            inv.setItem(i, item);
        }
        ItemStack bone = new ItemStack(Material.BONE);
        ItemMeta meta = bone.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Mounts");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Here you can view and spawn \n your mounts");
        meta.setLore(lore);
        bone.setItemMeta(meta);
        inv.setItem(4, bone);
        setInventory(inv);
    }

    public static Inventory getInventory(){
        return INV;
    }

    public static void setInventory(Inventory inv){
        INV = inv;
    }

    public static void openInventory(Player player){
        CustomMounts customMounts = CustomMounts.getInstance();
        List<Mount> mounts = customMounts.getCustomMounts(player);
        for (Mount mount : mounts){
            Entity entity = mount.getMount();
            ItemStack item = new ItemStack(mount.getItem());
            ItemMeta meta = item.getItemMeta();
            String name = mount.getMount().getType().toString();
            String s = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            meta.setDisplayName(ChatColor.GREEN + s);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Click to spawn your mount.");
            meta.setLore(lore);
            item.setItemMeta(meta);
            INV.setItem(INV.firstEmpty(), item);
        }
        player.openInventory(INV);
    }

}
