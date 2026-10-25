package net.junedev.viridium;

import net.junedev.viridium.blocks.BushBlock;
import net.junedev.viridium.blocks.FernBlock;
import net.junedev.viridium.blocks.GroundPlaneBlock;
import net.junedev.viridium.blocks.TallPlantBlock;
import net.junedev.viridium.blocks.sets.SmallTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.AmericanChestnutBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.BurntLeavesTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.PalmTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.WhitePoplarBlockSet;
import net.junedev.viridium.blocks.vanilike.BaseFullLog;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BasePlanks;
import net.junedev.viridium.blocks.vanilike.WillowVineBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ViriBlocks {

    public void preInit() {

        ainselu = registerBush("ainselu");
        bears_breeches = registerBush("bears_breeches");
        blackberry = registerBush("blackberry");
        creosote_bush = registerBush("creosote_bush");
        croton = registerBush("croton");
        elderberry = registerBush("elderberry");
        false_indigo = registerBush("false_indigo");
        golden_wattle = registerBush("golden_wattle");
        honeysuckle = registerBush("honeysuckle");
        japanese_andromeda = registerBush("japanese_andromeda");
        koki_o_ono = registerBush("koki_o_ono");
        kudzu = registerBush("kudzu");
        kutjera = registerBush("kutjera");
        ma_o_hau_hele = registerBush("ma_o_hau_hele");
        oldman_saltbush = registerBush("oldman_saltbush");
        poinsettia = registerBush("poinsettia");
        quandong = registerBush("quandong");
        raspberry = registerBush("raspberry");
        rhododendron = registerBush("rhododendron");
        rose_shrub = registerBush("rose_shrub");
        sandhill_wattle = registerBush("sandhill_wattle");
        satsuki_azalea = registerBush("satsuki_azalea");
        silverleaf_hydrangea = registerBush("silverleaf_hydrangea");
        staghorn_sumac = registerBush("staghorn_sumac");
        wedding_bush = registerBush("wedding_bush");

        black_spruce = new SmallTreeBlockSet("black_spruce");
        blackthorn = new SmallTreeBlockSet("blackthorn");
        blue_mahoe = new SmallTreeBlockSet("blue_mahoe");
        box_elder = new SmallTreeBlockSet("box_elder");
        callery_pear = new SmallTreeBlockSet("callery_pear");
        citron = new SmallTreeBlockSet("citron");
        crape_myrtle = new SmallTreeBlockSet("crape_myrtle");
        desert_bloodwood = new SmallTreeBlockSet("desert_bloodwood");
        flowering_dogwood = new SmallTreeBlockSet("flowering_dogwood");
        giant_bamboo = new SmallTreeBlockSet("giant_bamboo", 3, 4, false);
        holly = new SmallTreeBlockSet("holly");
        jacaranda = new SmallTreeBlockSet("jacaranda");
        japanese_maple = new SmallTreeBlockSet("japanese_maple");
        joshua_tree = new SmallTreeBlockSet("joshua_tree");
        koki_o_kea = new SmallTreeBlockSet("koki_o_kea");
        kumquat = new SmallTreeBlockSet("kumquat");
        lemon_myrtle = new SmallTreeBlockSet("lemon_myrtle");
        lilac = new SmallTreeBlockSet("lilac");
        mimosa = new SmallTreeBlockSet("mimosa");
        mulga = new SmallTreeBlockSet("mulga");
        olive = new SmallTreeBlockSet("olive");
        pacific_yew = new SmallTreeBlockSet("pacific_yew");
        paperbark_maple = new SmallTreeBlockSet("paperbark_maple");
        pawpaw = new SmallTreeBlockSet("pawpaw");
        pear = new SmallTreeBlockSet("pear");
        red_cinchona = new SmallTreeBlockSet("red_cinchona");
        redbud = new SmallTreeBlockSet("redbud");
        wild_apple = new SmallTreeBlockSet("wild_apple");
        witch_hazel = new SmallTreeBlockSet("witch_hazel");
        wisteria = new SmallTreeBlockSet("wisteria");

        agave_americana_leaves = registerTallPlant("agave_americana_leaves", 2, 30, 1);
        welwitschia_leaf = registerTallPlant("welwitschia_leaf", 3, 19, 22);
        corpse_flower_inflorescence = registerTallPlant("corpse_flower_inflorescence", 2, 24, 4);
        corpse_flower_leaf = registerTallPlant("corpse_flower_leaf", 2, 21, 6);
        hemp_leaf = registerTallPlant("hemp_leaf", 2, 21, 5);
        monstera_leaf_large = registerTallPlant("monstera_leaf_large", 2, 13, 9);

        // Bushes

        // Standalone Grasses
        autumn_fern_frond = registerFern("autumn_fern_frond", 8);
        bracken_frond = registerFern("bracken_frond", 16, 40);
        christmas_fern = registerFern("christmas_fern", 16, 40);
        cinnamon_fern_frond = registerFern("cinnamon_fern_frond", 16, 30);
        deer_fern_frond = registerFern("deer_fern_frond", 8);
        harts_tongue_fern_frond = registerFern("harts_tongue_fern_frond", 8);
        horsetail = registerFern("horsetail", 16, 40);
        mt_kaala_cyanea_frond = registerFern("mt_kaala_cyanea_frond", 16, 40);
        oem_frond = registerFern("oem_frond", 32, -20, 0.2D);
        silver_fern_frond = registerFern("silver_fern_frond", 16, 40, 0.2D);
        silver_fern_koru = registerFern("silver_fern_koru", 8, 45, 0.1D);
        sword_fern = registerFern("sword_fern", 8);

        // Standalone Flora

        // Mushrooms

        // Grass Decorators
        autumn_leaf_pile = registerGroundPlane("autumn_leaf_pile");
        clover_patch = registerGroundPlane("clover_patch_top");
        dead_leaf_pile = registerGroundPlane("dead_leaf_pile");
        leaf_pile = registerGroundPlane("leaf_pile");
        pinestraw = registerGroundPlane("pinestraw");
        snorkelwort = registerGroundPlane("snorkelwort");

        // Vines

        // Full Trees
        amaranth = new LargeTreeBlockSet("amaranth");
        american_chestnut = new AmericanChestnutBlockSet("american_chestnut");
        american_sycamore = new LargeTreeBlockSet("american_sycamore");
        ash = new LargeTreeBlockSet("ash");
        bald_cypress = new LargeTreeBlockSet("bald_cypress");
        baobab = new LargeTreeBlockSet("baobab");
        beech = new LargeTreeBlockSet("beech");
        black_oak = new LargeTreeBlockSet("black_oak");
        black_pine = new LargeTreeBlockSet("black_pine");
        cacao = new LargeTreeBlockSet("cacao");
        candlenut = new LargeTreeBlockSet("candlenut");
        coast_redwood = new BurntLeavesTreeBlockSet("coast_redwood");
        coconut_palm = new LargeTreeBlockSet("coconut_palm");
        coolibah = new LargeTreeBlockSet("coolibah");
        dahurian_larch = new LargeTreeBlockSet("dahurian_larch");
        dragons_blood = new LargeTreeBlockSet("dragons_blood");
        driftwood = new LargeTreeBlockSet("driftwood");
        ebony = new LargeTreeBlockSet("ebony");
        giant_sequoia = new BurntLeavesTreeBlockSet("giant_sequoia");
        grand_fir = new LargeTreeBlockSet("grand_fir");
        hawthorn = new LargeTreeBlockSet("hawthorn");
        kankan = new LargeTreeBlockSet("kankan");
        koa = new LargeTreeBlockSet("koa");
        mahogany = new LargeTreeBlockSet("mahogany");
        mediterranean_cypress = new LargeTreeBlockSet("mediterranean_cypress");
        nazareno = new LargeTreeBlockSet("nazareno");
        paper_birch = new LargeTreeBlockSet("paper_birch");
        pedunculate_oak = new LargeTreeBlockSet("pedunculate_oak");
        quaking_aspen = new LargeTreeBlockSet("quaking_aspen");
        rainbow_eucalyptus = new LargeTreeBlockSet("rainbow_eucalyptus");
        red_alder = new LargeTreeBlockSet("red_alder");
        red_gum = new LargeTreeBlockSet("red_gum");
        red_maple = new LargeTreeBlockSet("red_maple");
        red_spruce = new LargeTreeBlockSet("red_spruce");
        rowan = new LargeTreeBlockSet("rowan");
        royal_palm = new PalmTreeBlockSet("royal_palm");
        rubber = new LargeTreeBlockSet("rubber");
        rubberLogExpended = registerLargeLog("rubber_log_expended");
        rubberLogResinous = registerLargeLog("rubber_log_resinous");
        scots_pine = new LargeTreeBlockSet("scots_pine");
        siberian_larch = new LargeTreeBlockSet("siberian_larch");
        sierra_juniper = new LargeTreeBlockSet("sierra_juniper");
        southern_magnolia = new LargeTreeBlockSet("southern_magnolia");
        sweet_cherry = new LargeTreeBlockSet("sweet_cherry");
        sweetgum = new LargeTreeBlockSet("sweetgum");
        tamarack = new LargeTreeBlockSet("tamarack");
        turkish_pine = new LargeTreeBlockSet("turkish_pine");
        umbrella_tree = new LargeTreeBlockSet("umbrella_tree");
        weeping_willow = new LargeTreeBlockSet("weeping_willow");
        Block weepingWillowVines = new WillowVineBlock().setBlockName("weeping_willow_vines");
        GameRegistry.registerBlock(weepingWillowVines, "weeping_willow_vines");
        white_poplar = new WhitePoplarBlockSet("white_poplar");
        yew = new LargeTreeBlockSet("yew");
    }

    // Bushes
    private Block registerBush(String name) {
        Block bush = new BushBlock().setBlockName(name)
            .setBlockTextureName("bushes/" + name);
        GameRegistry.registerBlock(bush, name);
        return bush;
    }

    // Full Trees
    private Block registerLargeLog(String name) {
        Block log = new BaseFullLog().setBlockName(name)
            .setBlockTextureName("large_trees/" + name);
        GameRegistry.registerBlock(log, name);
        return log;
    }

    private Block registerLeaves(String name) {
        Block leaves = new BaseLeaves().setBlockName(name)
            .setBlockTextureName("large_trees/" + name);
        GameRegistry.registerBlock(leaves, name);
        return leaves;
    }

    private Block registerPlanks(String name) {
        Block planks = new BasePlanks().setBlockName(name)
            .setBlockTextureName("large_trees/" + name);
        GameRegistry.registerBlock(planks, name);
        return planks;
    }

    // Standalone Grasses
    private Block registerTallPlant(String name, int size, int pixelWidth) {
        Block tallPlant = new TallPlantBlock(size, pixelWidth).setBlockName(name);
        GameRegistry.registerBlock(tallPlant, name);
        return tallPlant;
    }

    private Block registerTallPlant(String name, int size, int pixelWidth, int textureX) {
        Block tallPlant = new TallPlantBlock(size, pixelWidth, textureX).setBlockName(name)
            .setBlockTextureName("complex_plants/" + name);
        GameRegistry.registerBlock(tallPlant, name);
        return tallPlant;
    }

    private Block registerTallPlant(String name, int size) {
        return registerTallPlant(name, size, 16);
    }

    private Block registerFern(String name, int halfSize) {
        return registerFern(name, halfSize, 45, 0.0);
    }

    private Block registerFern(String name, int halfSize, double openingAngle) {
        return registerFern(name, halfSize, openingAngle, 0.0);
    }

    private Block registerFern(String name, int halfSize, double openingAngle, double minimumRadius) {
        Block fern = new FernBlock(halfSize, openingAngle, minimumRadius).setBlockName(name)
            .setBlockTextureName("ferns/" + name);
        GameRegistry.registerBlock(fern, name);
        return fern;
    }

    // Standalone Flora

    // Mushrooms

    // Grass Decorators
    private Block registerGroundPlane(String name) {
        Block groundPlane = new GroundPlaneBlock().setBlockName(name)
            .setBlockTextureName("ground/" + name);
        GameRegistry.registerBlock(groundPlane, name);
        return groundPlane;
    }

    // Vines

    // Bushes
    public static Block ainselu;
    public static Block bears_breeches;
    public static Block blackberry;
    public static Block creosote_bush;
    public static Block croton;
    public static Block elderberry;
    public static Block false_indigo;
    public static Block golden_wattle;
    public static Block honeysuckle;
    public static Block japanese_andromeda;
    public static Block koki_o_ono;
    public static Block kudzu;
    public static Block kutjera;
    public static Block ma_o_hau_hele;
    public static Block oldman_saltbush;
    public static Block poinsettia;
    public static Block quandong;
    public static Block raspberry;
    public static Block rhododendron;
    public static Block rose_shrub;
    public static Block sandhill_wattle;
    public static Block satsuki_azalea;
    public static Block silverleaf_hydrangea;
    public static Block staghorn_sumac;
    public static Block wedding_bush;

    public static SmallTreeBlockSet black_spruce;
    public static SmallTreeBlockSet blackthorn;
    public static SmallTreeBlockSet blue_mahoe;
    public static SmallTreeBlockSet box_elder;
    public static SmallTreeBlockSet callery_pear;
    public static SmallTreeBlockSet citron;
    public static SmallTreeBlockSet crape_myrtle;
    public static SmallTreeBlockSet desert_bloodwood;
    public static SmallTreeBlockSet flowering_dogwood;
    public static SmallTreeBlockSet giant_bamboo;
    public static SmallTreeBlockSet holly;
    public static SmallTreeBlockSet jacaranda;
    public static SmallTreeBlockSet japanese_maple;
    public static SmallTreeBlockSet joshua_tree;
    public static SmallTreeBlockSet koki_o_kea;
    public static SmallTreeBlockSet kumquat;
    public static SmallTreeBlockSet lemon_myrtle;
    public static SmallTreeBlockSet lilac;
    public static SmallTreeBlockSet mimosa;
    public static SmallTreeBlockSet mulga;
    public static SmallTreeBlockSet olive;
    public static SmallTreeBlockSet pacific_yew;
    public static SmallTreeBlockSet paperbark_maple;
    public static SmallTreeBlockSet pawpaw;
    public static SmallTreeBlockSet pear;
    public static SmallTreeBlockSet red_cinchona;
    public static SmallTreeBlockSet redbud;
    public static SmallTreeBlockSet wild_apple;
    public static SmallTreeBlockSet witch_hazel;
    public static SmallTreeBlockSet wisteria;

    public static Block autumn_fern_frond;
    public static Block bracken_frond;
    public static Block christmas_fern;
    public static Block cinnamon_fern_frond;
    public static Block deer_fern_frond;
    public static Block harts_tongue_fern_frond;
    public static Block horsetail;
    public static Block mt_kaala_cyanea_frond;
    public static Block oem_frond;
    public static Block silver_fern_frond;
    public static Block silver_fern_koru;
    public static Block sword_fern;

    public static Block welwitschia_leaf;
    public static Block corpse_flower_leaf;
    public static Block corpse_flower_inflorescence;
    public static Block hemp_leaf;
    public static Block monstera_leaf_large;
    public static Block agave_americana_leaves;

    // Grass Decorators
    public static Block autumn_leaf_pile;
    public static Block clover_patch;
    public static Block dead_leaf_pile;
    public static Block leaf_pile;
    public static Block pinestraw;
    public static Block snorkelwort;

    // Large Trees
    public static LargeTreeBlockSet amaranth;
    public static AmericanChestnutBlockSet american_chestnut;
    public static LargeTreeBlockSet american_sycamore;
    public static LargeTreeBlockSet ash;
    public static LargeTreeBlockSet bald_cypress;
    public static LargeTreeBlockSet baobab;
    public static LargeTreeBlockSet beech;
    public static LargeTreeBlockSet black_oak;
    public static LargeTreeBlockSet black_pine;
    public static LargeTreeBlockSet cacao;
    public static LargeTreeBlockSet candlenut;
    public static BurntLeavesTreeBlockSet coast_redwood;
    public static LargeTreeBlockSet coconut_palm;
    public static LargeTreeBlockSet coolibah;
    public static LargeTreeBlockSet dahurian_larch;
    public static LargeTreeBlockSet dragons_blood;
    public static LargeTreeBlockSet driftwood;
    public static LargeTreeBlockSet ebony;
    public static BurntLeavesTreeBlockSet giant_sequoia;
    public static LargeTreeBlockSet grand_fir;
    public static LargeTreeBlockSet hawthorn;
    public static LargeTreeBlockSet kankan;
    public static LargeTreeBlockSet koa;
    public static LargeTreeBlockSet mahogany;
    public static LargeTreeBlockSet mediterranean_cypress;
    public static LargeTreeBlockSet nazareno;
    public static LargeTreeBlockSet paper_birch;
    public static LargeTreeBlockSet pedunculate_oak;
    public static LargeTreeBlockSet quaking_aspen;
    public static LargeTreeBlockSet rainbow_eucalyptus;
    public static LargeTreeBlockSet red_alder;
    public static LargeTreeBlockSet red_gum;
    public static LargeTreeBlockSet red_maple;
    public static LargeTreeBlockSet red_spruce;
    public static LargeTreeBlockSet rowan;
    public static PalmTreeBlockSet royal_palm;
    public static LargeTreeBlockSet rubber;
    public static Block rubberLogExpended;
    public static Block rubberLogResinous;
    public static LargeTreeBlockSet scots_pine;
    public static LargeTreeBlockSet siberian_larch;
    public static LargeTreeBlockSet sierra_juniper;
    public static LargeTreeBlockSet southern_magnolia;
    public static LargeTreeBlockSet sweet_cherry;
    public static LargeTreeBlockSet sweetgum;
    public static LargeTreeBlockSet tamarack;
    public static LargeTreeBlockSet turkish_pine;
    public static LargeTreeBlockSet umbrella_tree;
    public static LargeTreeBlockSet weeping_willow;
    public static WhitePoplarBlockSet white_poplar;
    public static LargeTreeBlockSet yew;

}
