package me.thomas.custommounts;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class CustomMounts extends JavaPlugin {

    public Map<UUID, List<Mount>> mounts = new HashMap<>();
    private static CustomMounts instance;
    private ProtocolManager protocolManager;
    public MoveMount moveMount;

    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        moveMount = new MoveMount();
        instance = this;
        GUI.register();
        registerEvents();
        registerCommands();
        moveMount.vehiclePacket();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ProtocolManager getProtocolManager(){
        return protocolManager;
    }

    public void registerEvents(){
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new PlayerDismount(), this);
        pm.registerEvents(new AddMount(), this);
        pm.registerEvents(new InventoryClose(), this);
    }

    public void registerCommands(){
        this.getCommand("mounts").setExecutor(new MountsGUICommand());
    }

    public static CustomMounts getInstance(){
        return instance;
    }

    public void addCustomMount(Player player, Mount mount){
        if (!mounts.containsKey(player.getUniqueId())){
            mounts.put(player.getUniqueId(), new ArrayList<>());
        }
        mounts.get(player.getUniqueId()).add(mount);
    }

    public List<Mount> getCustomMounts(Player player){
        if (mounts.containsKey(player.getUniqueId()))
            return mounts.get(player.getUniqueId());
        return null;
    }

    public ItemStack tamerStick(){
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Tamer Stick");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right click on an entity while \n sneaking to tame it");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (label.equalsIgnoreCase("tamestick")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            player.getInventory().addItem(this.tamerStick());
            return true;
        }
        return false;
    }

}