package net.junedev.viridium.worldgen.treegen;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import net.junedev.viridium.ViriBlocks;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.minecraft.world.gen.feature.WorldGenerator;

public final class TreeRegistry {

    private static final Map<String, TreeType> TREES = new HashMap<>();

    public static void preInit() {

        register(
            "american_chestnut",
            (notifiesNeighbors) -> createDefaultGenerator(ViriBlocks.american_chestnut, notifiesNeighbors)).withLayout(
                SaplingLayout.TWO_BY_TWO,
                (notifiesNeighbors) -> new AmericanChestnutTreeGen(
                    notifiesNeighbors,
                    ViriBlocks.american_chestnut,
                    17,
                    4,
                    0.30D,
                    5,
                    2,
                    12,
                    3));
        register(
            "pedunculate_oak",
            (notifiesNeighbors) -> createDefaultGenerator(ViriBlocks.pedunculate_oak, notifiesNeighbors)).withLayout(
                SaplingLayout.TWO_BY_TWO,
                (notifiesNeighbors) -> new MediumPedunculateOakTreeGen(
                    notifiesNeighbors,
                    ViriBlocks.pedunculate_oak,
                    10,
                    3,
                    0.40D,
                    7,
                    2,
                    12,
                    3));
        register(
            "coconut_palm",
            (notifiesNeighbors) -> new CoconutPalmTreeGen(notifiesNeighbors, ViriBlocks.coconut_palm),
            true);
    }

    private TreeRegistry() {}

    public static TreeType register(String treeId, Function<Boolean, WorldGenerator> defaultFactory) {
        return register(treeId, defaultFactory, false);
    }

    public static TreeType register(String treeId, Function<Boolean, WorldGenerator> defaultFactory,
        boolean growsOnSand) {
        TreeType tree = new TreeType(Objects.requireNonNull(defaultFactory, "defaultFactory"), growsOnSand);
        TREES.put(Objects.requireNonNull(treeId, "treeId"), tree);
        return tree;
    }

    // Create a new generator for each tree to avoid reusing state from previous generations
    public static WorldGenerator createGenerator(String treeId, WoodBlockSet blockSet, boolean notifiesNeighbors) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? createDefaultGenerator(blockSet, notifiesNeighbors)
            : tree.defaultFactory.apply(notifiesNeighbors);
    }

    public static WorldGenerator createGenerator(String treeId, WoodBlockSet blockSet, SaplingLayout layout,
        boolean notifiesNeighbors) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? createDefaultGenerator(blockSet, notifiesNeighbors)
            : tree.layoutFactories.getOrDefault(layout, tree.defaultFactory)
                .apply(notifiesNeighbors);
    }

    public static boolean hasLayout(String treeId, SaplingLayout layout) {
        TreeType tree = TREES.get(treeId);
        return tree != null && tree.layoutFactories.containsKey(layout);
    }

    private static WorldGenerator createDefaultGenerator(WoodBlockSet blockSet, boolean notifiesNeighbors) {
        return new LargeDeciduousTreeGen(notifiesNeighbors, blockSet, "", 8, 4, 0.30D, 2, 4, 8, 6);
    }

    // TODO: Placeholder while SoilTypeRegistry is introduced
    public static boolean growsOnSand(String treeId) {
        TreeType tree = TREES.get(treeId);
        return tree != null && tree.growsOnSand;
    }

    public static final class TreeType {

        private final Function<Boolean, WorldGenerator> defaultFactory;
        private final Map<SaplingLayout, Function<Boolean, WorldGenerator>> layoutFactories = new EnumMap<>(
            SaplingLayout.class);
        // TODO: This should be substituted by a list of soil types (EnumSet<SoilType>)
        private final boolean growsOnSand;

        private TreeType(Function<Boolean, WorldGenerator> defaultFactory, boolean growsOnSand) {
            this.defaultFactory = defaultFactory;
            this.growsOnSand = growsOnSand;
        }

        public TreeType withLayout(SaplingLayout layout, Function<Boolean, WorldGenerator> generatorFactory) {
            layoutFactories.put(
                Objects.requireNonNull(layout, "layout"),
                Objects.requireNonNull(generatorFactory, "generatorFactory"));
            return this;
        }
    }
}
