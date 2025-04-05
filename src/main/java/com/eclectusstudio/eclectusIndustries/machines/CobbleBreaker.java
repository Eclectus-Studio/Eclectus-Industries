package com.eclectusstudio.eclectusIndustries.machines;

import com.eclectusstudio.eclectusIndustries.api.BurnableItems;
import com.eclectusstudio.eclectusIndustries.api.Machine;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.Bukkit;

public class CobbleBreaker implements Machine {

    private final Block furnaceBlock;

    public CobbleBreaker(Block furnaceBlock) {
        this.furnaceBlock = furnaceBlock;
    }

    @Override
    public void tick() {
        if (furnaceBlock.getType() == Material.FURNACE) {
            Furnace furnace = (Furnace) furnaceBlock.getState();
            FurnaceInventory inv = furnace.getInventory();
            System.out.println(furnace.getBurnTime());

            ItemStack fuel = inv.getFuel(); // Get the fuel in the furnace

            // If the furnace isn't burning but has fuel, consume it and add burn time
            if (furnace.getBurnTime() == 0 && fuel != null) {
                int burnTime = BurnableItems.getBurnTime(fuel.getType());

                if (burnTime > 0) { // Ensure it's a valid fuel
                    furnace.setBurnTime((short) burnTime);

                    // Remove one unit of fuel
                    if (fuel.getAmount() > 1) {
                        fuel.setAmount(fuel.getAmount() - 1);
                        furnace.getInventory().setFuel(fuel);
                        furnace.update();
                    } else {
                        furnace.getInventory().setFuel(null); // If last item, remove fuel
                        furnace.update();
                    }

                    furnace.update();
                }
            }

            if(furnace.getBurnTime() > 0){
                // Get the facing direction of the furnace
                BlockState furnaceState = furnaceBlock.getState();
                BlockData furnaceData = furnaceState.getBlockData();
                BlockFace facingDirection = ((org.bukkit.block.data.type.Furnace) furnaceData).getFacing();

                // Get the blocks relative to the furnace's facing direction
                Block behindBlock = furnaceBlock.getRelative(facingDirection.getOppositeFace()); // Chest is behind
                Block frontBlock = furnaceBlock.getRelative(facingDirection); // Cobblestone in front
                System.out.println("Behind: " + behindBlock.getType());
                System.out.println("Front: " + frontBlock.getType());

                // Check if the chest is behind the furnace
                if (behindBlock.getType() == Material.CHEST) {
                    Chest chest = (Chest) behindBlock.getState();
                    Inventory inventory = chest.getInventory();

                    // If there's cobblestone in front, break it and add it to the chest
                    if (frontBlock.getType() == Material.COBBLESTONE) {
                        frontBlock.setType(Material.AIR); // Remove cobblestone

                        // Drop cobblestone into the chest
                        ItemStack drop = new ItemStack(Material.COBBLESTONE);
                        inventory.addItem(drop);
                    }
                }
            }
        }
    }

    @Override
    public Block getBlock() {
        return this.furnaceBlock;
    }
}
