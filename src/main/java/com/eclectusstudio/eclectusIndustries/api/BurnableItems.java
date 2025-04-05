package com.eclectusstudio.eclectusIndustries.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class BurnableItems {
    // Method to get burn time of a material
    public static int getBurnTime(Material material) {
        World world = Bukkit.getWorld("world");
        ItemStack itemStack = new ItemStack(material);
        long time = ((CraftWorld) world).getHandle()
                .fuelValues()
                .burnDuration(CraftItemStack.asNMSCopy(itemStack));

        return Math.toIntExact(time);
    }
}
