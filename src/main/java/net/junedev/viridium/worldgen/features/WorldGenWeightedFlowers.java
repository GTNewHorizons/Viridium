package net.junedev.viridium.worldgen.features;

import java.util.Random;

import net.junedev.viridium.worldgen.biomes.PlantVariant;
import net.junedev.viridium.worldgen.biomes.WeightedSelector;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;

/** Uses the vanilla flower decoration loop and Forge FLOWERS event, with a weighted plant per patch. */
public final class WorldGenWeightedFlowers extends WorldGenFlowers {

    private final WeightedSelector<PlantVariant> selector;

    public WorldGenWeightedFlowers(WeightedSelector<PlantVariant> selector) {
        super(Blocks.red_flower);
        this.selector = selector;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (selector.isEmpty()) return false;
        // One flower for all the patch
        PlantVariant plant = selector.select(random);
        boolean placed = false;
        for (int i = 0; i < 64; i++) {
            int px = x + random.nextInt(8) - random.nextInt(8);
            int py = y + random.nextInt(4) - random.nextInt(4);
            int pz = z + random.nextInt(8) - random.nextInt(8);
            placed |= PlantPlacement.place(world, plant, px, py, pz);
        }
        return placed;
    }

}
