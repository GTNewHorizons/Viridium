package net.junedev.viridium;

import cpw.mods.fml.common.registry.GameRegistry;
import net.junedev.viridium.blocks.BushBlock;
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




    }

    private Block registerBush(String name) {
        Block bush = new BushBlock().setBlockName(name);
        GameRegistry.registerBlock(bush, name);
        return bush;
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
}
