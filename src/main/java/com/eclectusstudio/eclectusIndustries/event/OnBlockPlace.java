package com.eclectusstudio.eclectusIndustries.event;

import com.eclectusstudio.eclectusIndustries.api.MachineItem;
import com.eclectusstudio.eclectusIndustries.api.MachineItems;
import com.eclectusstudio.eclectusIndustries.machines.Machines;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class OnBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();

        for (MachineItem machineItem : MachineItems.getMachineItems()){
            if(machineItem.baseBlock() == block.getType()){
                ItemStack placedItem = event.getItemInHand();
                var meta = placedItem.getItemMeta();

                if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey("eclectusindustries", "machine"), PersistentDataType.STRING)) {
                    String machineType = meta.getPersistentDataContainer().get(new NamespacedKey("eclectusindustries", "machine"), PersistentDataType.STRING);

                    if (machineType != null && machineType.equals(machineItem.getName())) {
                        Machines.addMachine(machineItem.createMachine(block));
                    }
                }
            }
        }
    }
}
