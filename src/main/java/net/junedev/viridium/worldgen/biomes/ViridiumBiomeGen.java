package net.junedev.viridium.worldgen.biomes;

import java.util.Random;

import net.junedev.viridium.utils.parser.BlockParser;
import net.junedev.viridium.worldgen.features.PlantPlacement;
import net.junedev.viridium.worldgen.features.WorldGenBiomeGrass;
import net.junedev.viridium.worldgen.features.WorldGenWeightedFlowers;
import net.junedev.viridium.worldgen.treegen.TreeRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ViridiumBiomeGen extends BiomeGenBase {

    private Integer grassColorOverride;
    private Integer foliageColorOverride;

    public Block baseTopBlock = Blocks.grass;
    public int baseTopBlockMeta;
    public Block baseFillerBlock = Blocks.dirt;
    private ViridiumBiomeDefinition.SurfacePatch[] patches = new ViridiumBiomeDefinition.SurfacePatch[0];
    private WeightedSelector<ViridiumBiomeDefinition.TreeEntry> treeSelector;
    private WeightedSelector<PlantVariant> grassSelector;
    private WeightedSelector<PlantVariant> flowerSelector;

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
        field_150604_aj = baseTopBlockMeta;
        fillerBlock = baseFillerBlock;

        for (ViridiumBiomeDefinition.SurfacePatch patch : patches) {
            if (noise > patch.minNoise) {
                setTopBlock(BlockParser.getBlock(patch.topBlockId));
                field_150604_aj = patch.topBlockMeta != null ? patch.topBlockMeta
                    : (patch.topBlockId == null ? baseTopBlockMeta : 0);
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
        if (treeSelector == null || treeSelector.isEmpty()) return super.func_150567_a(random);
        ViridiumBiomeDefinition.TreeEntry tree = treeSelector.select(random);
        return TreeRegistry.createGenerator(tree.id, tree.variant, false);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random random) {
        if (grassSelector == null || grassSelector.isEmpty()) return super.getRandomWorldGenForGrass(random);

        PlantVariant selectedGrass = grassSelector.select(random);
        return new WorldGenBiomeGrass(selectedGrass);
    }

    public void setSmallPatches(ViridiumBiomeDefinition.SmallPatch[] smallPatches) {
        viridiumDecorator.smallPatches = smallPatches;
    }

    public void setExtraDecoration(ViridiumBiomeDefinition.Decoration decoration) {
        viridiumDecorator.waterLakeChance = decoration.waterLakeChance;
        viridiumDecorator.melonChance = decoration.melonChance;
    }

    public void setBlobs(ViridiumBiomeDefinition.Blob[] blobs) {
        viridiumDecorator.blobs = blobs;
    }

    public void setBushes(ViridiumBiomeDefinition.Blob[] bushes) {
        viridiumDecorator.bushes = bushes;
    }

    public void setTreeSelector(WeightedSelector<ViridiumBiomeDefinition.TreeEntry> treeSelector) {
        this.treeSelector = treeSelector;
    }

    public void setGrassSelector(WeightedSelector<PlantVariant> grassSelector) {
        this.grassSelector = grassSelector;
    }

    public void setFlowerSelector(WeightedSelector<PlantVariant> selector) {
        flowerSelector = selector;
        viridiumDecorator.yellowFlowerGen = new WorldGenWeightedFlowers(selector);
    }

    @Override
    public void plantFlower(World world, Random random, int x, int y, int z) {
        if (flowerSelector == null) {
            super.plantFlower(world, random, x, y, z);
        } else if (!flowerSelector.isEmpty()) {
            PlantPlacement.place(world, flowerSelector.select(random), x, y, z);
        }
    }

}
