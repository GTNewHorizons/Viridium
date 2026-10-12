package net.junedev.viridium;

import java.util.HashMap;
import java.util.Map;

import net.junedev.viridium.utils.parser.BlockParser;
import net.junedev.viridium.utils.parser.ColorParser;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeDefinition;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeDefinitionLoader;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeGen;
import net.junedev.viridium.worldgen.biomes.WeightedSelector;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class ViriBiomes {

    private int currentBiomeIndex = 100;
    private final Map<String, WorldGenAbstractTree> treeGenerators = new HashMap<>();

    public void preInit() {

        currentBiomeIndex = Config.firstBiomeId;
        registerTreeGenerators();

        chaparral = registerBiomeFromFile("chaparral.json");
        cherry_blossom_grove = registerBiomeFromFile("cherry_blossom_grove.json");
        coniferous_forest = registerBiomeFromFile("coniferous_forest.json");
        dead_forest = registerBiomeFromFile("dead_forest.json");
        deciduous_forest = registerBiomeFromFile("deciduous_forest.json");
        fen = registerBiomeFromFile("fen.json");
        fungi_forest = registerBiomeFromFile("fungi_forest.json");
        heathland = registerBiomeFromFile("heathland.json");
        jade_cliffs = registerBiomeFromFile("jade_cliffs.json");
        lavender_fields = registerBiomeFromFile("lavender_fields.json");
        meadow = registerBiomeFromFile("meadow.json");
        prairie = registerBiomeFromFile("prairie.json");
        shrubland = registerBiomeFromFile("shrubland.json");
        woodland = registerBiomeFromFile("woodland.json");
    }

    private void registerTreeGenerators() {
        treeGenerators.put("minecraft:oak", new WorldGenTrees(false));
        treeGenerators.put("minecraft:tall_oak", new WorldGenBigTree(false));
        treeGenerators.put("minecraft:birch", new WorldGenForest(false, false));
        treeGenerators.put("minecraft:tall_birch", new WorldGenForest(false, true));
        treeGenerators.put("minecraft:acacia", new WorldGenSavannaTree(false));
        treeGenerators.put("minecraft:dark_oak", new WorldGenCanopyTree(false));
        treeGenerators.put("minecraft:spruce", new WorldGenTaiga2(false));
        treeGenerators.put("minecraft:tall_spruce", new WorldGenMegaPineTree(false, true));
        treeGenerators.put("minecraft:jungle", new WorldGenTrees(false, 4, 3, 3, true));
        treeGenerators.put("minecraft:tall_jungle", new WorldGenMegaJungle(false, 10, 20, 3, 3));
    }

    BiomeGenBase registerBiomeFromFile(String path) {
        if (path == null) return null;

        ViridiumBiomeDefinition definition = ViridiumBiomeDefinitionLoader.loadBuiltin(path);

        // I made the game crash to be sure that the dev knows that there is a biome which was not correctly registered.
        if (definition == null) throw new RuntimeException(
            "Biome in path " + path + " was not correctly registered. Check logs to see what failed.");

        return registerBiome(definition);
    }

    BiomeGenBase registerBiome(ViridiumBiomeDefinition definition) {
        if (currentBiomeIndex > 255) {
            throw new IllegalStateException("Maximum biome index reached. Cannot add another biome.");
        }
        if (BiomeGenBase.getBiomeGenArray()[currentBiomeIndex] != null) {
            throw new IllegalStateException("Biome ID " + currentBiomeIndex + " is already occupied.");
        }

        ViridiumBiomeGen biome = new ViridiumBiomeGen(currentBiomeIndex);

        biome.setBiomeName(definition.name);
        biome.setTemperatureRainfall(definition.climate.temperature, definition.climate.rainfall);
        if (!definition.climate.rain) biome.setDisableRain();

        biome.setHeight(
            new BiomeGenBase.Height(definition.terrain.height.baseHeight, definition.terrain.height.heightVariation));

        if (definition.appearance.mapColor != null)
            biome.setMapColor(ColorParser.parseRgb(definition.appearance.mapColor));
        if (definition.appearance.grassColor != null)
            biome.setGrassColorOverride(ColorParser.parseRgb(definition.appearance.grassColor));
        if (definition.appearance.foliageColor != null)
            biome.setFoliageColorOverride(ColorParser.parseRgb(definition.appearance.foliageColor));

        if (definition.terrain.surface.topBlockId != null) {
            Block topBlock = BlockParser.getBlock(definition.terrain.surface.topBlockId);
            if (topBlock != null) biome.baseTopBlock = topBlock;
        }

        if (definition.terrain.surface.fillerBlockId != null) {
            Block fillerBlock = BlockParser.getBlock(definition.terrain.surface.fillerBlockId);
            if (fillerBlock != null) biome.baseFillerBlock = fillerBlock;
        }

        biome.setSurfacePatches(definition.terrain.surface.patches);
        biome.setSmallPatches(definition.decoration.features.smallPatches);
        biome.setBlobs(definition.decoration.features.blobs);
        biome.setBushes(definition.decoration.features.bushes);
        biome.setTreeSelector(createTreeSelector(definition.decoration.trees));
        biome.setGrass(definition.decoration.grass);
        biome.setFlowers(definition.decoration.flowers);

        biome.theBiomeDecorator.treesPerChunk = definition.decoration.treesPerChunk;
        biome.theBiomeDecorator.grassPerChunk = definition.decoration.grassPerChunk;
        biome.theBiomeDecorator.flowersPerChunk = definition.decoration.flowersPerChunk;

        BiomeDictionary.registerBiomeType(biome, definition.generation.dictionaryTypes);
        BiomeManager.addBiome(
            definition.generation.climateType,
            new BiomeManager.BiomeEntry(biome, definition.generation.weight));

        currentBiomeIndex++;

        return biome;
    }

    private WeightedSelector<WorldGenAbstractTree> createTreeSelector(ViridiumBiomeDefinition.TreeEntry[] entries) {
        WeightedSelector<WorldGenAbstractTree> selector = new WeightedSelector<>();
        if (entries == null) return selector;

        for (ViridiumBiomeDefinition.TreeEntry entry : entries) {
            if (entry == null || entry.id == null) {
                throw new IllegalArgumentException("Tree entries need an id and a positive weight");
            }
            WorldGenAbstractTree generator = treeGenerators.get(entry.id);
            if (generator == null) throw new IllegalArgumentException("Unknown tree generator: " + entry.id);
            selector.add(generator, entry.weight);
        }

        return selector;
    }

    public static BiomeGenBase chaparral;
    public static BiomeGenBase cherry_blossom_grove;
    public static BiomeGenBase coniferous_forest;
    public static BiomeGenBase dead_forest;
    public static BiomeGenBase deciduous_forest;
    public static BiomeGenBase fen;
    public static BiomeGenBase fungi_forest;
    public static BiomeGenBase heathland;
    public static BiomeGenBase jade_cliffs;
    public static BiomeGenBase lavender_fields;
    public static BiomeGenBase meadow;
    public static BiomeGenBase prairie;
    public static BiomeGenBase shrubland;
    public static BiomeGenBase woodland;
}
