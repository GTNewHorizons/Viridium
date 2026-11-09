package net.junedev.viridium.worldgen.treegen;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import net.junedev.viridium.ViriBlocks;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.worldgen.SoilType;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;

public final class TreeRegistry {

    private static final Map<String, TreeType> TREES = new HashMap<>();

    public static void preInit() {
        register("minecraft:oak", WorldGenTrees::new).withVariant("tall", WorldGenBigTree::new);
        register("minecraft:birch", notify -> new WorldGenForest(notify, false))
            .withVariant("tall", notify -> new WorldGenForest(notify, true));
        register("minecraft:acacia", WorldGenSavannaTree::new);
        register("minecraft:dark_oak", WorldGenCanopyTree::new);
        register("minecraft:spruce", WorldGenTaiga2::new)
            .withVariant("tall", notify -> new WorldGenMegaPineTree(notify, true));
        register("minecraft:jungle", notify -> new WorldGenTrees(notify, 4, 3, 3, true))
            .withVariant("tall", notify -> new WorldGenMegaJungle(notify, 10, 20, 3, 3));
        register("minecraft:oak_shrub", notify -> new WorldGenShrub(0, 0));
        register("minecraft:birch_shrub", notify -> new WorldGenShrub(2, 2));

        register(
            "viridium:american_chestnut",
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
            "viridium:pedunculate_oak",
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
        // Existing species blocks with shared, interim tree silhouettes.
        registerSimple(
            "bald_cypress",
            ViriBlocks.bald_cypress.log,
            ViriBlocks.bald_cypress.leaves,
            10,
            5,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "black_oak",
            ViriBlocks.black_oak.log,
            ViriBlocks.black_oak.leaves,
            7,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "cacao",
            ViriBlocks.cacao.log,
            ViriBlocks.cacao.leaves,
            5,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "coast_redwood",
            ViriBlocks.coast_redwood.log,
            ViriBlocks.coast_redwood.leaves,
            20,
            10,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "giant_sequoia",
            ViriBlocks.giant_sequoia.log,
            ViriBlocks.giant_sequoia.leaves,
            24,
            10,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "kankan",
            ViriBlocks.kankan.log,
            ViriBlocks.kankan.leaves,
            7,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "mahogany",
            ViriBlocks.mahogany.log,
            ViriBlocks.mahogany.leaves,
            13,
            6,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "quaking_aspen",
            ViriBlocks.quaking_aspen.log,
            ViriBlocks.quaking_aspen.leaves,
            8,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "red_maple",
            ViriBlocks.red_maple.log,
            ViriBlocks.red_maple.leaves,
            8,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "red_spruce",
            ViriBlocks.red_spruce.log,
            ViriBlocks.red_spruce.leaves,
            10,
            6,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "scots_pine",
            ViriBlocks.scots_pine.log,
            ViriBlocks.scots_pine.leaves,
            9,
            5,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "sweetgum",
            ViriBlocks.sweetgum.log,
            ViriBlocks.sweetgum.leaves,
            9,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "weeping_willow",
            ViriBlocks.weeping_willow.log,
            ViriBlocks.weeping_willow.leaves,
            7,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "black_spruce",
            ViriBlocks.black_spruce.log,
            ViriBlocks.black_spruce.leaves,
            5,
            3,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "flowering_dogwood",
            ViriBlocks.flowering_dogwood.log,
            ViriBlocks.flowering_dogwood.leaves,
            4,
            2,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "giant_bamboo",
            ViriBlocks.giant_bamboo.log,
            ViriBlocks.giant_bamboo.leaves,
            10,
            7,
            SimpleSpeciesTreeGen.Shape.BAMBOO);
        registerSimple(
            "jacaranda",
            ViriBlocks.jacaranda.log,
            ViriBlocks.jacaranda.leaves,
            5,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "mimosa",
            ViriBlocks.mimosa.log,
            ViriBlocks.mimosa.leaves,
            4,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "mulga",
            ViriBlocks.mulga.log,
            ViriBlocks.mulga.leaves,
            3,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF).withSoils(SoilType.GENERIC, SoilType.SAND);
        registerSimple(
            "red_cinchona",
            ViriBlocks.red_cinchona.log,
            ViriBlocks.red_cinchona.leaves,
            4,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "wisteria",
            ViriBlocks.wisteria.log,
            ViriBlocks.wisteria.leaves,
            4,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);

        register(
            "viridium:coconut_palm",
            (notifiesNeighbors) -> new CoconutPalmTreeGen(notifiesNeighbors, ViriBlocks.coconut_palm))
                .withSoils(SoilType.GENERIC, SoilType.SAND);
    }

    private static TreeType registerSimple(String name, Block log, Block leaves, int height, int variation,
        SimpleSpeciesTreeGen.Shape shape) {
        return register(
            "viridium:" + name,
            notify -> new SimpleSpeciesTreeGen(notify, log, leaves, height, variation, shape));
    }

    private TreeRegistry() {}

    public static TreeType register(String treeId, Function<Boolean, WorldGenAbstractTree> defaultFactory) {
        Objects.requireNonNull(treeId, "treeId");
        TreeType tree = new TreeType(Objects.requireNonNull(defaultFactory, "defaultFactory"));
        TREES.put(treeId, tree);
        return tree;
    }

    // Create a new generator for each tree to avoid reusing state from previous generations
    public static WorldGenAbstractTree createGenerator(String treeId, WoodBlockSet blockSet,
        boolean notifiesNeighbors) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? createDefaultGenerator(blockSet, notifiesNeighbors)
            : configureSoil(tree.defaultFactory.apply(notifiesNeighbors), tree);
    }

    public static WorldGenAbstractTree createGenerator(String treeId, WoodBlockSet blockSet, SaplingLayout layout,
        boolean notifiesNeighbors) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? createDefaultGenerator(blockSet, notifiesNeighbors)
            : configureSoil(
                tree.layoutFactories.getOrDefault(layout, tree.defaultFactory)
                    .apply(notifiesNeighbors),
                tree);
    }

    /** Strict lookup for biome JSONs; saplings retain their block-set fallback. */
    public static WorldGenAbstractTree createGenerator(String treeId, String variant, boolean notifiesNeighbors) {
        TreeType tree = TREES.get(treeId);
        if (!contains(treeId, variant)) {
            throw new IllegalArgumentException("Unknown tree or variant: " + treeId + " / " + variant);
        }
        return configureSoil(
            tree.variantFactories.get(variant)
                .apply(notifiesNeighbors),
            tree);
    }

    public static boolean contains(String treeId, String variant) {
        TreeType tree = TREES.get(treeId);
        return tree != null && tree.variantFactories.containsKey(variant);
    }

    public static boolean hasLayout(String treeId, SaplingLayout layout) {
        TreeType tree = TREES.get(treeId);
        return tree != null && tree.layoutFactories.containsKey(layout);
    }

    private static WorldGenAbstractTree createDefaultGenerator(WoodBlockSet blockSet, boolean notifiesNeighbors) {
        return new LargeDeciduousTreeGen(notifiesNeighbors, blockSet, "", 8, 4, 0.30D, 2, 4, 8, 6);
    }

    public static boolean isValidSoil(String treeId, Block soil) {
        TreeType tree = TREES.get(treeId);
        return tree == null ? SoilType.GENERIC.matches(soil) : tree.isValidSoil(soil);
    }

    private static WorldGenAbstractTree configureSoil(WorldGenAbstractTree generator, TreeType tree) {
        if (generator instanceof LargeTreeWorldGenBase treeGenerator) {
            treeGenerator.setSoils(tree.soils);
        }
        return generator;
    }

    public static final class TreeType {

        private final Function<Boolean, WorldGenAbstractTree> defaultFactory;
        private final Map<SaplingLayout, Function<Boolean, WorldGenAbstractTree>> layoutFactories = new EnumMap<>(
            SaplingLayout.class);
        private final Map<String, Function<Boolean, WorldGenAbstractTree>> variantFactories = new HashMap<>();
        private EnumSet<SoilType> soils = EnumSet.of(SoilType.GENERIC);

        private TreeType(Function<Boolean, WorldGenAbstractTree> defaultFactory) {
            this.defaultFactory = defaultFactory;
            variantFactories.put("default", defaultFactory);
            variantFactories.put("single", defaultFactory);
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

        public TreeType withVariant(String variant, Function<Boolean, WorldGenAbstractTree> generatorFactory) {
            variantFactories.put(
                Objects.requireNonNull(variant, "variant"),
                Objects.requireNonNull(generatorFactory, "generatorFactory"));
            return this;
        }

        public TreeType withLayout(SaplingLayout layout, Function<Boolean, WorldGenAbstractTree> generatorFactory) {
            layoutFactories.put(
                Objects.requireNonNull(layout, "layout"),
                Objects.requireNonNull(generatorFactory, "generatorFactory"));
            withVariant(
                layout.name()
                    .toLowerCase(Locale.ROOT),
                generatorFactory);
            return this;
        }
    }
}
