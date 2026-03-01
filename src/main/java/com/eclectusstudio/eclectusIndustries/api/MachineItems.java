package com.eclectusstudio.eclectusIndustries.api;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.ArrayList;
import java.util.List;

public class MachineItems {
    public static List<MachineItem> machineItems = new ArrayList<>();

    public static List<MachineItem> getMachineItems(){
        return machineItems;
    }

    public static MachineItem getMachine(int index) {
        return machineItems.get(index);
    }

    public static void registerMachineItem(MachineItem machineItem){
        machineItems.add(machineItem);
        Bukkit.addRecipe(machineItem.getRecipe());
    }

    public static int getLength(){
        return machineItems.size();
    }
}
