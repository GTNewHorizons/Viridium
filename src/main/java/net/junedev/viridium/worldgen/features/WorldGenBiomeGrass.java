package net.junedev.viridium.worldgen.features;

import java.util.Random;

import net.junedev.viridium.worldgen.biomes.PlantVariant;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/** Vanilla grass scatter with soil checks and support for multi-block plants. */
public final class WorldGenBiomeGrass extends WorldGenerator {

    private final PlantVariant plant;

    public WorldGenBiomeGrass(PlantVariant plant) {
        this.plant = plant;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        y = Math.min(y, world.getHeight() - 1);
        while (y > 0) {
            Block block = world.getBlock(x, y, z);
            if (!block.isAir(world, x, y, z) && !block.isLeaves(world, x, y, z)) break;
            y--;
        }
        boolean placed = false;
        for (int i = 0; i < 128; i++) {
            int px = x + random.nextInt(8) - random.nextInt(8);
            int py = y + random.nextInt(4) - random.nextInt(4);
            int pz = z + random.nextInt(8) - random.nextInt(8);
            placed |= PlantPlacement.place(world, plant, px, py, pz);
        }
        return placed;
    }
}
