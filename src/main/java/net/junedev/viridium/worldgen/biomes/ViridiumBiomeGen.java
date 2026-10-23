package net.junedev.viridium.worldgen.biomes;

import java.util.Random;

import net.junedev.viridium.utils.parser.BlockParser;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ViridiumBiomeGen extends BiomeGenBase {

    private Integer grassColorOverride;
    private Integer foliageColorOverride;

    public Block baseTopBlock = Blocks.grass;
    public Block baseFillerBlock = Blocks.dirt;
    private ViridiumBiomeDefinition.SurfacePatch[] patches = new ViridiumBiomeDefinition.SurfacePatch[0];
    private WeightedSelector<WorldGenAbstractTree> treeSelector;
    private WeightedSelector<PlantVariant> grassSelector;
    private ViridiumBiomeDefinition.VegetationEntry[] flowers = new ViridiumBiomeDefinition.VegetationEntry[0];

    private ViridiumBiomeDecorator viridiumDecorator;

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
    public BiomeDecorator createBiomeDecorator() {
        viridiumDecorator = new ViridiumBiomeDecorator();
        return getModdedBiomeDecorator(viridiumDecorator);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random) {
        return treeSelector == null || treeSelector.isEmpty() ? super.func_150567_a(random)
            : treeSelector.select(random);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random random) {
        if (grassSelector == null || grassSelector.isEmpty()) return super.getRandomWorldGenForGrass(random);

        PlantVariant selectedGrass = grassSelector.select(random);
        return new WorldGenTallGrass(selectedGrass.block, selectedGrass.meta);
    }

    public void setSmallPatches(ViridiumBiomeDefinition.SmallPatch[] smallPatches) {
        viridiumDecorator.smallPatches = smallPatches;
    }

    public void setBlobs(ViridiumBiomeDefinition.Blob[] blobs) {
        viridiumDecorator.blobs = blobs;
    }

    public void setBushes(ViridiumBiomeDefinition.Blob[] bushes) {
        viridiumDecorator.bushes = bushes;
    }

    public void setTreeSelector(WeightedSelector<WorldGenAbstractTree> treeSelector) {
        this.treeSelector = treeSelector;
    }

    public void setGrassSelector(WeightedSelector<PlantVariant> grassSelector) {
        this.grassSelector = grassSelector;
    }

    public void setFlowers(ViridiumBiomeDefinition.VegetationEntry[] flowers) {
        this.flowers = flowers;
    }

}
