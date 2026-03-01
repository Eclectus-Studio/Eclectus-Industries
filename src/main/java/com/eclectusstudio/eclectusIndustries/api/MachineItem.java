package com.eclectusstudio.eclectusIndustries.api;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public interface MachineItem {
    ItemStack getItem();
    String getName();
    Recipe getRecipe();
    Machine createMachine(Block block);
    Material baseBlock();
}
