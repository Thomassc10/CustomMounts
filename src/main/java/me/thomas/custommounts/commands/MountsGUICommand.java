package me.thomas.custommounts.commands;

import me.thomas.custommounts.inventory.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
