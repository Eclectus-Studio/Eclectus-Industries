package com.eclectusstudio.eclectusIndustries.api;

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
    }

    public static int getLength(){
        return machineItems.size();
    }
}
