package com.eclectusstudio.eclectusIndustries.api;

import org.bukkit.block.Block;

public interface Machine {

    // Method to perform machine operations every tick
    void tick();
    Block getBlock();
}
