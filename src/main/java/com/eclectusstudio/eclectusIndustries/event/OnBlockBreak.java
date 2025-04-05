package com.eclectusstudio.eclectusIndustries.event;

import com.eclectusstudio.eclectusIndustries.machines.Machines;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Check if the block is a machine
        if (Machines.getMachine(event.getBlock()) != null) {
            Machines.removeMachine(event.getBlock());
            event.getPlayer().sendMessage("§cMachine removed.");
        }
    }
}
