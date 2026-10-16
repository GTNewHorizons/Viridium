package net.junedev.viridium.worldgen.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBush extends WorldGenerator {

    private Block block;
    private int radius;

    public WorldGenBush(Block block, int radius) {
        this.block = block;
        this.radius = radius;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        while (true) {
            if (y > 3) {
                label63: {
                    if (!world.isAirBlock(x, y - 1, z)) {
                        Block block = world.getBlock(x, y - 1, z);

                        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.stone) {
                            break label63;
                        }
                    }

                    --y;
                    continue;
                }
            }

            if (y <= 3) {
                return false;
            }

            int r = this.radius;
            int verDelta = random.nextInt(2);
            int horDelta = verDelta + random.nextInt(2);

            int j1 = r + verDelta;

            int i1 = r + horDelta;
            int k1 = r + horDelta;

            for (int i2 = x - i1; i2 <= x + i1; ++i2) {
                for (int k2 = z - k1; k2 <= z + k1; ++k2) {
                    for (int j2 = y - j1; j2 <= y + j1; ++j2) {
                        float f1 = (float) (i2 - x) / i1;
                        float f2 = (float) (k2 - z) / k1;
                        float f3 = (float) (j2 - y) / j1;

                        if (f1 * f1 + f2 * f2 + f3 * f3 <= 1.0 && world.getBlock(i2, j2, k2)
                            .isReplaceable(world, x, y, z)) {
                            world.setBlock(i2, j2, k2, this.block, 0, 4);
                        }
                    }
                }
            }


            return true;
        }
    }
}
