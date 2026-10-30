package net.junedev.viridium.worldgen.treegen;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import net.junedev.viridium.ViriBlocks;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.worldgen.SoilType;
import net.minecraft.block.Block;
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
            (notifiesNeighbors) -> new CoconutPalmTreeGen(notifiesNeighbors, ViriBlocks.coconut_palm))
                .withSoils(SoilType.GENERIC, SoilType.SAND);
    }

    private TreeRegistry() {}

    public static TreeType register(String treeId, Function<Boolean, WorldGenerator> defaultFactory) {
        TreeType tree = new TreeType(Objects.requireNonNull(defaultFactory, "defaultFactory"));
        TREES.put(Objects.requireNonNull(treeId, "treeId"), tree);
        return tree;
    }

    // Create a new generator for each tree to avoid reusing state from previous generations
    public static WorldGenerator createGenerator(String treeId, WoodBlockSet blockSet, boolean notifiesNeighbors) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? createDefaultGenerator(blockSet, notifiesNeighbors)
            : configureSoil(tree.defaultFactory.apply(notifiesNeighbors), tree);
    }

    public static WorldGenerator createGenerator(String treeId, WoodBlockSet blockSet, SaplingLayout layout,
        boolean notifiesNeighbors) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? createDefaultGenerator(blockSet, notifiesNeighbors)
            : configureSoil(
                tree.layoutFactories.getOrDefault(layout, tree.defaultFactory)
                    .apply(notifiesNeighbors),
                tree);
    }

    public static boolean hasLayout(String treeId, SaplingLayout layout) {
        TreeType tree = TREES.get(treeId);
        return tree != null && tree.layoutFactories.containsKey(layout);
    }

    private static WorldGenerator createDefaultGenerator(WoodBlockSet blockSet, boolean notifiesNeighbors) {
        return new LargeDeciduousTreeGen(notifiesNeighbors, blockSet, "", 8, 4, 0.30D, 2, 4, 8, 6);
    }

    public static boolean isValidSoil(String treeId, Block soil) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? SoilType.GENERIC.matches(soil) : tree.isValidSoil(soil);
    }

    private static WorldGenerator configureSoil(WorldGenerator generator, TreeType tree) {
        if (generator instanceof LargeTreeWorldGenBase treeGenerator) {
            treeGenerator.setSoils(tree.soils);
        }
        return generator;
    }

    public static final class TreeType {

        private final Function<Boolean, WorldGenerator> defaultFactory;
        private final Map<SaplingLayout, Function<Boolean, WorldGenerator>> layoutFactories = new EnumMap<>(
            SaplingLayout.class);
        private EnumSet<SoilType> soils = EnumSet.of(SoilType.GENERIC);

        private TreeType(Function<Boolean, WorldGenerator> defaultFactory) {
            this.defaultFactory = defaultFactory;
        }

        /** Replaces the allowed soil categories for every layout of this tree. */
        public TreeType withSoils(SoilType... soilTypes) {
            EnumSet<SoilType> allowed = EnumSet.noneOf(SoilType.class);
            for (SoilType soilType : soilTypes) {
                allowed.add(Objects.requireNonNull(soilType, "soilType"));
            }
            soils = allowed;
            return this;
        }

        private boolean isValidSoil(Block soil) {
            for (SoilType soilType : soils) {
                if (soilType.matches(soil)) return true;
            }
            return false;
        }

        public TreeType withLayout(SaplingLayout layout, Function<Boolean, WorldGenerator> generatorFactory) {
            layoutFactories.put(
                Objects.requireNonNull(layout, "layout"),
                Objects.requireNonNull(generatorFactory, "generatorFactory"));
            return this;
        }
    }
}
