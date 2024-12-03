package com.github.GhostNemesis12.nemesisworld;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;

public class ToroidalConverter {
    private final int xMin;
    private final int xMax;
    private final int zMin;
    private final int zMax;

    public ToroidalConverter(int xMin, int xMax, int zMin, int zMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.zMin = zMin;
        this.zMax = zMax;
    }

    public int toToroidalX(int x) {
        return ((x - xMin) % (xMax - xMin) + (xMax - xMin)) % (xMax - xMin) + xMin;
    }

    public int toToroidalZ(int z) {
        return ((z - zMin) % (zMax - zMin) + (zMax - zMin)) % (zMax - zMin) + zMin;
    }

    public BlockPos toToroidalPosition(BlockPos position) {
        int newX = toToroidalX(position.getX());
        int newZ = toToroidalZ(position.getZ());
        return new BlockPos(newX, position.getY(), newZ);
    }
}
