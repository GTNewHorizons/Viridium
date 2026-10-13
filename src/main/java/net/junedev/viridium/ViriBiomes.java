package net.junedev.viridium;

import net.junedev.viridium.utils.parser.BlockParser;
import net.junedev.viridium.utils.parser.ColorParser;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeDefinition;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeDefinitionLoader;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeGen;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class ViriBiomes {

    private int currentBiomeIndex = 100;

    public void preInit() {

        currentBiomeIndex = Config.firstBiomeId;

        chaparral = registerBiomeFromFile("chaparral.json");
        deciduous_forest = registerBiomeFromFile("deciduous_forest.json");
        fen = registerBiomeFromFile("fen.json");
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
            new BiomeGenBase.Height(definition.terrain.height.baseWeight, definition.terrain.height.heightVariation));

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

    public static BiomeGenBase chaparral;
    public static BiomeGenBase deciduous_forest;
    public static BiomeGenBase fen;
}
