package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseFullLog extends BlockLog {

    public IIcon[] sides;
    public IIcon[] tops;
    public String[] texNames = new String[] { "amaranth_log", "amaranth_stripped", "american_sycamore_log",
        "american_sycamore_stripped", "ash_log", "ash_stripped", "bald_cypress_log", "bald_cypress_stripped",
        "baobab_log", "baobab_stripped", "beech_log", "beech_stripped", "black_oak_log", "black_oak_stripped",
        "black_pine_log", "black_pine_stripped", "cacao_log", "cacao_stripped", "candlenut_log", "candlenut_stripped",
        "coast_redwood_log", "coast_redwood_stripped", "coconut_palm_log", "coconut_palm_stripped", "coolibah_log",
        "coolibah_stripped", "dahurian_larch_log", "dahurian_larch_stripped", "dragons_blood_log",
        "dragons_blood_stripped", "ebony_log", "ebony_stripped", "giant_sequoia_log", "giant_sequoia_stripped",
        "grand_fir_log", "grand_fir_stripped", "hawthorn_log", "hawthorn_stripped", "kankan_log", "kankan_stripped",
        "koa_log", "koa_stripped", "mahogany_log", "mahogany_stripped", "mediterranean_cypress_log",
        "mediterranean_cypress_stripped", "nazareno_log", "nazareno_stripped", "paper_birch_log",
        "paper_birch_stripped", "pedunculate_oak_log", "pedunculate_oak_stripped", "quaking_aspen_log",
        "quaking_aspen_stripped", "rainbow_eucalyptus_log", "rainbow_eucalyptus_stripped", "red_alder_log",
        "red_alder_stripped", "red_gum_log", "red_gum_stripped", "red_maple_log", "red_maple_stripped",
        "red_spruce_log", "red_spruce_stripped", "rowan_log", "rowan_stripped", "royal_palm_log", "royal_palm_crown",
        "rubber_log", "rubber_log_expended", "rowan_log_resinous", "rubber_log_stripped", "scots_pine_log",
        "scots_pine_stripped", "siberian_larch_log", "siberian_larch_stripped", "sierra_juniper_log",
        "sierra_juniper_stripped", "southern_magnolia_log", "southern_magnolia_stripped", "sweet_cherry_log",
        "sweet_cherry_stripped", "sweetgum_log", "sweetgum_stripped", "tamarack_log", "tamarack_stripped",
        "weeping_willow_log", "weeping_willow_stripped", "driftwood_log" };

    public BaseFullLog() {
        super();
        this.setHardness(1.0F); // Hardness of vanilla logs?
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.sides = new IIcon[texNames.length];
        this.tops = new IIcon[texNames.length];

        for (int i = 0; i < texNames.length; ++i) {
            this.sides[i] = register.registerIcon(Viridium.MOD_ID + ":" + texNames[i] + "_side");
            this.tops[i] = register.registerIcon(Viridium.MOD_ID + ":" + texNames[i] + "_top");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int orientation = meta & 12;

        switch (orientation) {
            case 0:
                if (side == 1 || side == 0) return this.tops[findLogName(texNames)];
                break;
            case 4:
                if (side == 5 || side == 4) return this.tops[findLogName(texNames)];
                break;
            case 8:
                if (side == 2 || side == 3) return this.tops[findLogName(texNames)];
                break;
        }
        return this.sides[findLogName(texNames)];
    }

    public int findLogName(String[] names) {
        return ArrayUtils.indexOf(names, getUnlocalizedName().substring(5));
    }
}
