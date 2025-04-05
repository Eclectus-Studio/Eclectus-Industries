package com.eclectusstudio.eclectusIndustries.commands;

import com.eclectusstudio.eclectusIndustries.api.MachineItem;
import com.eclectusstudio.eclectusIndustries.api.MachineItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveMachineItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (sender instanceof Player player) {

            // Check permission
            if (player.hasPermission("giveitem.command") || player.isOp()) {

                // Must supply 1 argument: the machine name
                if (args.length == 1) {
                    String inputName = args[0].toLowerCase();

                    // Search for machine
                    for (MachineItem machineItem : MachineItems.getMachineItems()) {
                        if (machineItem.getName().equalsIgnoreCase(inputName)) {
                            player.getInventory().addItem(machineItem.getItem());
                            player.sendMessage("§aYou have been given a " + machineItem.getName());
                            return true;
                        }
                    }

                    player.sendMessage("§cMachine not found: " + inputName);
                    return false;

                } else {
                    player.sendMessage("§cUsage: /givemachineitem <machine>");
                    return false;
                }

            } else {
                player.sendMessage("§cYou do not have permission to use this command.");
                return false;
            }

        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }
    }
}
