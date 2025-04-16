package com.eclectusstudio.eclectusIndustries.data;

import com.eclectusstudio.eclectusIndustries.api.Machine;
import com.eclectusstudio.eclectusIndustries.machines.CobbleBreaker;
import com.eclectusstudio.eclectusIndustries.machines.Machines;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadMachines {

    private static final Gson gson = new Gson(); // Gson instance for deserialization

    public static void loadMachinesFromWorld(World world, Plugin plugin) {
        // Read the JSON file with machine data
        File dataFile = new File(plugin.getDataFolder(), "machines.json");
        if (!dataFile.exists()) {
            plugin.getLogger().info("No saved machines found.");
            return;
        }

        try (FileReader reader = new FileReader(dataFile)) {
            JsonArray machineArray = gson.fromJson(reader, JsonArray.class);

            // Loop through each machine in the JSON array and create the machine objects
            for (int i = 0; i < machineArray.size(); i++) {
                JsonObject machineJson = machineArray.get(i).getAsJsonObject();

                // Deserialize the machine data
                MachineData machineData = gson.fromJson(machineJson.get("machine"), MachineData.class);

                // Create the machine from the data
                World worldLoaded = Bukkit.getWorld(machineData.getWorld());
                if (worldLoaded == null) continue;

                Block block = worldLoaded.getBlockAt(machineData.getX(), machineData.getY(), machineData.getZ());
                Machine machine = createMachineByType(block, machineData.getMachineType());

                if (machine != null) {
                    Machines.addMachine(machine);
                }
            }
            plugin.getLogger().info("Loaded machines from machines.json");

        } catch (IOException e) {
            plugin.getLogger().severe("Failed to load machines from machines.json: " + e.getMessage());
        }
    }

    private static Machine createMachineByType(Block block, String machineType) {
        // Create machine objects based on machineType
        switch (machineType) {
            case "CobbleBreaker":
                return new CobbleBreaker(block);
            // Add more machine types here as needed
            default:
                return null;
        }
    }
}