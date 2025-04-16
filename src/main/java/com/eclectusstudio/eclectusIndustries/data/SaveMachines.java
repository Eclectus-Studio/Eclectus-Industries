package com.eclectusstudio.eclectusIndustries.data;

import com.eclectusstudio.eclectusIndustries.api.Machine;
import com.eclectusstudio.eclectusIndustries.machines.Machines;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveMachines {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveMachinesToWorld(World world, Plugin plugin) {
        JsonArray machineArray = new JsonArray();

        for (Machine machine : Machines.getMachines().values()) {
            Block block = machine.getBlock();

            MachineData machineData = new MachineData(
                    block.getX(), block.getY(), block.getZ(),
                    block.getWorld().getName(),
                    machine.getClass().getSimpleName()
            );

            JsonObject machineJson = new JsonObject();
            machineJson.add("machine", gson.toJsonTree(machineData));

            machineArray.add(machineJson);
        }

        // Ensure plugin data folder exists
        File pluginFolder = plugin.getDataFolder();
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs(); // create the directory
        }

        File dataFile = new File(pluginFolder, "machines.json");

        try (FileWriter writer = new FileWriter(dataFile)) {
            gson.toJson(machineArray, writer);
            plugin.getLogger().info("Saved machines to machines.json");
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save machines to machines.json: " + e.getMessage());
        }
    }
}
