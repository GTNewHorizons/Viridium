package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasePlanks extends Block {

    public IIcon[] textures;
    public String[] texNames = new String[] { "amaranth_planks", "american_sycamore_planks", "ash_planks",
        "bald_cypress_planks", "baobab_planks", "beech_planks", "black_oak_planks", "black_pine_planks", "cacao_planks",
        "candlenut_planks", "coast_redwood_planks", "coconut_palm_planks", "coolibah_planks", "dahurian_larch_planks",
        "dragons_blood_planks", "ebony_planks", "giant_sequoia_planks", "grand_fir_planks", "hawthorn_planks",
        "kankan_planks", "koa_planks", "mahogany_planks", "mediterranean_cypress_planks", "nazareno_planks",
        "paper_birch_planks", "pedunculate_oak_planks", "quaking_aspen_planks", "rainbow_eucalyptus_planks",
        "red_alder_planks", "red_gum_planks", "red_maple_planks", "red_spruce_planks", "rowan_planks",
        "royal_palm_planks", "rubber_planks", "scots_pine_planks", "siberian_larch_planks", "sierra_juniper_planks",
        "southern_magnolia_planks", "sweet_cherry_planks", "sweetgum_planks", "tamarack_planks",
        "weeping_willow_planks", "driftwood_planks" };

    public BasePlanks() {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.textures = new IIcon[texNames.length];

        for (int i = 0; i < texNames.length; ++i) {
            this.textures[i] = register.registerIcon(Viridium.MOD_ID + ":" + texNames[i]);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.textures[findPlankName(texNames)];
    }

    public int findPlankName(String[] names) {
        return ArrayUtils.indexOf(names, getUnlocalizedName().substring(5));
    }
}
