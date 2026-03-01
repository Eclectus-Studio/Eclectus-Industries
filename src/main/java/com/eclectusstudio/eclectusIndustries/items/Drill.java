package com.eclectusstudio.eclectusIndustries.items;

import com.eclectusstudio.eclectusIndustries.Main;
import com.eclectusstudio.eclectusIndustries.api.Machine;
import com.eclectusstudio.eclectusIndustries.api.MachineItem;
import com.eclectusstudio.eclectusIndustries.machines.CobbleBreaker;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Drill implements MachineItem {

    @Override
    public ItemStack getItem() {
        ItemStack furnace = new ItemStack(Material.FURNACE);  // Use furnace as the base item
        ItemMeta meta = furnace.getItemMeta();

        if (meta != null) {
            // Set a custom name for the furnace
            meta.setDisplayName("§7Drill");  // §7 = gray color

            // Add a tooltip (lore)
            meta.setLore(List.of(
                    "§8Industrial Grade Machine", // §8 = dark gray
                    "§fBreaks cobblestone and stores it", // §f = white
                    "§finto a connected chest automatically."
            ));

            // Add a custom tag to identify it as a "drill"
            meta.getPersistentDataContainer().set(new NamespacedKey("eclectusindustries", "machine"), PersistentDataType.STRING, "drill");
            furnace.setItemMeta(meta);
        }

        return furnace;
    }

    @Override
    public String getName(){
        return "drill";
    }

    @Override
    public Recipe getRecipe() {
        NamespacedKey key = new NamespacedKey(Main.plugin, "drill");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("   ", " X ","YZY");

        recipe.setIngredient('X', Material.IRON_PICKAXE);
        recipe.setIngredient('Y', Material.TORCH);
        recipe.setIngredient('Z', Material.FURNACE);

        return recipe;
    }

    @Override
    public Machine createMachine(Block block) {
        return new CobbleBreaker(block);
    }

    @Override
    public Material baseBlock() {
        return Material.FURNACE;
    }
}
