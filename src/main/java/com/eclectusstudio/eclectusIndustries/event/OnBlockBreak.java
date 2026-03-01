package com.eclectusstudio.eclectusIndustries.event;

import com.eclectusstudio.eclectusIndustries.api.Machine;
import com.eclectusstudio.eclectusIndustries.machines.Machines;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Check if the block is a machine
        if (Machines.getMachine(event.getBlock()) != null) {
            Block block = event.getBlock();
            Machine machine = Machines.getMachine(event.getBlock());
            World world = block.getWorld();
            Location location = block.getLocation();

            Machines.removeMachine(block);
            event.setDropItems(false);
            world.dropItemNaturally(location,machine.getDrop());
            event.getPlayer().sendMessage("§cMachine removed.");
        }
    }
}
