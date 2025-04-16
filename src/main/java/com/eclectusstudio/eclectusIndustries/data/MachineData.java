package com.eclectusstudio.eclectusIndustries.data;

public class MachineData {

    private int x;
    private int y;
    private int z;
    private String world;
    private String machineType;

    // Constructor
    public MachineData(int x, int y, int z, String world, String machineType) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.machineType = machineType;
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }
}