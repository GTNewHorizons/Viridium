package net.junedev.viridium.blocks;

import java.util.ArrayList;

import net.junedev.viridium.Viridium;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseLeaves extends BlockLeaves {

    public IIcon[] textures;
    public String[] texNames = new String[] { "amaranth_leaves", "american_sycamore_leaves", "ash_leaves",
        "bald_cypress_leaves", "baobab_leaves", "beech_leaves", "black_oak_leaves", "black_pine_leaves", "cacao_leaves",
        "candlenut_leaves", "coast_redwood_leaves", "coconut_palm_leaves", "coolibah_leaves", "dahurian_larch_leaves",
        "dragons_blood_leaves", "ebony_leaves", "giant_sequoia_leaves", "giant_sequoia_leaves_burnt",
        "grand_fir_leaves", "hawthorn_leaves", "kankan_leaves", "koa_leaves", "mahogany_leaves",
        "mediterranean_cypress_leaves", "nazareno_leaves", "paper_birch_leaves", "pedunculate_oak_leaves",
        "quaking_aspen_leaves", "rainbow_eucalyptus_leaves", "red_alder_leaves", "red_gum_leaves", "red_maple_leaves",
        "red_spruce_leaves", "rowan_leaves", "royal_palm_leaves", "rubber_leaves", "scots_pine_leaves",
        "siberian_larch_leaves", "sierra_juniper_leaves", "southern_magnolia_leaves", "sweet_cherry_leaves",
        "sweetgum_leaves", "tamarack_leaves", "weeping_willow_leaves" };

    public BaseLeaves() {
        super();
        this.setHardness(0.2F);
        this.setCreativeTab(Viridium.VTab);
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
        return this.textures[findLeafName(texNames)];
    }

    // @Override
    // public Item getItemDropped(int meta, Random random, int fortune)
    // {
    // return ;
    // }

    @Override
    public String[] func_150125_e() {
        return new String[0];
    }

    @Override
    public boolean isOpaqueCube() {
        return Blocks.leaves.isOpaqueCube();
    }

    public int findLeafName(String[] names) {
        return ArrayUtils.indexOf(names, getUnlocalizedName().substring(5));
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        return null;
    }
}
