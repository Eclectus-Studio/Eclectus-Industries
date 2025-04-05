package com.eclectusstudio.eclectusIndustries.tick;

import com.eclectusstudio.eclectusIndustries.machines.Machines;

public class Ticker {
    public static void tick(){
        Machines.getMachines().forEach((block, machine) -> {
            // Call the tick() method for each machine
            machine.tick();
        });
    }
}
