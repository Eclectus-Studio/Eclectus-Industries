package com.eclectusstudio.eclectusIndustries.event;

import com.eclectusstudio.eclectusIndustries.machines.CobbleBreaker;
import com.eclectusstudio.eclectusIndustries.machines.Machines;
import com.eclectusstudio.eclectusIndustries.items.Drill;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class OnBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();

        // Check if the placed block is a furnace
        if (block.getType() == Material.FURNACE) {
            // Check if the furnace has the "drill" tag
            ItemStack placedItem = event.getItemInHand();
            var meta = placedItem.getItemMeta();

            if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey("eclectusindustries", "machine"), PersistentDataType.STRING)) {
                String machineType = meta.getPersistentDataContainer().get(new NamespacedKey("eclectusindustries", "machine"), PersistentDataType.STRING);

                if ("drill".equals(machineType)) {
                    // Register the machine when a furnace is placed as a "drill"
                    CobbleBreaker cobbleBreaker = new CobbleBreaker(block);
                    Machines.addMachine(cobbleBreaker);  // Assuming you have a method to add this machine
                }
            }
        }
    }
}
