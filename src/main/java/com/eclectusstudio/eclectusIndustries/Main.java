package com.eclectusstudio.eclectusIndustries;

import com.eclectusstudio.eclectusIndustries.api.MachineItems;
import com.eclectusstudio.eclectusIndustries.commands.GiveMachineItem;
import com.eclectusstudio.eclectusIndustries.commands.tabcompleter.GiveMachineItemTabCompleter;
import com.eclectusstudio.eclectusIndustries.event.OnBlockBreak;
import com.eclectusstudio.eclectusIndustries.event.OnBlockPlace;
import com.eclectusstudio.eclectusIndustries.items.Drill;
import com.eclectusstudio.eclectusIndustries.tick.Ticker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Events
        Bukkit.getPluginManager().registerEvents(new OnBlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new OnBlockBreak(), this);


        //Register Machine Items
        MachineItems.registerMachineItem(new Drill());

        //Register Commands
        getCommand("givemachineitem").setExecutor(new GiveMachineItem());

        //Register Tab Completer
        getCommand("givemachineitem").setTabCompleter(new GiveMachineItemTabCompleter());

        //Tick Machines
        new BukkitRunnable() {
            @Override
            public void run() {
                // Run the machines
                Ticker.tick();
            }
        }.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
