package net.junedev.viridium.worldgen.biomes;

import java.util.Random;

import net.junedev.viridium.utils.parser.BlockParser;
import net.junedev.viridium.worldgen.features.WorldGenSmallPatch;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ViridiumBiomeGen extends BiomeGenBase {

    private Integer grassColorOverride;
    private Integer foliageColorOverride;

    public Block baseTopBlock = Blocks.grass;
    public Block baseFillerBlock = Blocks.dirt;
    private ViridiumBiomeDefinition.SurfacePatch[] patches = new ViridiumBiomeDefinition.SurfacePatch[0];
    private ViridiumBiomeDefinition.SmallPatch[] smallPatches = new ViridiumBiomeDefinition.SmallPatch[0];
    private ViridiumBiomeDefinition.Blob[] blobs = new ViridiumBiomeDefinition.Blob[0];

    public ViridiumBiomeGen(int id) {
        super(id);
    }

    // Color overrides. If unused, uses the temperature/rainfall calculation.
    public ViridiumBiomeGen setMapColor(Integer color) {
        if (color == null) return this;
        setColor(color);
        return this;
    }

    public ViridiumBiomeGen setGrassColorOverride(Integer color) {
        if (color == null) return this;
        grassColorOverride = color;
        return this;
    }

    public ViridiumBiomeGen setFoliageColorOverride(Integer color) {
        if (color == null) return this;
        foliageColorOverride = color;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
        return grassColorOverride != null ? grassColorOverride
            : super.getBiomeGrassColor(p_150558_1_, p_150558_2_, p_150558_3_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
        return foliageColorOverride != null ? foliageColorOverride
            : super.getBiomeFoliageColor(p_150571_1_, p_150571_2_, p_150571_3_);
    }

    // Surface modifiers
    public void setSurfacePatches(ViridiumBiomeDefinition.SurfacePatch[] patches) {
        this.patches = patches;
    }

    @Override
    public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] metadata, int x, int z,
        double noise) {

        topBlock = baseTopBlock;
        fillerBlock = baseFillerBlock;

        for (ViridiumBiomeDefinition.SurfacePatch patch : patches) {
            if (noise > patch.minNoise) {
                setTopBlock(BlockParser.getBlock(patch.topBlockId));
                setFillerBlock(BlockParser.getBlock(patch.fillerBlockId));
            }
        }

        genBiomeTerrain(world, random, blocks, metadata, x, z, noise);
    }

    public ViridiumBiomeGen setTopBlock(Block block) {
        if (block == null) {
            topBlock = baseTopBlock;
        } else {
            topBlock = block;
        }
        return this;
    }

    public ViridiumBiomeGen setFillerBlock(Block block) {
        if (block == null) {
            fillerBlock = baseFillerBlock;
        } else {
            fillerBlock = block;
        }
        return this;
    }

    // Decoration
    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {

        super.decorate(world, random, chunkX, chunkZ);

        if (!TerrainGen.decorate(world, random, chunkX, chunkZ, DecorateBiomeEvent.Decorate.EventType.CUSTOM)) {
            return;
        }

        for (ViridiumBiomeDefinition.SmallPatch patch : smallPatches) {
            if (random.nextInt(patch.frequency) == 0) {
                int x = chunkX + random.nextInt(16) + 8;
                int z = chunkZ + random.nextInt(16) + 8;
                int y = world.getHeightValue(x, z);

                new WorldGenSmallPatch(BlockParser.getBlock(patch.blockId, Blocks.beacon), patch.radius)
                    .generate(world, random, x, y, z);
            }
        }

        for (ViridiumBiomeDefinition.Blob blob : blobs) {
            if (random.nextInt(blob.frequency) == 0) {
                int x = chunkX + random.nextInt(16) + 8;
                int z = chunkZ + random.nextInt(16) + 8;
                int y = world.getHeightValue(x, z);

                new WorldGenBlockBlob(BlockParser.getBlock(blob.blockId, Blocks.diamond_block), blob.radius)
                    .generate(world, random, x, y, z);
            }
        }

    }

    public void setSmallPatches(ViridiumBiomeDefinition.SmallPatch[] smallPatches) {
        this.smallPatches = smallPatches;
    }

    public void setBlobs(ViridiumBiomeDefinition.Blob[] blobs) {
        this.blobs = blobs;
    }

}
