package net.junedev.viridium;

import net.junedev.viridium.blocks.BushBlock;
import net.junedev.viridium.blocks.SmallLogBlock;
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

        black_spruce_log = registerSmallLog("black_spruce_log");
        blackthorn_log = registerSmallLog("blackthorn_log");
        blue_mahoe_log = registerSmallLog("blue_mahoe_log");
        box_elder_log = registerSmallLog("box_elder_log");
        callery_pear_log = registerSmallLog("callery_pear_log");
        citron_log = registerSmallLog("citron_log");
        crape_myrtle_log = registerSmallLog("crape_myrtle_log");
        desert_bloodwood_log = registerSmallLog("desert_bloodwood_log");
        flowering_dogwood_log = registerSmallLog("flowering_dogwood_log");
        giant_bamboo_log = registerSmallLog("giant_bamboo_log", 3, 4, false);
        holly_log = registerSmallLog("holly_log");
        jacaranda_log = registerSmallLog("jacaranda_log");
        japanese_maple_log = registerSmallLog("japanese_maple_log");
        joshua_tree_log = registerSmallLog("joshua_tree_log");
        koki_o_kea_log = registerSmallLog("koki_o_kea_log");
        kumquat_log = registerSmallLog("kumquat_log");
        lemon_myrtle_log = registerSmallLog("lemon_myrtle_log");
        lilac_log = registerSmallLog("lilac_log");
        mimosa_log = registerSmallLog("mimosa_log");
        mulga_log = registerSmallLog("mulga_log");
        olive_log = registerSmallLog("olive_log");
        pacific_yew_log = registerSmallLog("pacific_yew_log");
        paperbark_maple_log = registerSmallLog("paperbark_maple_log");
        pawpaw_log = registerSmallLog("pawpaw_log");
        pear_log = registerSmallLog("pear_log");
        red_cinchona_log = registerSmallLog("red_cinchona_log");
        redbud_log = registerSmallLog("redbud_log");
        wild_apple_log = registerSmallLog("wild_apple_log");
        witch_hazel_log = registerSmallLog("witch_hazel_log");
        wisteria_log = registerSmallLog("wisteria_log");
        debug_log = registerSmallLog("debug_log");

    }

    private Block registerBush(String name) {
        Block bush = new BushBlock().setBlockName(name);
        GameRegistry.registerBlock(bush, name);
        return bush;
    }

    private Block registerSmallLog(String name) {
        return registerSmallLog(name, 4, 4, true);
    }

    private Block registerSmallLog(String name, int halfWidth, int faceHalfWidth, boolean doSidesConnect) {
        Block smallLog = new SmallLogBlock(halfWidth, faceHalfWidth, doSidesConnect).setBlockName(name);
        GameRegistry.registerBlock(smallLog, name);
        return smallLog;
    }

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

    public static Block black_spruce_log;
    public static Block blackthorn_log;
    public static Block blue_mahoe_log;
    public static Block box_elder_log;
    public static Block callery_pear_log;
    public static Block citron_log;
    public static Block crape_myrtle_log;
    public static Block desert_bloodwood_log;
    public static Block flowering_dogwood_log;
    public static Block giant_bamboo_log;
    public static Block holly_log;
    public static Block jacaranda_log;
    public static Block japanese_maple_log;
    public static Block joshua_tree_log;
    public static Block koki_o_kea_log;
    public static Block kumquat_log;
    public static Block lemon_myrtle_log;
    public static Block lilac_log;
    public static Block mimosa_log;
    public static Block mulga_log;
    public static Block olive_log;
    public static Block pacific_yew_log;
    public static Block paperbark_maple_log;
    public static Block pawpaw_log;
    public static Block pear_log;
    public static Block red_cinchona_log;
    public static Block redbud_log;
    public static Block wild_apple_log;
    public static Block witch_hazel_log;
    public static Block wisteria_log;
    public static Block debug_log;
}
