package me.thomas.custommounts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class MountsGUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("mounts")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            GUI.openInventory(player);
            return true;
        }
        return false;
    }
}
