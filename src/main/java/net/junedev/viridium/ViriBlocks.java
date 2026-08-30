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
        amaranthLog = new BaseFullLog().setBlockName("amaranth_log");
        amaranthLogStripped = new BaseFullLog().setBlockName("amaranth_stripped");
        amaranthLeaves = new BaseLeaves().setBlockName("amaranth_leaves");
        amaranthPlanks = new BasePlanks().setBlockName("amaranth_planks")
            .setBlockTextureName("amaranth_planks");
        amaranthSapling = new BaseSaplingBlock().setBlockName("amaranth_sapling")
            .setBlockTextureName("amaranth_sapling");
        GameRegistry.registerBlock(amaranthLog, "amaranth_log");
        GameRegistry.registerBlock(amaranthLogStripped, "amaranth_stripped");
        GameRegistry.registerBlock(amaranthLeaves, "amaranth_leaves");
        GameRegistry.registerBlock(amaranthPlanks, "amaranth_planks");
        GameRegistry.registerBlock(amaranthSapling, "amaranth_sapling");

        americanSycamoreLog = new BaseFullLog().setBlockName("american_sycamore_log");
        americanSycamoreLogStripped = new BaseFullLog().setBlockName("american_sycamore_stripped");
        americanSycamoreLeaves = new BaseLeaves().setBlockName("american_sycamore_leaves");
        americanSycamorePlanks = new BasePlanks().setBlockName("american_sycamore_planks")
            .setBlockTextureName("american_sycamore_planks");
        americanSycamoreSapling = new BaseSaplingBlock().setBlockName("american_sycamore_sapling")
            .setBlockTextureName("american_sycamore_sapling");
        GameRegistry.registerBlock(americanSycamoreLog, "american_sycamore_log");
        GameRegistry.registerBlock(americanSycamoreLogStripped, "american_sycamore_stripped");
        GameRegistry.registerBlock(americanSycamoreLeaves, "american_sycamore_leaves");
        GameRegistry.registerBlock(americanSycamorePlanks, "american_sycamore_planks");
        GameRegistry.registerBlock(americanSycamoreSapling, "american_sycamore_sapling");

        ashLog = new BaseFullLog().setBlockName("ash_log");
        ashLogStripped = new BaseFullLog().setBlockName("ash_stripped");
        ashLeaves = new BaseLeaves().setBlockName("ash_leaves");
        ashPlanks = new BasePlanks().setBlockName("ash_planks")
            .setBlockTextureName("ash_planks");
        ashSapling = new BaseSaplingBlock().setBlockName("ash_sapling")
            .setBlockTextureName("ash_sapling");
        GameRegistry.registerBlock(ashLog, "ash_log");
        GameRegistry.registerBlock(ashLogStripped, "ash_stripped");
        GameRegistry.registerBlock(ashLeaves, "ash_leaves");
        GameRegistry.registerBlock(ashPlanks, "ash_planks");
        GameRegistry.registerBlock(ashSapling, "ash_sapling");

        baldCypressLog = new BaseFullLog().setBlockName("bald_cypress_log");
        baldCypressLogStripped = new BaseFullLog().setBlockName("bald_cypress_stripped");
        baldCypressLeaves = new BaseLeaves().setBlockName("bald_cypress_leaves");
        baldCypressPlanks = new BasePlanks().setBlockName("bald_cypress_planks")
            .setBlockTextureName("bald_cypress_planks");
        baldCypressSapling = new BaseSaplingBlock().setBlockName("bald_cypress_sapling")
            .setBlockTextureName("bald_cypress_sapling");
        GameRegistry.registerBlock(baldCypressLog, "bald_cypress_log");
        GameRegistry.registerBlock(baldCypressLogStripped, "bald_cypress_stripped");
        GameRegistry.registerBlock(baldCypressLeaves, "bald_cypress_leaves");
        GameRegistry.registerBlock(baldCypressPlanks, "bald_cypress_planks");
        GameRegistry.registerBlock(baldCypressSapling, "bald_cypress_sapling");

        // TODO: Baobab planks texture
        baobabLog = new BaseFullLog().setBlockName("baobab_log");
        baobabLogStripped = new BaseFullLog().setBlockName("baobab_stripped");
        baobabLeaves = new BaseLeaves().setBlockName("baobab_leaves");
        baobabPlanks = new BasePlanks().setBlockName("baobab_planks")
            .setBlockTextureName("baobab_planks");
        baobabSapling = new BaseSaplingBlock().setBlockName("baobab_sapling")
            .setBlockTextureName("baobab_sapling");
        GameRegistry.registerBlock(baobabLog, "baobab_log");
        GameRegistry.registerBlock(baobabLogStripped, "baobab_stripped");
        GameRegistry.registerBlock(baobabLeaves, "baobab_leaves");
        GameRegistry.registerBlock(baobabPlanks, "baobab_planks");
        GameRegistry.registerBlock(baobabSapling, "baobab_sapling");

        beechLog = new BaseFullLog().setBlockName("beech_log");
        beechLogStripped = new BaseFullLog().setBlockName("beech_stripped");
        beechLeaves = new BaseLeaves().setBlockName("beech_leaves");
        beechPlanks = new BasePlanks().setBlockName("beech_planks")
            .setBlockTextureName("beech_planks");
        beechSapling = new BaseSaplingBlock().setBlockName("beech_sapling")
            .setBlockTextureName("beech_sapling");
        GameRegistry.registerBlock(beechLog, "beech_log");
        GameRegistry.registerBlock(beechLogStripped, "beech_stripped");
        GameRegistry.registerBlock(beechLeaves, "beech_leaves");
        GameRegistry.registerBlock(beechPlanks, "beech_planks");
        GameRegistry.registerBlock(beechSapling, "beech_sapling");

        blackOakLog = new BaseFullLog().setBlockName("black_oak_log");
        blackOakLogStripped = new BaseFullLog().setBlockName("black_oak_stripped");
        blackOakLeaves = new BaseLeaves().setBlockName("black_oak_leaves");
        blackOakPlanks = new BasePlanks().setBlockName("black_oak_planks")
            .setBlockTextureName("black_oak_planks");
        blackOakSapling = new BaseSaplingBlock().setBlockName("black_oak_sapling")
            .setBlockTextureName("black_oak_sapling");
        GameRegistry.registerBlock(blackOakLog, "black_oak_log");
        GameRegistry.registerBlock(blackOakLogStripped, "black_oak_stripped");
        GameRegistry.registerBlock(blackOakLeaves, "black_oak_leaves");
        GameRegistry.registerBlock(blackOakPlanks, "black_oak_planks");
        GameRegistry.registerBlock(blackOakSapling, "black_oak_sapling");

        blackPineLog = new BaseFullLog().setBlockName("black_pine_log");
        blackPineLogStripped = new BaseFullLog().setBlockName("black_pine_stripped");
        blackPineLeaves = new BaseLeaves().setBlockName("black_pine_leaves");
        blackPinePlanks = new BasePlanks().setBlockName("black_pine_planks")
            .setBlockTextureName("black_pine_planks");
        blackPineSapling = new BaseSaplingBlock().setBlockName("black_pine_sapling")
            .setBlockTextureName("black_pine_sapling");
        GameRegistry.registerBlock(blackPineLog, "black_pine_log");
        GameRegistry.registerBlock(blackPineLogStripped, "black_pine_stripped");
        GameRegistry.registerBlock(blackPineLeaves, "black_pine_leaves");
        GameRegistry.registerBlock(blackPinePlanks, "black_pine_planks");
        GameRegistry.registerBlock(blackPineSapling, "black_pine_sapling");

        cacaoLog = new BaseFullLog().setBlockName("cacao_log");
        cacaoLogStripped = new BaseFullLog().setBlockName("cacao_stripped");
        cacaoLeaves = new BaseLeaves().setBlockName("cacao_leaves");
        cacaoPlanks = new BasePlanks().setBlockName("cacao_planks")
            .setBlockTextureName("cacao_planks");
        cacaoSapling = new BaseSaplingBlock().setBlockName("cacao_sapling")
            .setBlockTextureName("cacao_sapling");
        GameRegistry.registerBlock(cacaoLog, "cacao_log");
        GameRegistry.registerBlock(cacaoLogStripped, "cacao_stripped");
        GameRegistry.registerBlock(cacaoLeaves, "cacao_leaves");
        GameRegistry.registerBlock(cacaoPlanks, "cacao_planks");
        GameRegistry.registerBlock(cacaoSapling, "cacao_sapling");

        candlenutLog = new BaseFullLog().setBlockName("candlenut_log");
        candlenutLogStripped = new BaseFullLog().setBlockName("candlenut_stripped");
        candlenutLeaves = new BaseLeaves().setBlockName("candlenut_leaves");
        candlenutPlanks = new BasePlanks().setBlockName("candlenut_planks")
            .setBlockTextureName("candlenut_planks");
        candlenutSapling = new BaseSaplingBlock().setBlockName("candlenut_sapling")
            .setBlockTextureName("candlenut_sapling");
        GameRegistry.registerBlock(candlenutLog, "candlenut_log");
        GameRegistry.registerBlock(candlenutLogStripped, "candlenut_stripped");
        GameRegistry.registerBlock(candlenutLeaves, "candlenut_leaves");
        GameRegistry.registerBlock(candlenutPlanks, "candlenut_planks");
        GameRegistry.registerBlock(candlenutSapling, "candlenut_sapling");

        coastRedwoodLog = new BaseFullLog().setBlockName("coast_redwood_log");
        coastRedwoodLogStripped = new BaseFullLog().setBlockName("coast_redwood_stripped");
        coastRedwoodLeaves = new BaseLeaves().setBlockName("coast_redwood_leaves");
        coastRedwoodLeavesBurnt = new BaseLeaves().setBlockName("coast_redwood_leaves_burnt");
        coastRedwoodPlanks = new BasePlanks().setBlockName("coast_redwood_planks")
            .setBlockTextureName("coast_redwood_planks");
        coastRedwoodSapling = new BaseSaplingBlock().setBlockName("coast_redwood_sapling")
            .setBlockTextureName("coast_redwood_sapling");
        GameRegistry.registerBlock(coastRedwoodLog, "coast_redwood_log");
        GameRegistry.registerBlock(coastRedwoodLogStripped, "coast_redwood_stripped");
        GameRegistry.registerBlock(coastRedwoodLeaves, "coast_redwood_leaves");
        GameRegistry.registerBlock(coastRedwoodPlanks, "coast_redwood_planks");
        GameRegistry.registerBlock(coastRedwoodSapling, "coast_redwood_sapling");

        coconutPalmLog = new BaseFullLog().setBlockName("coconut_palm_log");
        coconutPalmLogStripped = new BaseFullLog().setBlockName("coconut_palm_stripped");
        coconutPalmLeaves = new BaseLeaves().setBlockName("coconut_palm_leaves");
        coconutPalmPlanks = new BasePlanks().setBlockName("coconut_palm_planks")
            .setBlockTextureName("coconut_palm_planks");
        coconutPalmSapling = new BaseSaplingBlock().setBlockName("coconut_palm_sapling")
            .setBlockTextureName("coconut_palm_sapling");
        GameRegistry.registerBlock(coconutPalmLog, "coconut_palm_log");
        GameRegistry.registerBlock(coconutPalmLogStripped, "coconut_palm_stripped");
        GameRegistry.registerBlock(coconutPalmLeaves, "coconut_palm_leaves");
        GameRegistry.registerBlock(coconutPalmPlanks, "coconut_palm_planks");
        GameRegistry.registerBlock(coconutPalmSapling, "coconut_palm_sapling");

        coolibahLog = new BaseFullLog().setBlockName("coolibah_log");
        coolibahLogStripped = new BaseFullLog().setBlockName("coolibah_stripped");
        coolibahLeaves = new BaseLeaves().setBlockName("coolibah_leaves");
        coolibahPlanks = new BasePlanks().setBlockName("coolibah_planks")
            .setBlockTextureName("coolibah_planks");
        coolibahSapling = new BaseSaplingBlock().setBlockName("coolibah_sapling")
            .setBlockTextureName("coolibah_sapling");
        GameRegistry.registerBlock(coolibahLog, "coolibah_log");
        GameRegistry.registerBlock(coolibahLogStripped, "coolibah_stripped");
        GameRegistry.registerBlock(coolibahLeaves, "coolibah_leaves");
        GameRegistry.registerBlock(coolibahPlanks, "coolibah_planks");
        GameRegistry.registerBlock(coolibahSapling, "coolibah_sapling");

        dahurianLarchLog = new BaseFullLog().setBlockName("dahurian_larch_log");
        dahurianLarchLogStripped = new BaseFullLog().setBlockName("dahurian_larch_stripped");
        dahurianLarchLeaves = new BaseLeaves().setBlockName("dahurian_larch_leaves");
        dahurianLarchPlanks = new BasePlanks().setBlockName("dahurian_larch_planks")
            .setBlockTextureName("dahurian_larch_planks");
        dahurianLarchSapling = new BaseSaplingBlock().setBlockName("dahurian_larch_sapling")
            .setBlockTextureName("dahurian_larch_sapling");
        GameRegistry.registerBlock(dahurianLarchLog, "dahurian_larch_log");
        GameRegistry.registerBlock(dahurianLarchLogStripped, "dahurian_larch_stripped");
        GameRegistry.registerBlock(dahurianLarchLeaves, "dahurian_larch_leaves");
        GameRegistry.registerBlock(dahurianLarchPlanks, "dahurian_larch_planks");
        GameRegistry.registerBlock(dahurianLarchSapling, "dahurian_larch_sapling");

        dragonsBloodLog = new BaseFullLog().setBlockName("dragons_blood_log");
        dragonsBloodLogStripped = new BaseFullLog().setBlockName("dragons_blood_stripped");
        dragonsBloodLeaves = new BaseLeaves().setBlockName("dragons_blood_leaves");
        dragonsBloodPlanks = new BasePlanks().setBlockName("dragons_blood_planks")
            .setBlockTextureName("dragons_blood_planks");
        dragonsBloodSapling = new BaseSaplingBlock().setBlockName("dragons_blood_sapling")
            .setBlockTextureName("dragons_blood_sapling");
        GameRegistry.registerBlock(dragonsBloodLog, "dragons_blood_log");
        GameRegistry.registerBlock(dragonsBloodLogStripped, "dragons_blood_stripped");
        GameRegistry.registerBlock(dragonsBloodLeaves, "dragons_blood_leaves");
        GameRegistry.registerBlock(dragonsBloodPlanks, "dragons_blood_planks");
        GameRegistry.registerBlock(dragonsBloodSapling, "dragons_blood_sapling");

        driftwoodLog = new BaseFullLog().setBlockName("driftwood_log");
        driftwoodPlanks = new BasePlanks().setBlockName("driftwood_planks")
            .setBlockTextureName("driftwood_planks");
        GameRegistry.registerBlock(driftwoodLog, "driftwood_log");
        GameRegistry.registerBlock(driftwoodPlanks, "driftwood_planks");

        ebonyLog = new BaseFullLog().setBlockName("ebony_log");
        ebonyLogStripped = new BaseFullLog().setBlockName("ebony_stripped");
        ebonyLeaves = new BaseLeaves().setBlockName("ebony_leaves");
        ebonyPlanks = new BasePlanks().setBlockName("ebony_planks")
            .setBlockTextureName("ebony_planks");
        ebonySapling = new BaseSaplingBlock().setBlockName("ebony_sapling")
            .setBlockTextureName("ebony_sapling");
        GameRegistry.registerBlock(ebonyLog, "ebony_log");
        GameRegistry.registerBlock(ebonyLogStripped, "ebony_stripped");
        GameRegistry.registerBlock(ebonyLeaves, "ebony_leaves");
        GameRegistry.registerBlock(ebonyPlanks, "ebony_planks");
        GameRegistry.registerBlock(ebonySapling, "ebony_sapling");

        giantSequoiaLog = new BaseFullLog().setBlockName("giant_sequoia_log");
        giantSequoiaLogStripped = new BaseFullLog().setBlockName("giant_sequoia_stripped");
        giantSequoiaLeaves = new BaseLeaves().setBlockName("giant_sequoia_leaves");
        giantSequoiaLeavesBurnt = new BaseLeaves().setBlockName("giant_sequoia_leaves_burnt");
        giantSequoiaPlanks = new BasePlanks().setBlockName("giant_sequoia_planks")
            .setBlockTextureName("giant_sequoia_planks");
        giantSequoiaSapling = new BaseSaplingBlock().setBlockName("giant_sequoia_sapling")
            .setBlockTextureName("giant_sequoia_sapling");
        GameRegistry.registerBlock(giantSequoiaLog, "giant_sequoia_log");
        GameRegistry.registerBlock(giantSequoiaLogStripped, "giant_sequoia_stripped");
        GameRegistry.registerBlock(giantSequoiaLeaves, "giant_sequoia_leaves");
        GameRegistry.registerBlock(giantSequoiaLeavesBurnt, "giant_sequoia_leaves_burnt");
        GameRegistry.registerBlock(giantSequoiaPlanks, "giant_sequoia_planks");
        GameRegistry.registerBlock(giantSequoiaSapling, "giant_sequoia_sapling");

        grandFirLog = new BaseFullLog().setBlockName("grand_fir_log");
        grandFirLogStripped = new BaseFullLog().setBlockName("grand_fir_stripped");
        grandFirLeaves = new BaseLeaves().setBlockName("grand_fir_leaves");
        grandFirPlanks = new BasePlanks().setBlockName("grand_fir_planks")
            .setBlockTextureName("grand_fir_planks");
        grandFirSapling = new BaseSaplingBlock().setBlockName("grand_fir_sapling")
            .setBlockTextureName("grand_fir_sapling");
        GameRegistry.registerBlock(grandFirLog, "grand_fir_log");
        GameRegistry.registerBlock(grandFirLogStripped, "grand_fir_stripped");
        GameRegistry.registerBlock(grandFirLeaves, "grand_fir_leaves");
        GameRegistry.registerBlock(grandFirPlanks, "grand_fir_planks");
        GameRegistry.registerBlock(grandFirSapling, "grand_fir_sapling");

        hawthornLog = new BaseFullLog().setBlockName("hawthorn_log");
        hawthornLogStripped = new BaseFullLog().setBlockName("hawthorn_stripped");
        hawthornLeaves = new BaseLeaves().setBlockName("hawthorn_leaves");
        hawthornPlanks = new BasePlanks().setBlockName("hawthorn_planks")
            .setBlockTextureName("hawthorn_planks");
        hawthornSapling = new BaseSaplingBlock().setBlockName("hawthorn_sapling")
            .setBlockTextureName("hawthorn_sapling");
        GameRegistry.registerBlock(hawthornLog, "hawthorn_log");
        GameRegistry.registerBlock(hawthornLogStripped, "hawthorn_stripped");
        GameRegistry.registerBlock(hawthornLeaves, "hawthorn_leaves");
        GameRegistry.registerBlock(hawthornPlanks, "hawthorn_planks");
        GameRegistry.registerBlock(hawthornSapling, "hawthorn_sapling");

        kankanLog = new BaseFullLog().setBlockName("kankan_log");
        kankanLogStripped = new BaseFullLog().setBlockName("kankan_stripped");
        kankanLeaves = new BaseLeaves().setBlockName("kankan_leaves");
        kankanPlanks = new BasePlanks().setBlockName("kankan_planks")
            .setBlockTextureName("kankan_planks");
        kankanSapling = new BaseSaplingBlock().setBlockName("kankan_sapling")
            .setBlockTextureName("kankan_sapling");
        GameRegistry.registerBlock(kankanLog, "kankan_log");
        GameRegistry.registerBlock(kankanLogStripped, "kankan_stripped");
        GameRegistry.registerBlock(kankanLeaves, "kankan_leaves");
        GameRegistry.registerBlock(kankanPlanks, "kankan_planks");
        GameRegistry.registerBlock(kankanSapling, "kankan_sapling");

        koaLog = new BaseFullLog().setBlockName("koa_log");
        koaLogStripped = new BaseFullLog().setBlockName("koa_stripped");
        koaLeaves = new BaseLeaves().setBlockName("koa_leaves");
        koaPlanks = new BasePlanks().setBlockName("koa_planks")
            .setBlockTextureName("koa_planks");
        koaSapling = new BaseSaplingBlock().setBlockName("koa_sapling")
            .setBlockTextureName("koa_sapling");
        GameRegistry.registerBlock(koaLog, "koa_log");
        GameRegistry.registerBlock(koaLogStripped, "koa_stripped");
        GameRegistry.registerBlock(koaLeaves, "koa_leaves");
        GameRegistry.registerBlock(koaPlanks, "koa_planks");
        GameRegistry.registerBlock(koaSapling, "koa_sapling");

        mahoganyLog = new BaseFullLog().setBlockName("mahogany_log");
        mahoganyLogStripped = new BaseFullLog().setBlockName("mahogany_stripped");
        mahoganyLeaves = new BaseLeaves().setBlockName("mahogany_leaves");
        mahoganyPlanks = new BasePlanks().setBlockName("mahogany_planks")
            .setBlockTextureName("mahogany_planks");
        mahoganySapling = new BaseSaplingBlock().setBlockName("mahogany_sapling")
            .setBlockTextureName("mahogany_sapling");
        GameRegistry.registerBlock(mahoganyLog, "mahogany_log");
        GameRegistry.registerBlock(mahoganyLogStripped, "mahogany_stripped");
        GameRegistry.registerBlock(mahoganyLeaves, "mahogany_leaves");
        GameRegistry.registerBlock(mahoganyPlanks, "mahogany_planks");
        GameRegistry.registerBlock(mahoganySapling, "mahogany_sapling");

        mediterraneanCypressLog = new BaseFullLog().setBlockName("mediterranean_cypress_log");
        mediterraneanCypressLogStripped = new BaseFullLog().setBlockName("mediterranean_cypress_stripped");
        mediterraneanCypressLeaves = new BaseLeaves().setBlockName("mediterranean_cypress_leaves");
        mediterraneanCypressPlanks = new BasePlanks().setBlockName("mediterranean_cypress_planks")
            .setBlockTextureName("mediterranean_cypress_planks");
        mediterraneanCypressSapling = new BaseSaplingBlock().setBlockName("mediterranean_cypress_sapling")
            .setBlockTextureName("mediterranean_cypress_sapling");
        GameRegistry.registerBlock(mediterraneanCypressLog, "mediterranean_cypress_log");
        GameRegistry.registerBlock(mediterraneanCypressLogStripped, "mediterranean_cypress_stripped");
        GameRegistry.registerBlock(mediterraneanCypressLeaves, "mediterranean_cypress_leaves");
        GameRegistry.registerBlock(mediterraneanCypressPlanks, "mediterranean_cypress_planks");
        GameRegistry.registerBlock(mediterraneanCypressSapling, "mediterranean_cypress_sapling");

        nazarenoLog = new BaseFullLog().setBlockName("nazareno_log");
        nazarenoLogStripped = new BaseFullLog().setBlockName("nazareno_stripped");
        nazarenoLeaves = new BaseLeaves().setBlockName("nazareno_leaves");
        nazarenoPlanks = new BasePlanks().setBlockName("nazareno_planks")
            .setBlockTextureName("nazareno_planks");
        nazarenoSapling = new BaseSaplingBlock().setBlockName("nazareno_sapling")
            .setBlockTextureName("nazareno_sapling");
        GameRegistry.registerBlock(nazarenoLog, "nazareno_log");
        GameRegistry.registerBlock(nazarenoLogStripped, "nazareno_stripped");
        GameRegistry.registerBlock(nazarenoLeaves, "nazareno_leaves");
        GameRegistry.registerBlock(nazarenoPlanks, "nazareno_planks");
        GameRegistry.registerBlock(nazarenoSapling, "nazareno_sapling");

        paperBirchLog = new BaseFullLog().setBlockName("paper_birch_log");
        paperBirchLogStripped = new BaseFullLog().setBlockName("paper_birch_stripped");
        paperBirchLeaves = new BaseLeaves().setBlockName("paper_birch_leaves");
        paperBirchPlanks = new BasePlanks().setBlockName("paper_birch_planks")
            .setBlockTextureName("paper_birch_planks");
        paperBirchSapling = new BaseSaplingBlock().setBlockName("paper_birch_sapling")
            .setBlockTextureName("paper_birch_sapling");
        GameRegistry.registerBlock(paperBirchLog, "paper_birch_log");
        GameRegistry.registerBlock(paperBirchLogStripped, "paper_birch_stripped");
        GameRegistry.registerBlock(paperBirchLeaves, "paper_birch_leaves");
        GameRegistry.registerBlock(paperBirchPlanks, "paper_birch_planks");
        GameRegistry.registerBlock(paperBirchSapling, "paper_birch_sapling");

        pedunculateOakLog = new BaseFullLog().setBlockName("pedunculate_oak_log");
        pedunculateOakLogStripped = new BaseFullLog().setBlockName("pedunculate_oak_stripped");
        pedunculateOakLeaves = new BaseLeaves().setBlockName("pedunculate_oak_leaves");
        pedunculateOakPlanks = new BasePlanks().setBlockName("pedunculate_oak_planks")
            .setBlockTextureName("pedunculate_oak_planks");
        pedunculateOakSapling = new BaseSaplingBlock().setBlockName("pedunculate_oak_sapling")
            .setBlockTextureName("pedunculate_oak_sapling");
        GameRegistry.registerBlock(pedunculateOakLog, "pedunculate_oak_log");
        GameRegistry.registerBlock(pedunculateOakLogStripped, "pedunculate_oak_stripped");
        GameRegistry.registerBlock(pedunculateOakLeaves, "pedunculate_oak_leaves");
        GameRegistry.registerBlock(pedunculateOakPlanks, "pedunculate_oak_planks");
        GameRegistry.registerBlock(pedunculateOakSapling, "pedunculate_oak_sapling");

        quakingAspenLog = new BaseFullLog().setBlockName("quaking_aspen_log");
        quakingAspenLogStripped = new BaseFullLog().setBlockName("quaking_aspen_stripped");
        quakingAspenLeaves = new BaseLeaves().setBlockName("quaking_aspen_leaves");
        quakingAspenPlanks = new BasePlanks().setBlockName("quaking_aspen_planks")
            .setBlockTextureName("quaking_aspen_planks");
        quakingAspenSapling = new BaseSaplingBlock().setBlockName("quaking_aspen_sapling")
            .setBlockTextureName("quaking_aspen_sapling");
        GameRegistry.registerBlock(quakingAspenLog, "quaking_aspen_log");
        GameRegistry.registerBlock(quakingAspenLogStripped, "quaking_aspen_stripped");
        GameRegistry.registerBlock(quakingAspenLeaves, "quaking_aspen_leaves");
        GameRegistry.registerBlock(quakingAspenPlanks, "quaking_aspen_planks");
        GameRegistry.registerBlock(quakingAspenSapling, "quaking_aspen_sapling");

        rainbowEucalyptusLog = new BaseFullLog().setBlockName("rainbow_eucalyptus_log");
        rainbowEucalyptusLogStripped = new BaseFullLog().setBlockName("rainbow_eucalyptus_stripped");
        rainbowEucalyptusLeaves = new BaseLeaves().setBlockName("rainbow_eucalyptus_leaves");
        rainbowEucalyptusPlanks = new BasePlanks().setBlockName("rainbow_eucalyptus_planks")
            .setBlockTextureName("rainbow_eucalyptus_planks");
        rainbowEucalyptusSapling = new BaseSaplingBlock().setBlockName("rainbow_eucalyptus_sapling")
            .setBlockTextureName("rainbow_eucalyptus_sapling");
        GameRegistry.registerBlock(rainbowEucalyptusLog, "rainbow_eucalyptus_log");
        GameRegistry.registerBlock(rainbowEucalyptusLogStripped, "rainbow_eucalyptus_stripped");
        GameRegistry.registerBlock(rainbowEucalyptusLeaves, "rainbow_eucalyptus_leaves");
        GameRegistry.registerBlock(rainbowEucalyptusPlanks, "rainbow_eucalyptus_planks");
        GameRegistry.registerBlock(rainbowEucalyptusSapling, "rainbow_eucalyptus_sapling");

        redAlderLog = new BaseFullLog().setBlockName("red_alder_log");
        redAlderLogStripped = new BaseFullLog().setBlockName("red_alder_stripped");
        redAlderLeaves = new BaseLeaves().setBlockName("red_alder_leaves");
        redAlderPlanks = new BasePlanks().setBlockName("red_alder_planks")
            .setBlockTextureName("red_alder_planks");
        redAlderSapling = new BaseSaplingBlock().setBlockName("red_alder_sapling")
            .setBlockTextureName("red_alder_sapling");
        GameRegistry.registerBlock(redAlderLog, "red_alder_log");
        GameRegistry.registerBlock(redAlderLogStripped, "red_alder_stripped");
        GameRegistry.registerBlock(redAlderLeaves, "red_alder_leaves");
        GameRegistry.registerBlock(redAlderPlanks, "red_alder_planks");
        GameRegistry.registerBlock(redAlderSapling, "red_alder_sapling");

        redGumLog = new BaseFullLog().setBlockName("red_gum_log");
        redGumLogStripped = new BaseFullLog().setBlockName("red_gum_stripped");
        redGumLeaves = new BaseLeaves().setBlockName("red_gum_leaves");
        redGumPlanks = new BasePlanks().setBlockName("red_gum_planks")
            .setBlockTextureName("red_gum_planks");
        redGumSapling = new BaseSaplingBlock().setBlockName("red_gum_sapling")
            .setBlockTextureName("red_gum_sapling");
        GameRegistry.registerBlock(redGumLog, "red_gum_log");
        GameRegistry.registerBlock(redGumLogStripped, "red_gum_stripped");
        GameRegistry.registerBlock(redGumLeaves, "red_gum_leaves");
        GameRegistry.registerBlock(redGumPlanks, "red_gum_planks");
        GameRegistry.registerBlock(redGumSapling, "red_gum_sapling");

        redMapleLog = new BaseFullLog().setBlockName("red_maple_log");
        redMapleLogStripped = new BaseFullLog().setBlockName("red_maple_stripped");
        redMapleLeaves = new BaseLeaves().setBlockName("red_maple_leaves");
        redMaplePlanks = new BasePlanks().setBlockName("red_maple_planks")
            .setBlockTextureName("red_maple_planks");
        redMapleSapling = new BaseSaplingBlock().setBlockName("red_maple_sapling")
            .setBlockTextureName("red_maple_sapling");
        GameRegistry.registerBlock(redMapleLog, "red_maple_log");
        GameRegistry.registerBlock(redMapleLogStripped, "red_maple_stripped");
        GameRegistry.registerBlock(redMapleLeaves, "red_maple_leaves");
        GameRegistry.registerBlock(redMaplePlanks, "red_maple_planks");
        GameRegistry.registerBlock(redMapleSapling, "red_maple_sapling");

        redSpruceLog = new BaseFullLog().setBlockName("red_spruce_log");
        redSpruceLogStripped = new BaseFullLog().setBlockName("red_spruce_stripped");
        redSpruceLeaves = new BaseLeaves().setBlockName("red_spruce_leaves");
        redSprucePlanks = new BasePlanks().setBlockName("red_spruce_planks")
            .setBlockTextureName("red_spruce_planks");
        redSpruceSapling = new BaseSaplingBlock().setBlockName("red_spruce_sapling")
            .setBlockTextureName("red_spruce_sapling");
        GameRegistry.registerBlock(redSpruceLog, "red_spruce_log");
        GameRegistry.registerBlock(redSpruceLogStripped, "red_spruce_stripped");
        GameRegistry.registerBlock(redSpruceLeaves, "red_spruce_leaves");
        GameRegistry.registerBlock(redSprucePlanks, "red_spruce_planks");
        GameRegistry.registerBlock(redSpruceSapling, "red_spruce_sapling");

        rowanLog = new BaseFullLog().setBlockName("rowan_log");
        rowanLogStripped = new BaseFullLog().setBlockName("rowan_stripped");
        rowanLeaves = new BaseLeaves().setBlockName("rowan_leaves");
        rowanPlanks = new BasePlanks().setBlockName("rowan_planks")
            .setBlockTextureName("rowan_planks");
        rowanSapling = new BaseSaplingBlock().setBlockName("rowan_sapling")
            .setBlockTextureName("rowan_sapling");
        GameRegistry.registerBlock(rowanLog, "rowan_log");
        GameRegistry.registerBlock(rowanLogStripped, "rowan_stripped");
        GameRegistry.registerBlock(rowanLeaves, "rowan_leaves");
        GameRegistry.registerBlock(rowanPlanks, "rowan_planks");
        GameRegistry.registerBlock(rowanSapling, "rowan_sapling");

        royalPalmLog = new BaseFullLog().setBlockName("royal_palm_log");
        royalPalmCrown = new BaseFullLog().setBlockName("royal_palm_crown");
        royalPalmLeaves = new BaseLeaves().setBlockName("royal_palm_leaves");
        royalPalmPlanks = new BasePlanks().setBlockName("royal_palm_planks")
            .setBlockTextureName("royal_palm_planks");
        royalPalmSapling = new BaseSaplingBlock().setBlockName("royal_palm_sapling")
            .setBlockTextureName("royal_palm_sapling");
        GameRegistry.registerBlock(royalPalmLog, "royal_palm_log");
        GameRegistry.registerBlock(royalPalmCrown, "royal_palm_crown");
        GameRegistry.registerBlock(royalPalmLeaves, "royal_palm_leaves");
        GameRegistry.registerBlock(royalPalmPlanks, "royal_palm_planks");
        GameRegistry.registerBlock(royalPalmSapling, "royal_palm_sapling");

        rubberLog = new BaseFullLog().setBlockName("rubber_log");
        rubberLogExpended = new BaseFullLog().setBlockName("rubber_log_expended");
        rubberLogResinous = new BaseFullLog().setBlockName("rubber_log_resinous");
        rubberLogStripped = new BaseFullLog().setBlockName("rubber_stripped");
        rubberLeaves = new BaseLeaves().setBlockName("rubber_leaves");
        rubberPlanks = new BasePlanks().setBlockName("rubber_planks")
            .setBlockTextureName("rubber_planks");
        rubberSapling = new BaseSaplingBlock().setBlockName("rubber_sapling")
            .setBlockTextureName("rubber_sapling");
        GameRegistry.registerBlock(rubberLogExpended, "rubber_log_expended");
        GameRegistry.registerBlock(rubberLogResinous, "rubber_log_resinous");
        GameRegistry.registerBlock(rubberLogStripped, "rubber_stripped");
        GameRegistry.registerBlock(rubberLeaves, "rubber_leaves");
        GameRegistry.registerBlock(rubberPlanks, "rubber_planks");
        GameRegistry.registerBlock(rubberSapling, "rubber_sapling");

        scotsPineLog = new BaseFullLog().setBlockName("scots_pine_log");
        scotsPineLogStripped = new BaseFullLog().setBlockName("scots_pine_stripped");
        scotsPineLeaves = new BaseLeaves().setBlockName("scots_pine_leaves");
        scotsPinePlanks = new BasePlanks().setBlockName("scots_pine_planks")
            .setBlockTextureName("scots_pine_planks");
        scotsPineSapling = new BaseSaplingBlock().setBlockName("scots_pine_sapling")
            .setBlockTextureName("scots_pine_sapling");
        GameRegistry.registerBlock(scotsPineLog, "scots_pine_log");
        GameRegistry.registerBlock(scotsPineLogStripped, "scots_pine_stripped");
        GameRegistry.registerBlock(scotsPineLeaves, "scots_pine_leaves");
        GameRegistry.registerBlock(scotsPinePlanks, "scots_pine_planks");
        GameRegistry.registerBlock(scotsPineSapling, "scots_pine_sapling");

        siberianLarchLog = new BaseFullLog().setBlockName("siberian_larch_log");
        siberianLarchLogStripped = new BaseFullLog().setBlockName("siberian_larch_stripped");
        siberianLarchLeaves = new BaseLeaves().setBlockName("siberian_larch_leaves");
        siberianLarchPlanks = new BasePlanks().setBlockName("siberian_larch_planks")
            .setBlockTextureName("siberian_larch_planks");
        siberianLarchSapling = new BaseSaplingBlock().setBlockName("siberian_larch_sapling")
            .setBlockTextureName("siberian_larch_sapling");
        GameRegistry.registerBlock(siberianLarchLog, "siberian_larch_log");
        GameRegistry.registerBlock(siberianLarchLogStripped, "siberian_larch_stripped");
        GameRegistry.registerBlock(siberianLarchLeaves, "siberian_larch_leaves");
        GameRegistry.registerBlock(siberianLarchPlanks, "siberian_larch_planks");
        GameRegistry.registerBlock(siberianLarchSapling, "siberian_larch_sapling");

        sierraJuniperLog = new BaseFullLog().setBlockName("sierra_juniper_log");
        sierraJuniperLogStripped = new BaseFullLog().setBlockName("sierra_juniper_stripped");
        sierraJuniperLeaves = new BaseLeaves().setBlockName("sierra_juniper_leaves");
        sierraJuniperPlanks = new BasePlanks().setBlockName("sierra_juniper_planks")
            .setBlockTextureName("sierra_juniper_planks");
        sierraJuniperSapling = new BaseSaplingBlock().setBlockName("sierra_juniper_sapling")
            .setBlockTextureName("sierra_juniper_sapling");
        GameRegistry.registerBlock(sierraJuniperLog, "sierra_juniper_log");
        GameRegistry.registerBlock(sierraJuniperLogStripped, "sierra_juniper_stripped");
        GameRegistry.registerBlock(sierraJuniperLeaves, "sierra_juniper_leaves");
        GameRegistry.registerBlock(sierraJuniperPlanks, "sierra_juniper_planks");
        GameRegistry.registerBlock(sierraJuniperSapling, "sierra_juniper_sapling");

        southernMagnoliaLog = new BaseFullLog().setBlockName("southern_magnolia_log");
        southernMagnoliaLogStripped = new BaseFullLog().setBlockName("southern_magnolia_stripped");
        southernMagnoliaLeaves = new BaseLeaves().setBlockName("southern_magnolia_leaves");
        southernMagnoliaPlanks = new BasePlanks().setBlockName("southern_magnolia_planks")
            .setBlockTextureName("southern_magnolia_planks");
        southernMagnoliaSapling = new BaseSaplingBlock().setBlockName("southern_magnolia_sapling")
            .setBlockTextureName("southern_magnolia_sapling");
        GameRegistry.registerBlock(southernMagnoliaLog, "southern_magnolia_log");
        GameRegistry.registerBlock(southernMagnoliaLogStripped, "southern_magnolia_stripped");
        GameRegistry.registerBlock(southernMagnoliaLeaves, "southern_magnolia_leaves");
        GameRegistry.registerBlock(southernMagnoliaPlanks, "southern_magnolia_planks");
        GameRegistry.registerBlock(southernMagnoliaSapling, "southern_magnolia_sapling");

        sweetCherryLog = new BaseFullLog().setBlockName("sweet_cherry_log");
        sweetCherryLogStripped = new BaseFullLog().setBlockName("sweet_cherry_stripped");
        sweetCherryLeaves = new BaseLeaves().setBlockName("sweet_cherry_leaves");
        sweetCherryPlanks = new BasePlanks().setBlockName("sweet_cherry_planks")
            .setBlockTextureName("sweet_cherry_planks");
        sweetCherrySapling = new BaseSaplingBlock().setBlockName("sweet_cherry_sapling")
            .setBlockTextureName("sweet_cherry_sapling");
        GameRegistry.registerBlock(sweetCherryLog, "sweet_cherry_log");
        GameRegistry.registerBlock(sweetCherryLogStripped, "sweet_cherry_stripped");
        GameRegistry.registerBlock(sweetCherryLeaves, "sweet_cherry_leaves");
        GameRegistry.registerBlock(sweetCherryPlanks, "sweet_cherry_planks");
        GameRegistry.registerBlock(sweetCherrySapling, "sweet_cherry_sapling");

        sweetgumLog = new BaseFullLog().setBlockName("sweetgum_log");
        sweetgumLogStripped = new BaseFullLog().setBlockName("sweetgum_stripped");
        sweetgumLeaves = new BaseLeaves().setBlockName("sweetgum_leaves");
        sweetgumPlanks = new BasePlanks().setBlockName("sweetgum_planks")
            .setBlockTextureName("sweetgum_planks");
        sweetgumSapling = new BaseSaplingBlock().setBlockName("sweetgum_sapling")
            .setBlockTextureName("sweetgum_sapling");
        GameRegistry.registerBlock(sweetgumLog, "sweetgum_log");
        GameRegistry.registerBlock(sweetgumLogStripped, "sweetgum_stripped");
        GameRegistry.registerBlock(sweetgumLeaves, "sweetgum_leaves");
        GameRegistry.registerBlock(sweetgumPlanks, "sweetgum_planks");
        GameRegistry.registerBlock(sweetgumSapling, "sweetgum_sapling");

        tamarackLog = new BaseFullLog().setBlockName("tamarack_log");
        tamarackLogStripped = new BaseFullLog().setBlockName("tamarack_stripped");
        tamarackLeaves = new BaseLeaves().setBlockName("tamarack_leaves");
        tamarackPlanks = new BasePlanks().setBlockName("tamarack_planks")
            .setBlockTextureName("tamarack_planks");
        tamarackSapling = new BaseSaplingBlock().setBlockName("tamarack_sapling")
            .setBlockTextureName("tamarack_sapling");
        GameRegistry.registerBlock(tamarackLog, "tamarack_log");
        GameRegistry.registerBlock(tamarackLogStripped, "tamarack_stripped");
        GameRegistry.registerBlock(tamarackLeaves, "tamarack_leaves");
        GameRegistry.registerBlock(tamarackPlanks, "tamarack_planks");
        GameRegistry.registerBlock(tamarackSapling, "tamarack_sapling");

        turkishPineLog = new BaseFullLog().setBlockName("turkish_pine_log");
        turkishPineLogStripped = new BaseFullLog().setBlockName("turkish_pine_stripped");
        turkishPineLeaves = new BaseLeaves().setBlockName("turkish_pine_leaves");
        turkishPinePlanks = new BasePlanks().setBlockName("turkish_pine_planks")
            .setBlockTextureName("turkish_pine_planks");
        turkishPineSapling = new BaseSaplingBlock().setBlockName("turkish_pine_sapling")
            .setBlockTextureName("turkish_pine_sapling");
        GameRegistry.registerBlock(turkishPineLog, "turkish_pine_log");
        GameRegistry.registerBlock(turkishPineLogStripped, "turkish_pine_stripped");
        GameRegistry.registerBlock(turkishPineLeaves, "turkish_pine_leaves");
        GameRegistry.registerBlock(turkishPinePlanks, "turkish_pine_planks");
        GameRegistry.registerBlock(turkishPineSapling, "turkish_pine_sapling");

        umbrellaTreeLog = new BaseFullLog().setBlockName("umbrella_tree_log");
        umbrellaTreeLogStripped = new BaseFullLog().setBlockName("umbrella_tree_stripped");
        umbrellaTreeLeaves = new BaseLeaves().setBlockName("umbrella_tree_leaves");
        umbrellaTreePlanks = new BasePlanks().setBlockName("umbrella_tree_planks")
            .setBlockTextureName("umbrella_tree_planks");
        umbrellaTreeSapling = new BaseSaplingBlock().setBlockName("umbrella_tree_sapling")
            .setBlockTextureName("umbrella_tree_sapling");
        GameRegistry.registerBlock(umbrellaTreeLog, "umbrella_tree_log");
        GameRegistry.registerBlock(umbrellaTreeLogStripped, "umbrella_tree_stripped");
        GameRegistry.registerBlock(umbrellaTreeLeaves, "umbrella_tree_leaves");
        GameRegistry.registerBlock(umbrellaTreePlanks, "umbrella_tree_planks");
        GameRegistry.registerBlock(umbrellaTreeSapling, "umbrella_tree_sapling");

        weepingWillowLog = new BaseFullLog().setBlockName("weeping_willow_log");
        weepingWillowLogStripped = new BaseFullLog().setBlockName("weeping_willow_stripped");
        weepingWillowLeaves = new BaseLeaves().setBlockName("weeping_willow_leaves");
        weepingWillowPlanks = new BasePlanks().setBlockName("weeping_willow_planks")
            .setBlockTextureName("weeping_willow_planks");
        weepingWillowSapling = new BaseSaplingBlock().setBlockName("weeping_willow_sapling")
            .setBlockTextureName("weeping_willow_sapling");
        GameRegistry.registerBlock(weepingWillowLog, "weeping_willow_log");
        GameRegistry.registerBlock(weepingWillowLogStripped, "weeping_willow_stripped");
        GameRegistry.registerBlock(weepingWillowLeaves, "weeping_willow_leaves");
        GameRegistry.registerBlock(weepingWillowPlanks, "weeping_willow_planks");
        GameRegistry.registerBlock(weepingWillowSapling, "weeping_willow_sapling");

        whitePoplarLog = new BaseFullLog().setBlockName("white_poplar_log");
        whitePoplarLogStripped = new BaseFullLog().setBlockName("white_poplar_stripped");
        whitePoplarLeavesOrange = new BaseLeaves().setBlockName("white_poplar_leaves_orange");
        whitePoplarLeavesRed = new BaseLeaves().setBlockName("white_poplar_leaves_red");
        whitePoplarLeavesYellow = new BaseLeaves().setBlockName("white_poplar_leaves_yellow");
        whitePoplarPlanks = new BasePlanks().setBlockName("white_poplar_planks")
            .setBlockTextureName("white_poplar_planks");
        whitePoplarSaplingOrange = new BaseSaplingBlock().setBlockName("white_poplar_sapling_orange")
            .setBlockTextureName("white_poplar_sapling_orange");
        whitePoplarSaplingRed = new BaseSaplingBlock().setBlockName("white_poplar_sapling_red")
            .setBlockTextureName("white_poplar_sapling_red");
        whitePoplarSaplingYellow = new BaseSaplingBlock().setBlockName("white_poplar_sapling_yellow")
            .setBlockTextureName("white_poplar_sapling_yellow");
        GameRegistry.registerBlock(whitePoplarLog, "white_poplar_log");
        GameRegistry.registerBlock(whitePoplarLogStripped, "white_poplar_stripped");
        GameRegistry.registerBlock(whitePoplarLeavesOrange, "white_poplar_leaves_orange");
        GameRegistry.registerBlock(whitePoplarLeavesRed, "white_poplar_leaves_red");
        GameRegistry.registerBlock(whitePoplarLeavesYellow, "white_poplar_leaves_yellow");
        GameRegistry.registerBlock(whitePoplarPlanks, "white_poplar_planks");
        GameRegistry.registerBlock(whitePoplarSaplingOrange, "white_poplar_sapling_orange");
        GameRegistry.registerBlock(whitePoplarSaplingRed, "white_poplar_sapling_red");
        GameRegistry.registerBlock(whitePoplarSaplingYellow, "white_poplar_sapling_yellow");

        yewLog = new BaseFullLog().setBlockName("yew_log");
        yewLogStripped = new BaseFullLog().setBlockName("yew_stripped");
        yewLeaves = new BaseLeaves().setBlockName("yew_leaves");
        yewPlanks = new BasePlanks().setBlockName("yew_planks")
            .setBlockTextureName("yew_planks");
        yewSapling = new BaseSaplingBlock().setBlockName("yew_sapling")
            .setBlockTextureName("yew_sapling");
        GameRegistry.registerBlock(yewLog, "yew_log");
        GameRegistry.registerBlock(yewLogStripped, "yew_stripped");
        GameRegistry.registerBlock(yewLeaves, "yew_leaves");
        GameRegistry.registerBlock(yewPlanks, "yew_planks");
        GameRegistry.registerBlock(yewSapling, "yew_sapling");
    }

    // Bushes

    // Standalone Grasses

    // Standalone Flora

    // Mushrooms

    // Grass Decorators

    // Vines

    // Full Trees
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
    // TODO Paper Birch Stripped Textures
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
