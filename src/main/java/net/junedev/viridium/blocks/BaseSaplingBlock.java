package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import org.apache.commons.lang3.ArrayUtils;

public class BaseSaplingBlock extends BlockSapling {

    public IIcon[] textures;
    public String[] texNames = new String[] { "amaranth_sapling", "american_sycamore_sapling", "ash_sapling",
        "bald_cypress_sapling", "baobab_sapling", "beech_sapling", "black_oak_sapling", "black_pine_sapling",
        "cacao_sapling", "candlenut_sapling", "coast_redwood_sapling", "coconut_palm_sapling", "coolibah_sapling",
        "dahurian_larch_sapling", "dragons_blood_sapling", "ebony_sapling", "giant_sequoia_sapling",
        "grand_fir_sapling", "hawthorn_sapling", "kankan_sapling", "koa_sapling", "mahogany_sapling",
        "mediterranean_cypress_sapling", "nazareno_sapling", "paper_birch_sapling", "pedunculate_oak_sapling",
        "quaking_aspen_sapling", "rainbow_eucalyptus_sapling", "red_alder_sapling", "red_gum_sapling",
        "red_spruce_sapling", "red_maple_sapling", "rowan_sapling", "royal_palm_sapling", "rubber_sapling",
        "scots_pine_sapling", "siberian_larch_sapling", "sierra_juniper_sapling", "southern_magnolia_sapling",
        "sweet_cherry_sapling", "sweetgum_sapling", "tamarack_sapling", "weeping_willow_sapling" };

    public BaseSaplingBlock() {
        super();
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.textures = new IIcon[texNames.length];

        for (int i = 0; i < texNames.length; ++i) {
            this.textures[i] = register.registerIcon(Viridium.MOD_ID + ":" + texNames[i]);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return textures[findSaplingName(texNames)];
    }

    public int findSaplingName(String[] names) {
        return ArrayUtils.indexOf(names, getUnlocalizedName().substring(5));
    }

}
