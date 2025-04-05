package com.eclectusstudio.eclectusIndustries.machines;

import com.eclectusstudio.eclectusIndustries.api.Machine;
import org.bukkit.block.Block;

import java.util.HashMap;

public class Machines {

    // Store machine data using a Block as a key (furnace block)
    private static final HashMap<Block, Machine> machines = new HashMap<>();

    // Method to add a new machine
    public static void addMachine(Machine machine) {
        machines.put(machine.getBlock(), machine);
    }

    // Get a machine by its furnace block
    public static Machine getMachine(Block block) {
        return machines.get(block);
    }

    public static void removeMachine(Block block) {
        machines.remove(block);
    }

    public static HashMap<Block, Machine> getMachines(){
        return machines;
    }
}
