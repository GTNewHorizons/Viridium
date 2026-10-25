package net.junedev.viridium.worldgen.treegen;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.minecraft.world.gen.feature.WorldGenerator;

public final class TreeRegistry {

    private static final Map<String, TreeType> TREES = new HashMap<>();
    private static final TreeType DEFAULT = new TreeType(
        (blockSet, leafVariant) -> new LargeDeciduousTreeGen(true, blockSet, leafVariant, 8, 4, 0.30D, 2, 4, 8, 6),
        false);

    public static void preInit() {
        register(
            "american_chestnut",
            (blockSet, leafVariant) -> new AmericanChestnutTreeGen(true, blockSet, 17, 4, 0.30D, 5, 2, 12, 3));
        register(
            "pedunculate_oak",
            (blockSet, leafVariant) -> new MediumPedunculateOakTreeGen(true, blockSet, 10, 3, 0.40D, 7, 2, 12, 3));
        register("coconut_palm", (blockSet, leafVariant) -> new CoconutPalmTreeGen(true, blockSet), true);
    }

    private TreeRegistry() {}

    public static void register(String treeId, BiFunction<WoodBlockSet, String, WorldGenerator> generatorFactory) {
        register(treeId, generatorFactory, false);
    }

    public static void register(String treeId, BiFunction<WoodBlockSet, String, WorldGenerator> generatorFactory,
        boolean growsOnSand) {
        TREES.put(
            Objects.requireNonNull(treeId, "treeId"),
            new TreeType(Objects.requireNonNull(generatorFactory, "generatorFactory"), growsOnSand));
    }

    // Create a new generator for each tree to avoid reusing state from previous generations
    public static WorldGenerator createGenerator(String treeId, WoodBlockSet blockSet, String leafVariant) {
        return TREES.getOrDefault(treeId, DEFAULT).generatorFactory.apply(blockSet, leafVariant);
    }

    // TODO: Placeholder while SoilTypeRegistry is introduced
    public static boolean growsOnSand(String treeId) {
        return TREES.getOrDefault(treeId, DEFAULT).growsOnSand;
    }

    private static final class TreeType {

        private final BiFunction<WoodBlockSet, String, WorldGenerator> generatorFactory;
        // TODO: This should be substituted by a list of soil types (EnumSet<SoilType>)
        private final boolean growsOnSand;

        private TreeType(BiFunction<WoodBlockSet, String, WorldGenerator> generatorFactory, boolean growsOnSand) {
            this.generatorFactory = generatorFactory;
            this.growsOnSand = growsOnSand;
        }
    }
}
