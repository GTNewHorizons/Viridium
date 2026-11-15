package net.junedev.viridium.items;

import java.util.List;

import net.junedev.viridium.blocks.IBlockScientificName;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockScientificName extends ItemBlock {

    public ItemBlockScientificName(Block block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {

        super.addInformation(stack, player, tooltip, advanced);

        if (this.field_150939_a instanceof IBlockScientificName sciBlock) {
            tooltip.add(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + sciBlock.getScientificName());
        }
    }
}
