package com.eclectusstudio.eclectusIndustries.items;

import com.eclectusstudio.eclectusIndustries.api.MachineItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
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
}
