package com.eclectusstudio.eclectusIndustries.commands.tabcompleter;

import com.eclectusstudio.eclectusIndustries.api.MachineItem;
import com.eclectusstudio.eclectusIndustries.api.MachineItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GiveMachineItemTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (MachineItem item : MachineItems.getMachineItems()) {
                completions.add(item.getName());
            }
        }

        return completions;
    }
}
