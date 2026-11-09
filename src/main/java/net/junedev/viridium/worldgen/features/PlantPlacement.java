package net.junedev.viridium.worldgen.features;

import net.junedev.viridium.blocks.TallPlantBlock;
import net.junedev.viridium.worldgen.biomes.PlantVariant;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.world.World;

/** Shared placement rules for vegetation patches and bonemeal. */
public final class PlantPlacement {

    private PlantPlacement() {}

    public static boolean place(World world, PlantVariant plant, int x, int y, int z) {
        int height = plant.block instanceof TallPlantBlock tall ? tall.getVerticalBlockSize()
            : plant.block instanceof BlockDoublePlant ? 2 : 1;
        if (y < 1 || y + height > world.getHeight()) return false;
        for (int i = 0; i < height; i++) {
            if (!world.blockExists(x, y + i, z) || !world.isAirBlock(x, y + i, z)) return false;
        }
        if (!plant.block.canPlaceBlockAt(world, x, y, z) || !plant.block.canBlockStay(world, x, y, z)) return false;
        if (!world.setBlock(x, y, z, plant.block, plant.meta, 2)) return false;
        for (int i = 1; i < height; i++) {
            world.setBlock(x, y + i, z, plant.block, plant.block instanceof BlockDoublePlant ? 8 : i, 2);
        }
        return true;
    }
}
