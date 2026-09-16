package net.junedev.viridium;

import net.junedev.viridium.blocks.BaseFullLog;
import net.junedev.viridium.blocks.BaseLeaves;
import net.junedev.viridium.blocks.BasePlanks;
import net.junedev.viridium.blocks.BaseSaplingBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ViriBlocks {

    public void preInit() {
        // Bushes

        // Standalone Grasses

        // Standalone Flora

        // Mushrooms

        // Grass Decorators

        // Vines

        // Full Trees
        amaranthLog = registerLargeLog("amaranth_log");
        amaranthLogStripped = registerLargeLog("amaranth_stripped");
        amaranthLeaves = registerLeaves("amaranth_leaves");
        amaranthPlanks = registerPlanks("amaranth_planks");
        amaranthSapling = registerLargeSapling("amaranth_sapling");

        americanSycamoreLog = registerLargeLog("american_sycamore_log");
        americanSycamoreLogStripped = registerLargeLog("american_sycamore_stripped");
        americanSycamoreLeaves = registerLeaves("american_sycamore_leaves");
        americanSycamorePlanks = registerPlanks("american_sycamore_planks");
        americanSycamoreSapling = registerLargeSapling("american_sycamore_sapling");

        ashLog = registerLargeLog("ash_log");
        ashLogStripped = registerLargeLog("ash_stripped");
        ashLeaves = registerLeaves("ash_leaves");
        ashPlanks = registerPlanks("ash_planks");
        ashSapling = registerLargeSapling("ash_sapling");

        baldCypressLog = registerLargeLog("bald_cypress_log");
        baldCypressLogStripped = registerLargeLog("bald_cypress_stripped");
        baldCypressLeaves = registerLeaves("bald_cypress_leaves");
        baldCypressPlanks = registerPlanks("bald_cypress_planks");
        baldCypressSapling = registerLargeSapling("bald_cypress_sapling");

        baobabLog = registerLargeLog("baobab_log");
        baobabLogStripped = registerLargeLog("baobab_stripped");
        baobabLeaves = registerLeaves("baobab_leaves");
        baobabPlanks = registerPlanks("baobab_planks");
        baobabSapling = registerLargeSapling("baobab_sapling");

        beechLog = registerLargeLog("beech_log");
        beechLogStripped = registerLargeLog("beech_stripped");
        beechLeaves = registerLeaves("beech_leaves");
        beechPlanks = registerPlanks("beech_planks");
        beechSapling = registerLargeSapling("beech_sapling");

        blackOakLog = registerLargeLog("black_oak_log");
        blackOakLogStripped = registerLargeLog("black_oak_stripped");
        blackOakLeaves = registerLeaves("black_oak_leaves");
        blackOakPlanks = registerPlanks("black_oak_planks");
        blackOakSapling = registerLargeSapling("black_oak_sapling");

        blackPineLog = registerLargeLog("black_pine_log");
        blackPineLogStripped = registerLargeLog("black_pine_stripped");
        blackPineLeaves = registerLeaves("black_pine_leaves");
        blackPinePlanks = registerPlanks("black_pine_planks");
        blackPineSapling = registerLargeSapling("black_pine_sapling");

        cacaoLog = registerLargeLog("cacao_log");
        cacaoLogStripped = registerLargeLog("cacao_stripped");
        cacaoLeaves = registerLeaves("cacao_leaves");
        cacaoPlanks = registerPlanks("cacao_planks");
        cacaoSapling = registerLargeSapling("cacao_sapling");

        candlenutLog = registerLargeLog("candlenut_log");
        candlenutLogStripped = registerLargeLog("candlenut_stripped");
        candlenutLeaves = registerLeaves("candlenut_leaves");
        candlenutPlanks = registerPlanks("candlenut_planks");
        candlenutSapling = registerLargeSapling("candlenut_sapling");

        coastRedwoodLog = registerLargeLog("coast_redwood_log");
        coastRedwoodLogStripped = registerLargeLog("coast_redwood_stripped");
        coastRedwoodLeaves = registerLeaves("coast_redwood_leaves");
        coastRedwoodLeavesBurnt = registerLeaves("coast_redwood_leaves_burnt");
        coastRedwoodPlanks = registerPlanks("coast_redwood_planks");
        coastRedwoodSapling = registerLargeSapling("coast_redwood_sapling");

        coconutPalmLog = registerLargeLog("coconut_palm_log");
        coconutPalmLogStripped = registerLargeLog("coconut_palm_stripped");
        coconutPalmLeaves = registerLeaves("coconut_palm_leaves");
        coconutPalmPlanks = registerPlanks("coconut_palm_planks");
        coconutPalmSapling = registerLargeSapling("coconut_palm_sapling");

        coolibahLog = registerLargeLog("coolibah_log");
        coolibahLogStripped = registerLargeLog("coolibah_stripped");
        coolibahLeaves = registerLeaves("coolibah_leaves");
        coolibahPlanks = registerPlanks("coolibah_planks");
        coolibahSapling = registerLargeSapling("coolibah_sapling");

        dahurianLarchLog = registerLargeLog("dahurian_larch_log");
        dahurianLarchLogStripped = registerLargeLog("dahurian_larch_stripped");
        dahurianLarchLeaves = registerLeaves("dahurian_larch_leaves");
        dahurianLarchPlanks = registerPlanks("dahurian_larch_planks");
        dahurianLarchSapling = registerLargeSapling("dahurian_larch_sapling");

        dragonsBloodLog = registerLargeLog("dragons_blood_log");
        dragonsBloodLogStripped = registerLargeLog("dragons_blood_stripped");
        dragonsBloodLeaves = registerLeaves("dragons_blood_leaves");
        dragonsBloodPlanks = registerPlanks("dragons_blood_planks");
        dragonsBloodSapling = registerLargeSapling("dragons_blood_sapling");

        driftwoodLog = registerLargeLog("driftwood_log");
        driftwoodPlanks = registerPlanks("driftwood_planks");

        ebonyLog = registerLargeLog("ebony_log");
        ebonyLogStripped = registerLargeLog("ebony_stripped");
        ebonyLeaves = registerLeaves("ebony_leaves");
        ebonyPlanks = registerPlanks("ebony_planks");
        ebonySapling = registerLargeSapling("ebony_sapling");

        giantSequoiaLog = registerLargeLog("giant_sequoia_log");
        giantSequoiaLogStripped = registerLargeLog("giant_sequoia_stripped");
        giantSequoiaLeaves = registerLeaves("giant_sequoia_leaves");
        giantSequoiaLeavesBurnt = registerLeaves("giant_sequoia_leaves_burnt");
        giantSequoiaPlanks = registerPlanks("giant_sequoia_planks");
        giantSequoiaSapling = registerLargeSapling("giant_sequoia_sapling");

        grandFirLog = registerLargeLog("grand_fir_log");
        grandFirLogStripped = registerLargeLog("grand_fir_stripped");
        grandFirLeaves = registerLeaves("grand_fir_leaves");
        grandFirPlanks = registerPlanks("grand_fir_planks");
        grandFirSapling = registerLargeSapling("grand_fir_sapling");

        hawthornLog = registerLargeLog("hawthorn_log");
        hawthornLogStripped = registerLargeLog("hawthorn_stripped");
        hawthornLeaves = registerLeaves("hawthorn_leaves");
        hawthornPlanks = registerPlanks("hawthorn_planks");
        hawthornSapling = registerLargeSapling("hawthorn_sapling");

        kankanLog = registerLargeLog("kankan_log");
        kankanLogStripped = registerLargeLog("kankan_stripped");
        kankanLeaves = registerLeaves("kankan_leaves");
        kankanPlanks = registerPlanks("kankan_planks");
        kankanSapling = registerLargeSapling("kankan_sapling");

        koaLog = registerLargeLog("koa_log");
        koaLogStripped = registerLargeLog("koa_stripped");
        koaLeaves = registerLeaves("koa_leaves");
        koaPlanks = registerPlanks("koa_planks");
        koaSapling = registerLargeSapling("koa_sapling");

        mahoganyLog = registerLargeLog("mahogany_log");
        mahoganyLogStripped = registerLargeLog("mahogany_stripped");
        mahoganyLeaves = registerLeaves("mahogany_leaves");
        mahoganyPlanks = registerPlanks("mahogany_planks");
        mahoganySapling = registerLargeSapling("mahogany_sapling");

        mediterraneanCypressLog = registerLargeLog("mediterranean_cypress_log");
        mediterraneanCypressLogStripped = registerLargeLog("mediterranean_cypress_stripped");
        mediterraneanCypressLeaves = registerLeaves("mediterranean_cypress_leaves");
        mediterraneanCypressPlanks = registerPlanks("mediterranean_cypress_planks");
        mediterraneanCypressSapling = registerLargeSapling("mediterranean_cypress_sapling");

        nazarenoLog = registerLargeLog("nazareno_log");
        nazarenoLogStripped = registerLargeLog("nazareno_stripped");
        nazarenoLeaves = registerLeaves("nazareno_leaves");
        nazarenoPlanks = registerPlanks("nazareno_planks");
        nazarenoSapling = registerLargeSapling("nazareno_sapling");

        paperBirchLog = registerLargeLog("paper_birch_log");
        paperBirchLogStripped = registerLargeLog("paper_birch_stripped");
        paperBirchLeaves = registerLeaves("paper_birch_leaves");
        paperBirchPlanks = registerPlanks("paper_birch_planks");
        paperBirchSapling = registerLargeSapling("paper_birch_sapling");

        pedunculateOakLog = registerLargeLog("pedunculate_oak_log");
        pedunculateOakLogStripped = registerLargeLog("pedunculate_oak_stripped");
        pedunculateOakLeaves = registerLeaves("pedunculate_oak_leaves");
        pedunculateOakPlanks = registerPlanks("pedunculate_oak_planks");
        pedunculateOakSapling = registerLargeSapling("pedunculate_oak_sapling");

        quakingAspenLog = registerLargeLog("quaking_aspen_log");
        quakingAspenLogStripped = registerLargeLog("quaking_aspen_stripped");
        quakingAspenLeaves = registerLeaves("quaking_aspen_leaves");
        quakingAspenPlanks = registerPlanks("quaking_aspen_planks");
        quakingAspenSapling = registerLargeSapling("quaking_aspen_sapling");

        rainbowEucalyptusLog = registerLargeLog("rainbow_eucalyptus_log");
        rainbowEucalyptusLogStripped = registerLargeLog("rainbow_eucalyptus_stripped");
        rainbowEucalyptusLeaves = registerLeaves("rainbow_eucalyptus_leaves");
        rainbowEucalyptusPlanks = registerPlanks("rainbow_eucalyptus_planks");
        rainbowEucalyptusSapling = registerLargeSapling("rainbow_eucalyptus_sapling");

        redAlderLog = registerLargeLog("red_alder_log");
        redAlderLogStripped = registerLargeLog("red_alder_stripped");
        redAlderLeaves = registerLeaves("red_alder_leaves");
        redAlderPlanks = registerPlanks("red_alder_planks");
        redAlderSapling = registerLargeSapling("red_alder_sapling");

        redGumLog = registerLargeLog("red_gum_log");
        redGumLogStripped = registerLargeLog("red_gum_stripped");
        redGumLeaves = registerLeaves("red_gum_leaves");
        redGumPlanks = registerPlanks("red_gum_planks");
        redGumSapling = registerLargeSapling("red_gum_sapling");

        redMapleLog = registerLargeLog("red_maple_log");
        redMapleLogStripped = registerLargeLog("red_maple_stripped");
        redMapleLeaves = registerLeaves("red_maple_leaves");
        redMaplePlanks = registerPlanks("red_maple_planks");
        redMapleSapling = registerLargeSapling("red_maple_sapling");

        redSpruceLog = registerLargeLog("red_spruce_log");
        redSpruceLogStripped = registerLargeLog("red_spruce_stripped");
        redSpruceLeaves = registerLeaves("red_spruce_leaves");
        redSprucePlanks = registerPlanks("red_spruce_planks");
        redSpruceSapling = registerLargeSapling("red_spruce_sapling");

        rowanLog = registerLargeLog("rowan_log");
        rowanLogStripped = registerLargeLog("rowan_stripped");
        rowanLeaves = registerLeaves("rowan_leaves");
        rowanPlanks = registerPlanks("rowan_planks");
        rowanSapling = registerLargeSapling("rowan_sapling");

        royalPalmLog = registerLargeLog("royal_palm_log");
        royalPalmCrown = registerLargeLog("royal_palm_crown");
        royalPalmLeaves = registerLeaves("royal_palm_leaves");
        royalPalmPlanks = registerPlanks("royal_palm_planks");
        royalPalmSapling = registerLargeSapling("royal_palm_sapling");

        rubberLog = registerLargeLog("rubber_log");
        rubberLogExpended = registerLargeLog("rubber_log_expended");
        rubberLogResinous = registerLargeLog("rubber_log_resinous");
        rubberLogStripped = registerLargeLog("rubber_stripped");
        rubberLeaves = registerLeaves("rubber_leaves");
        rubberPlanks = registerPlanks("rubber_planks");
        rubberSapling = registerLargeSapling("rubber_sapling");

        scotsPineLog = registerLargeLog("scots_pine_log");
        scotsPineLogStripped = registerLargeLog("scots_pine_stripped");
        scotsPineLeaves = registerLeaves("scots_pine_leaves");
        scotsPinePlanks = registerPlanks("scots_pine_planks");
        scotsPineSapling = registerLargeSapling("scots_pine_sapling");

        siberianLarchLog = registerLargeLog("siberian_larch_log");
        siberianLarchLogStripped = registerLargeLog("siberian_larch_stripped");
        siberianLarchLeaves = registerLeaves("siberian_larch_leaves");
        siberianLarchPlanks = registerPlanks("siberian_larch_planks");
        siberianLarchSapling = registerLargeSapling("siberian_larch_sapling");

        sierraJuniperLog = registerLargeLog("sierra_juniper_log");
        sierraJuniperLogStripped = registerLargeLog("sierra_juniper_stripped");
        sierraJuniperLeaves = registerLeaves("sierra_juniper_leaves");
        sierraJuniperPlanks = registerPlanks("sierra_juniper_planks");
        sierraJuniperSapling = registerLargeSapling("sierra_juniper_sapling");

        southernMagnoliaLog = registerLargeLog("southern_magnolia_log");
        southernMagnoliaLogStripped = registerLargeLog("southern_magnolia_stripped");
        southernMagnoliaLeaves = registerLeaves("southern_magnolia_leaves");
        southernMagnoliaPlanks = registerPlanks("southern_magnolia_planks");
        southernMagnoliaSapling = registerLargeSapling("southern_magnolia_sapling");

        sweetCherryLog = registerLargeLog("sweet_cherry_log");
        sweetCherryLogStripped = registerLargeLog("sweet_cherry_stripped");
        sweetCherryLeaves = registerLeaves("sweet_cherry_leaves");
        sweetCherryPlanks = registerPlanks("sweet_cherry_planks");
        sweetCherrySapling = registerLargeSapling("sweet_cherry_sapling");

        sweetgumLog = registerLargeLog("sweetgum_log");
        sweetgumLogStripped = registerLargeLog("sweetgum_stripped");
        sweetgumLeaves = registerLeaves("sweetgum_leaves");
        sweetgumPlanks = registerPlanks("sweetgum_planks");
        sweetgumSapling = registerLargeSapling("sweetgum_sapling");

        tamarackLog = registerLargeLog("tamarack_log");
        tamarackLogStripped = registerLargeLog("tamarack_stripped");
        tamarackLeaves = registerLeaves("tamarack_leaves");
        tamarackPlanks = registerPlanks("tamarack_planks");
        tamarackSapling = registerLargeSapling("tamarack_sapling");

        turkishPineLog = registerLargeLog("turkish_pine_log");
        turkishPineLogStripped = registerLargeLog("turkish_pine_stripped");
        turkishPineLeaves = registerLeaves("turkish_pine_leaves");
        turkishPinePlanks = registerPlanks("turkish_pine_planks");
        turkishPineSapling = registerLargeSapling("turkish_pine_sapling");

        umbrellaTreeLog = registerLargeLog("umbrella_tree_log");
        umbrellaTreeLogStripped = registerLargeLog("umbrella_tree_stripped");
        umbrellaTreeLeaves = registerLeaves("umbrella_tree_leaves");
        umbrellaTreePlanks = registerPlanks("umbrella_tree_planks");
        umbrellaTreeSapling = registerLargeSapling("umbrella_tree_sapling");

        weepingWillowLog = registerLargeLog("weeping_willow_log");
        weepingWillowLogStripped = registerLargeLog("weeping_willow_stripped");
        weepingWillowLeaves = registerLeaves("weeping_willow_leaves");
        weepingWillowPlanks = registerPlanks("weeping_willow_planks");
        weepingWillowSapling = registerLargeSapling("weeping_willow_sapling");

        whitePoplarLog = registerLargeLog("white_poplar_log");
        whitePoplarLogStripped = registerLargeLog("white_poplar_stripped");
        whitePoplarLeavesOrange = registerLeaves("white_poplar_leaves_orange");
        whitePoplarLeavesRed = registerLeaves("white_poplar_leaves_red");
        whitePoplarLeavesYellow = registerLeaves("white_poplar_leaves_yellow");
        whitePoplarPlanks = registerPlanks("white_poplar_planks");
        whitePoplarSaplingOrange = registerLargeSapling("white_poplar_sapling_orange");
        whitePoplarSaplingRed = registerLargeSapling("white_poplar_sapling_red");
        whitePoplarSaplingYellow = registerLargeSapling("white_poplar_sapling_yellow");

        yewLog = registerLargeLog("yew_log");
        yewLogStripped = registerLargeLog("yew_stripped");
        yewLeaves = registerLeaves("yew_leaves");
        yewPlanks = registerPlanks("yew_planks");
        yewSapling = registerLargeSapling("yew_sapling");
    }

    // Bushes

    // Standalone Grasses

    // Standalone Flora

    // Mushrooms

    // Grass Decorators

    // Vines

    // Full Trees
    private Block registerLargeLog(String name) {
        Block log = new BaseFullLog().setBlockName(name);
        GameRegistry.registerBlock(log, name);
        return log;
    }

    private Block registerLeaves(String name) {
        Block leaves = new BaseLeaves().setBlockName(name);
        GameRegistry.registerBlock(leaves, name);
        return leaves;
    }

    private Block registerPlanks(String name) {
        Block planks = new BasePlanks().setBlockName(name)
            .setBlockTextureName(name);
        GameRegistry.registerBlock(planks, name);
        return planks;
    }

    private Block registerLargeSapling(String name) {
        Block sapling = new BaseSaplingBlock().setBlockName(name)
            .setBlockTextureName(name);
        GameRegistry.registerBlock(sapling, name);
        return sapling;
    }

    public static Block amaranthLog;
    public static Block amaranthLogStripped;
    public static Block amaranthLeaves;
    public static Block amaranthPlanks;
    public static Block amaranthSapling;

    public static Block americanSycamoreLog;
    public static Block americanSycamoreLogStripped;
    public static Block americanSycamoreLeaves;
    public static Block americanSycamorePlanks;
    public static Block americanSycamoreSapling;

    public static Block ashLog;
    public static Block ashLogStripped;
    public static Block ashLeaves;
    public static Block ashPlanks;
    public static Block ashSapling;

    public static Block baldCypressLog;
    public static Block baldCypressLogStripped;
    public static Block baldCypressLeaves;
    public static Block baldCypressPlanks;
    public static Block baldCypressSapling;

    public static Block baobabLog;
    public static Block baobabLogStripped;
    public static Block baobabLeaves;
    public static Block baobabPlanks;
    public static Block baobabSapling;

    public static Block beechLog;
    public static Block beechLogStripped;
    public static Block beechLeaves;
    public static Block beechPlanks;
    public static Block beechSapling;

    public static Block blackOakLog;
    public static Block blackOakLogStripped;
    public static Block blackOakLeaves;
    public static Block blackOakPlanks;
    public static Block blackOakSapling;

    public static Block blackPineLog;
    public static Block blackPineLogStripped;
    public static Block blackPineLeaves;
    public static Block blackPinePlanks;
    public static Block blackPineSapling;

    public static Block cacaoLog;
    public static Block cacaoLogStripped;
    public static Block cacaoLeaves;
    public static Block cacaoPlanks;
    public static Block cacaoSapling;

    public static Block candlenutLog;
    public static Block candlenutLogStripped;
    public static Block candlenutLeaves;
    public static Block candlenutPlanks;
    public static Block candlenutSapling;

    public static Block coastRedwoodLog;
    public static Block coastRedwoodLogStripped;
    public static Block coastRedwoodLeaves;
    public static Block coastRedwoodLeavesBurnt;
    public static Block coastRedwoodPlanks;
    public static Block coastRedwoodSapling;

    public static Block coconutPalmLog;
    public static Block coconutPalmLogStripped;
    public static Block coconutPalmLeaves;
    public static Block coconutPalmPlanks;
    public static Block coconutPalmSapling;

    public static Block coolibahLog;
    public static Block coolibahLogStripped;
    public static Block coolibahLeaves;
    public static Block coolibahPlanks;
    public static Block coolibahSapling;

    public static Block dahurianLarchLog;
    public static Block dahurianLarchLogStripped;
    public static Block dahurianLarchLeaves;
    public static Block dahurianLarchPlanks;
    public static Block dahurianLarchSapling;

    public static Block dragonsBloodLog;
    public static Block dragonsBloodLogStripped;
    public static Block dragonsBloodLeaves;
    public static Block dragonsBloodPlanks;
    public static Block dragonsBloodSapling;

    public static Block driftwoodLog;
    public static Block driftwoodPlanks;

    public static Block ebonyLog;
    public static Block ebonyLogStripped;
    public static Block ebonyLeaves;
    public static Block ebonyPlanks;
    public static Block ebonySapling;

    public static Block giantSequoiaLog;
    public static Block giantSequoiaLogStripped;
    public static Block giantSequoiaLeaves;
    public static Block giantSequoiaLeavesBurnt;
    public static Block giantSequoiaPlanks;
    public static Block giantSequoiaSapling;

    public static Block grandFirLog;
    public static Block grandFirLogStripped;
    public static Block grandFirLeaves;
    public static Block grandFirPlanks;
    public static Block grandFirSapling;

    public static Block hawthornLog;
    public static Block hawthornLogStripped;
    public static Block hawthornLeaves;
    public static Block hawthornPlanks;
    public static Block hawthornSapling;

    public static Block kankanLog;
    public static Block kankanLogStripped;
    public static Block kankanLeaves;
    public static Block kankanPlanks;
    public static Block kankanSapling;

    public static Block koaLog;
    public static Block koaLogStripped;
    public static Block koaLeaves;
    public static Block koaPlanks;
    public static Block koaSapling;

    public static Block mahoganyLog;
    public static Block mahoganyLogStripped;
    public static Block mahoganyLeaves;
    public static Block mahoganyPlanks;
    public static Block mahoganySapling;

    public static Block mediterraneanCypressLog;
    public static Block mediterraneanCypressLogStripped;
    public static Block mediterraneanCypressLeaves;
    public static Block mediterraneanCypressPlanks;
    public static Block mediterraneanCypressSapling;

    public static Block nazarenoLog;
    public static Block nazarenoLogStripped;
    public static Block nazarenoLeaves;
    public static Block nazarenoPlanks;
    public static Block nazarenoSapling;

    public static Block paperBirchLog;
    public static Block paperBirchLogStripped;
    public static Block paperBirchLeaves;
    public static Block paperBirchPlanks;
    public static Block paperBirchSapling;

    public static Block pedunculateOakLog;
    public static Block pedunculateOakLogStripped;
    public static Block pedunculateOakLeaves;
    public static Block pedunculateOakPlanks;
    public static Block pedunculateOakSapling;

    public static Block quakingAspenLog;
    public static Block quakingAspenLogStripped;
    public static Block quakingAspenLeaves;
    public static Block quakingAspenPlanks;
    public static Block quakingAspenSapling;

    public static Block rainbowEucalyptusLog;
    public static Block rainbowEucalyptusLogStripped;
    public static Block rainbowEucalyptusLeaves;
    public static Block rainbowEucalyptusPlanks;
    public static Block rainbowEucalyptusSapling;

    public static Block redAlderLog;
    public static Block redAlderLogStripped;
    public static Block redAlderLeaves;
    public static Block redAlderPlanks;
    public static Block redAlderSapling;

    public static Block redGumLog;
    public static Block redGumLogStripped;
    public static Block redGumLeaves;
    public static Block redGumPlanks;
    public static Block redGumSapling;

    public static Block redMapleLog;
    public static Block redMapleLogStripped;
    public static Block redMapleLeaves;
    public static Block redMaplePlanks;
    public static Block redMapleSapling;

    public static Block redSpruceLog;
    public static Block redSpruceLogStripped;
    public static Block redSpruceLeaves;
    public static Block redSprucePlanks;
    public static Block redSpruceSapling;

    public static Block rowanLog;
    public static Block rowanLogStripped;
    public static Block rowanLeaves;
    public static Block rowanPlanks;
    public static Block rowanSapling;

    public static Block royalPalmLog;
    public static Block royalPalmCrown;
    public static Block royalPalmLeaves;
    public static Block royalPalmPlanks;
    public static Block royalPalmSapling;

    public static Block rubberLog;
    public static Block rubberLogExpended;
    public static Block rubberLogResinous;
    public static Block rubberLogStripped;
    public static Block rubberLeaves;
    public static Block rubberPlanks;
    public static Block rubberSapling;

    public static Block scotsPineLog;
    public static Block scotsPineLogStripped;
    public static Block scotsPineLeaves;
    public static Block scotsPinePlanks;
    public static Block scotsPineSapling;

    public static Block siberianLarchLog;
    public static Block siberianLarchLogStripped;
    public static Block siberianLarchLeaves;
    public static Block siberianLarchPlanks;
    public static Block siberianLarchSapling;

    public static Block sierraJuniperLog;
    public static Block sierraJuniperLogStripped;
    public static Block sierraJuniperLeaves;
    public static Block sierraJuniperPlanks;
    public static Block sierraJuniperSapling;

    public static Block southernMagnoliaLog;
    public static Block southernMagnoliaLogStripped;
    public static Block southernMagnoliaLeaves;
    public static Block southernMagnoliaPlanks;
    public static Block southernMagnoliaSapling;

    public static Block sweetCherryLog;
    public static Block sweetCherryLogStripped;
    public static Block sweetCherryLeaves;
    public static Block sweetCherryPlanks;
    public static Block sweetCherrySapling;

    public static Block sweetgumLog;
    public static Block sweetgumLogStripped;
    public static Block sweetgumLeaves;
    public static Block sweetgumPlanks;
    public static Block sweetgumSapling;

    public static Block tamarackLog;
    public static Block tamarackLogStripped;
    public static Block tamarackLeaves;
    public static Block tamarackPlanks;
    public static Block tamarackSapling;

    public static Block turkishPineLog;
    public static Block turkishPineLogStripped;
    public static Block turkishPineLeaves;
    public static Block turkishPinePlanks;
    public static Block turkishPineSapling;

    public static Block umbrellaTreeLog;
    public static Block umbrellaTreeLogStripped;
    public static Block umbrellaTreeLeaves;
    public static Block umbrellaTreePlanks;
    public static Block umbrellaTreeSapling;

    public static Block weepingWillowLog;
    public static Block weepingWillowLogStripped;
    public static Block weepingWillowLeaves;
    public static Block weepingWillowPlanks;
    public static Block weepingWillowSapling;

    public static Block whitePoplarLog;
    public static Block whitePoplarLogStripped;
    public static Block whitePoplarLeavesOrange;
    public static Block whitePoplarLeavesRed;
    public static Block whitePoplarLeavesYellow;
    public static Block whitePoplarPlanks;
    public static Block whitePoplarSaplingOrange;
    public static Block whitePoplarSaplingRed;
    public static Block whitePoplarSaplingYellow;

    public static Block yewLog;
    public static Block yewLogStripped;
    public static Block yewLeaves;
    public static Block yewPlanks;
    public static Block yewSapling;

}
