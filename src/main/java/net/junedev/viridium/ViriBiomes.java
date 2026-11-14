package net.junedev.viridium;

import net.junedev.viridium.blocks.TallPlantBlock;
import net.junedev.viridium.utils.parser.BlockParser;
import net.junedev.viridium.utils.parser.ColorParser;
import net.junedev.viridium.worldgen.biomes.PlantVariant;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeDefinition;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeDefinitionLoader;
import net.junedev.viridium.worldgen.biomes.ViridiumBiomeGen;
import net.junedev.viridium.worldgen.biomes.WeightedSelector;
import net.junedev.viridium.worldgen.treegen.TreeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class ViriBiomes {

    private int currentBiomeIndex = 100;

    public void preInit() {

        currentBiomeIndex = Config.firstBiomeId;

        bamboo_forest = registerBiomeFromFile("bamboo_forest.json");
        bayou = registerBiomeFromFile("bayou.json");
        bog = registerBiomeFromFile("bog.json");
        boreal_forest = registerBiomeFromFile("boreal_forest.json");
        brushland = registerBiomeFromFile("brushland.json");
        canyon = registerBiomeFromFile("canyon.json");
        chaparral = registerBiomeFromFile("chaparral.json");
        cherry_blossom_grove = registerBiomeFromFile("cherry_blossom_grove.json");
        coniferous_forest = registerBiomeFromFile("coniferous_forest.json");
        dead_forest = registerBiomeFromFile("dead_forest.json");
        dead_swamp = registerBiomeFromFile("dead_swamp.json");
        deciduous_forest = registerBiomeFromFile("deciduous_forest.json");
        fen = registerBiomeFromFile("fen.json");
        frost_forest = registerBiomeFromFile("frost_forest.json");
        fungi_forest = registerBiomeFromFile("fungi_forest.json");
        garden = registerBiomeFromFile("garden.json");
        grove = registerBiomeFromFile("grove.json");
        heathland = registerBiomeFromFile("heathland.json");
        jade_cliffs = registerBiomeFromFile("jade_cliffs.json");
        lavender_fields = registerBiomeFromFile("lavender_fields.json");
        lush_desert = registerBiomeFromFile("lush_desert.json");
        lush_swamp = registerBiomeFromFile("lush_swamp.json");
        maple_woods = registerBiomeFromFile("maple_woods.json");
        meadow = registerBiomeFromFile("meadow.json");
        mountain = registerBiomeFromFile("mountain.json");
        mystic_grove = registerBiomeFromFile("mystic_grove.json");
        outback = registerBiomeFromFile("outback.json");
        prairie = registerBiomeFromFile("prairie.json");
        rainforest = registerBiomeFromFile("rainforest.json");
        redwood_forest = registerBiomeFromFile("redwood_forest.json");
        sacred_springs = registerBiomeFromFile("sacred_springs.json");
        seasonal_forest = registerBiomeFromFile("seasonal_forest.json");
        shield = registerBiomeFromFile("shield.json");
        shrubland = registerBiomeFromFile("shrubland.json");
        sludgepit = registerBiomeFromFile("sludgepit.json");
        snowy_coniferous_forest = registerBiomeFromFile("snowy_coniferous_forest.json");
        temperate_rainforest = registerBiomeFromFile("temperate_rainforest.json");
        tropical_rainforest = registerBiomeFromFile("tropical_rainforest.json");
        tundra = registerBiomeFromFile("tundra.json");
        wetland = registerBiomeFromFile("wetland.json");
        woodland = registerBiomeFromFile("woodland.json");
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
        // Fixed vanilla reservations: never shift existing IDs according to other mods' load order.
        while (isVanillaBiomeId(currentBiomeIndex)) currentBiomeIndex++;
        if (currentBiomeIndex > 255) {
            throw new IllegalStateException("Maximum biome index reached. Cannot add another biome.");
        }
        if (BiomeGenBase.getBiomeGenArray()[currentBiomeIndex] != null) {
            throw new IllegalStateException("Biome ID " + currentBiomeIndex + " is already occupied.");
        }

        ViridiumBiomeGen biome = new ViridiumBiomeGen(currentBiomeIndex);

        biome.setBiomeName(definition.name);
        biome.setTemperatureRainfall(definition.climate.minecraftTemperature(), definition.climate.minecraftRainfall());
        if (!definition.climate.rain) biome.setDisableRain();
        if (definition.climate.snow) biome.setEnableSnow();

        biome.setHeight(
            new BiomeGenBase.Height(definition.terrain.height.baseHeight, definition.terrain.height.heightVariation));

        if (definition.appearance.mapColor != null)
            biome.setMapColor(ColorParser.parseRgb(definition.appearance.mapColor));
        if (definition.appearance.grassColor != null)
            biome.setGrassColorOverride(ColorParser.parseRgb(definition.appearance.grassColor));
        if (definition.appearance.foliageColor != null)
            biome.setFoliageColorOverride(ColorParser.parseRgb(definition.appearance.foliageColor));
        if (definition.appearance.waterColor != null)
            biome.waterColorMultiplier = ColorParser.parseRgb(definition.appearance.waterColor);

        biome.baseTopBlockMeta = definition.terrain.surface.topBlockMeta;

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
        biome.setGrassSelector(createPlantSelector(definition.decoration.grass));
        biome.setFlowerSelector(createPlantSelector(definition.decoration.flowers));

        biome.theBiomeDecorator.treesPerChunk = definition.decoration.treesPerChunk;
        biome.theBiomeDecorator.grassPerChunk = definition.decoration.grassPerChunk;
        biome.theBiomeDecorator.flowersPerChunk = definition.decoration.flowersPerChunk;
        biome.theBiomeDecorator.deadBushPerChunk = definition.decoration.deadBushPerChunk;
        biome.theBiomeDecorator.reedsPerChunk = definition.decoration.reedsPerChunk;
        biome.theBiomeDecorator.waterlilyPerChunk = definition.decoration.waterlilyPerChunk;
        biome.theBiomeDecorator.mushroomsPerChunk = definition.decoration.mushroomsPerChunk;
        biome.theBiomeDecorator.bigMushroomsPerChunk = definition.decoration.bigMushroomsPerChunk;
        biome.setExtraDecoration(definition.decoration);

        BiomeDictionary.registerBiomeType(biome, definition.generation.dictionaryTypes);
        BiomeManager.addBiome(
            definition.generation.climateType,
            new BiomeManager.BiomeEntry(biome, definition.generation.weight));

        currentBiomeIndex++;

        return biome;
    }

    private static boolean isVanillaBiomeId(int id) {
        if (id >= 0 && id <= 39) return true;
        return switch (id) {
            case 129, 130, 131, 132, 133, 134, 140, 149, 151, 155, 156, 157, 158, 160, 161, 162, 163, 164, 165, 166, 167 -> true;
            default -> false;
        };
    }

    private WeightedSelector<ViridiumBiomeDefinition.TreeEntry> createTreeSelector(
        ViridiumBiomeDefinition.TreeEntry[] entries) {
        WeightedSelector<ViridiumBiomeDefinition.TreeEntry> selector = new WeightedSelector<>();
        if (entries == null) return selector;

        for (ViridiumBiomeDefinition.TreeEntry entry : entries) {
            if (entry == null || entry.id == null) {
                throw new IllegalArgumentException("Tree entries need an id and a positive weight");
            }
            if (!TreeRegistry.contains(entry.id, entry.variant))
                throw new IllegalArgumentException("Unknown tree or variant: " + entry.id + " / " + entry.variant);
            selector.add(entry, entry.weight);
        }

        return selector;
    }

    private WeightedSelector<PlantVariant> createPlantSelector(ViridiumBiomeDefinition.VegetationEntry[] entries) {
        WeightedSelector<PlantVariant> selector = new WeightedSelector<>();
        if (entries == null) return selector;

        for (ViridiumBiomeDefinition.VegetationEntry entry : entries) {
            if (entry == null || entry.blockId == null) {
                throw new IllegalArgumentException("Vegetation entries need an id and a positive weight");
            }
            if (entry.meta < 0 || entry.meta > 15) {
                throw new IllegalArgumentException("Invalid vegetation metadata: " + entry.blockId);
            }
            PlantVariant generator = PlantVariant.create(entry.blockId, entry.meta);
            if (generator == null) throw new IllegalArgumentException("Unknown vegetation block: " + entry.blockId);
            if (generator.block instanceof TallPlantBlock && entry.meta != 0) {
                throw new IllegalArgumentException("Tall plants require bottom metadata 0: " + entry.blockId);
            }
            if (generator.block instanceof BlockDoublePlant && entry.meta > 5) {
                throw new IllegalArgumentException(
                    "Double plants require a bottom variant from 0 to 5: " + entry.blockId);
            }
            selector.add(generator, entry.weight);
        }

        return selector;
    }

    public static BiomeGenBase bamboo_forest;
    public static BiomeGenBase bayou;
    public static BiomeGenBase bog;
    public static BiomeGenBase boreal_forest;
    public static BiomeGenBase brushland;
    public static BiomeGenBase canyon;
    public static BiomeGenBase chaparral;
    public static BiomeGenBase cherry_blossom_grove;
    public static BiomeGenBase coniferous_forest;
    public static BiomeGenBase dead_forest;
    public static BiomeGenBase dead_swamp;
    public static BiomeGenBase deciduous_forest;
    public static BiomeGenBase fen;
    public static BiomeGenBase frost_forest;
    public static BiomeGenBase fungi_forest;
    public static BiomeGenBase garden;
    public static BiomeGenBase grove;
    public static BiomeGenBase heathland;
    public static BiomeGenBase jade_cliffs;
    public static BiomeGenBase lavender_fields;
    public static BiomeGenBase lush_desert;
    public static BiomeGenBase lush_swamp;
    public static BiomeGenBase maple_woods;
    public static BiomeGenBase meadow;
    public static BiomeGenBase mountain;
    public static BiomeGenBase mystic_grove;
    public static BiomeGenBase outback;
    public static BiomeGenBase prairie;
    public static BiomeGenBase rainforest;
    public static BiomeGenBase redwood_forest;
    public static BiomeGenBase sacred_springs;
    public static BiomeGenBase seasonal_forest;
    public static BiomeGenBase shield;
    public static BiomeGenBase shrubland;
    public static BiomeGenBase sludgepit;
    public static BiomeGenBase snowy_coniferous_forest;
    public static BiomeGenBase temperate_rainforest;
    public static BiomeGenBase tropical_rainforest;
    public static BiomeGenBase tundra;
    public static BiomeGenBase wetland;
    public static BiomeGenBase woodland;
}
