package net.junedev.viridium.worldgen.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSmallPatch extends WorldGenerator {

    private Block block;
    private int radius;

    public WorldGenSmallPatch(Block block, int radius) {
        this.block = block;
        this.radius = radius;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int l = radius;
        // A bit of randomness
        if (radius > 2) l = random.nextInt(this.radius - 2) + 2;

        byte b0 = 2;

        for (int i1 = x - l; i1 <= x + l; ++i1) {
            for (int j1 = z - l; j1 <= z + l; ++j1) {
                int k1 = i1 - x;
                int l1 = j1 - z;

                int margin = l * l - (k1 * k1 + l1 * l1);

                if (margin >= 0) {
                    for (int i2 = y - b0; i2 <= y + b0; ++i2) {
                        Block block = world.getBlock(i1, i2, j1);

                        if (block == Blocks.dirt || block == Blocks.grass) {
                            if (random.nextInt(l * l) <= margin) world.setBlock(i1, i2, j1, this.block, 0, 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}
