package net.junedev.viridium.worldgen.treegen;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import net.junedev.viridium.ViriPlants;
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
            (notifiesNeighbors) -> createDefaultGenerator(ViriPlants.AMERICAN_CHESTNUT.getBlocks(), notifiesNeighbors))
                .withLayout(
                    SaplingLayout.TWO_BY_TWO,
                    (notifiesNeighbors) -> new AmericanChestnutTreeGen(
                        notifiesNeighbors,
                        ViriPlants.AMERICAN_CHESTNUT.getBlocks(),
                        17,
                        4,
                        0.30D,
                        5,
                        2,
                        12,
                        3));
        register(
            "viridium:pedunculate_oak",
            (notifiesNeighbors) -> createDefaultGenerator(ViriPlants.PEDUNCULATE_OAK.getBlocks(), notifiesNeighbors))
                .withLayout(
                    SaplingLayout.TWO_BY_TWO,
                    (notifiesNeighbors) -> new MediumPedunculateOakTreeGen(
                        notifiesNeighbors,
                        ViriPlants.PEDUNCULATE_OAK.getBlocks(),
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
            ViriPlants.BALD_CYPRESS.getBlocks().log,
            ViriPlants.BALD_CYPRESS.getBlocks().leaves,
            10,
            5,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "black_oak",
            ViriPlants.BLACK_OAK.getBlocks().log,
            ViriPlants.BLACK_OAK.getBlocks().leaves,
            7,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "cacao",
            ViriPlants.CACAO.getBlocks().log,
            ViriPlants.CACAO.getBlocks().leaves,
            5,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "coast_redwood",
            ViriPlants.COAST_REDWOOD.getBlocks().log,
            ViriPlants.COAST_REDWOOD.getBlocks().leaves,
            20,
            10,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "giant_sequoia",
            ViriPlants.GIANT_SEQUOIA.getBlocks().log,
            ViriPlants.GIANT_SEQUOIA.getBlocks().leaves,
            24,
            10,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "kankan",
            ViriPlants.KANKAN.getBlocks().log,
            ViriPlants.KANKAN.getBlocks().leaves,
            7,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "mahogany",
            ViriPlants.MAHOGANY.getBlocks().log,
            ViriPlants.MAHOGANY.getBlocks().leaves,
            13,
            6,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "quaking_aspen",
            ViriPlants.QUAKING_ASPEN.getBlocks().log,
            ViriPlants.QUAKING_ASPEN.getBlocks().leaves,
            8,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "red_maple",
            ViriPlants.RED_MAPLE.getBlocks().log,
            ViriPlants.RED_MAPLE.getBlocks().leaves,
            8,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "red_spruce",
            ViriPlants.RED_SPRUCE.getBlocks().log,
            ViriPlants.RED_SPRUCE.getBlocks().leaves,
            10,
            6,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "scots_pine",
            ViriPlants.SCOTS_PINE.getBlocks().log,
            ViriPlants.SCOTS_PINE.getBlocks().leaves,
            9,
            5,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "sweetgum",
            ViriPlants.SWEETGUM.getBlocks().log,
            ViriPlants.SWEETGUM.getBlocks().leaves,
            9,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "weeping_willow",
            ViriPlants.WEEPING_WILLOW.getBlocks().log,
            ViriPlants.WEEPING_WILLOW.getBlocks().leaves,
            7,
            4,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "black_spruce",
            ViriPlants.BLACK_SPRUCE.getBlocks().log,
            ViriPlants.BLACK_SPRUCE.getBlocks().leaves,
            5,
            3,
            SimpleSpeciesTreeGen.Shape.CONIFER);
        registerSimple(
            "flowering_dogwood",
            ViriPlants.FLOWERING_DOGWOOD.getBlocks().log,
            ViriPlants.FLOWERING_DOGWOOD.getBlocks().leaves,
            4,
            2,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "giant_bamboo",
            ViriPlants.GIANT_BAMBOO.getBlocks().log,
            ViriPlants.GIANT_BAMBOO.getBlocks().leaves,
            10,
            7,
            SimpleSpeciesTreeGen.Shape.BAMBOO);
        registerSimple(
            "jacaranda",
            ViriPlants.JACARANDA.getBlocks().log,
            ViriPlants.JACARANDA.getBlocks().leaves,
            5,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "mimosa",
            ViriPlants.MIMOSA.getBlocks().log,
            ViriPlants.MIMOSA.getBlocks().leaves,
            4,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "mulga",
            ViriPlants.MULGA.getBlocks().log,
            ViriPlants.MULGA.getBlocks().leaves,
            3,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF).withSoils(SoilType.GENERIC, SoilType.SAND);
        registerSimple(
            "red_cinchona",
            ViriPlants.RED_CINCHONA.getBlocks().log,
            ViriPlants.RED_CINCHONA.getBlocks().leaves,
            4,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);
        registerSimple(
            "wisteria",
            ViriPlants.WISTERIA.getBlocks().log,
            ViriPlants.WISTERIA.getBlocks().leaves,
            4,
            3,
            SimpleSpeciesTreeGen.Shape.BROADLEAF);

        register(
            "viridium:coconut_palm",
            (notifiesNeighbors) -> new CoconutPalmTreeGen(notifiesNeighbors, ViriPlants.COCONUT_PALM.getBlocks()))
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
