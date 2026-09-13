package net.junedev.viridium;

import cpw.mods.fml.common.registry.GameRegistry;
import net.junedev.viridium.blocks.BushBlock;
import net.junedev.viridium.blocks.SmallLogBlock;
import net.minecraft.block.Block;

public class ViriBlocks {

    public void preInit()
    {
        creosote_bush = registerBush("creosote_bush");
        golden_wattle = registerBush("golden_wattle");
        honeysuckle = registerBush("honeysuckle");
        koki_o_ono = registerBush("koki_o_ono");
        kudzu = registerBush("kudzu");
        kutjera = registerBush("kutjera");
        ma_o_hau_hele = registerBush("ma_o_hau_hele");
        oldman_saltbush = registerBush("oldman_saltbush");
        poinsettia = registerBush("poinsettia");
        quandong = registerBush("quandong");
        rhododendron = registerBush("rhododendron");
        rose_shrub = registerBush("rose_shrub");
        sandhill_wattle = registerBush("sandhill_wattle");
        silverleaf_hydrangea = registerBush("silverleaf_hydrangea");
        wedding_bush = registerBush("wedding_bush");


        black_spruce_log = registerSmallLog("black_spruce_log");
        blackthorn_log = registerSmallLog("blackthorn_log");
        callery_pear_log = registerSmallLog("callery_pear_log");
        citron_log = registerSmallLog("citron_log");
        crape_myrtle_log = registerSmallLog("crape_myrtle_log");
        desert_bloodwood_log = registerSmallLog("desert_bloodwood_log");
        flowering_dogwood_log = registerSmallLog("flowering_dogwood_log");
        giant_bamboo_log = registerSmallLog("giant_bamboo_log");
        holly_log = registerSmallLog("holly_log");
        jacaranda_log = registerSmallLog("jacaranda_log");
        joshua_tree_log = registerSmallLog("joshua_tree_log");
        koki_o_kea_log = registerSmallLog("koki_o_kea_log");
        kumquat_log = registerSmallLog("kumquat_log");
        lemon_myrtle_log = registerSmallLog("lemon_myrtle_log");
        lilac_log = registerSmallLog("lilac_log");
        mulga_log = registerSmallLog("mulga_log");
        olive_log = registerSmallLog("olive_log");
        pawpaw_log = registerSmallLog("pawpaw_log");
        pear_log = registerSmallLog("pear_log");
        red_cinchona_log = registerSmallLog("red_cinchona_log");
        wild_apple_log = registerSmallLog("wild_apple_log");
        witch_hazel_log = registerSmallLog("witch_hazel_log");

    }

    private Block registerBush(String name) {
        Block bush = new BushBlock().setBlockName(name);
        GameRegistry.registerBlock(bush, name);
        return bush;
    }

    private Block registerSmallLog(String name) {
        Block smallLog = new SmallLogBlock().setBlockName(name);
        GameRegistry.registerBlock(smallLog, name);
        return smallLog;
    }

    public static Block creosote_bush;
    public static Block golden_wattle;
    public static Block honeysuckle;
    public static Block koki_o_ono;
    public static Block kudzu;
    public static Block kutjera;
    public static Block ma_o_hau_hele;
    public static Block oldman_saltbush;
    public static Block poinsettia;
    public static Block quandong;
    public static Block rhododendron;
    public static Block rose_shrub;
    public static Block sandhill_wattle;
    public static Block silverleaf_hydrangea;
    public static Block wedding_bush;


    public static Block black_spruce_log;
    public static Block blackthorn_log;
    public static Block callery_pear_log;
    public static Block citron_log;
    public static Block crape_myrtle_log;
    public static Block desert_bloodwood_log;
    public static Block flowering_dogwood_log;
    public static Block giant_bamboo_log;
    public static Block holly_log;
    public static Block jacaranda_log;
    public static Block joshua_tree_log;
    public static Block koki_o_kea_log;
    public static Block kumquat_log;
    public static Block lemon_myrtle_log;
    public static Block lilac_log;
    public static Block mulga_log;
    public static Block olive_log;
    public static Block pawpaw_log;
    public static Block pear_log;
    public static Block red_cinchona_log;
    public static Block wild_apple_log;
    public static Block witch_hazel_log;
}
